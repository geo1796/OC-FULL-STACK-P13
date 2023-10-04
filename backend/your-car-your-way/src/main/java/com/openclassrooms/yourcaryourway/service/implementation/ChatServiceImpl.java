package com.openclassrooms.yourcaryourway.service.implementation;

import com.openclassrooms.yourcaryourway.dto.request.NewChat;
import com.openclassrooms.yourcaryourway.dto.response.ChatResponse;
import com.openclassrooms.yourcaryourway.dto.response.MessageResponse;
import com.openclassrooms.yourcaryourway.exception.NotFoundException;
import com.openclassrooms.yourcaryourway.mapper.ChatMapper;
import com.openclassrooms.yourcaryourway.mapper.MessageMapper;
import com.openclassrooms.yourcaryourway.model.Chat;
import com.openclassrooms.yourcaryourway.model.Message;
import com.openclassrooms.yourcaryourway.model.User;
import com.openclassrooms.yourcaryourway.repository.ChatRepository;
import com.openclassrooms.yourcaryourway.repository.MessageRepository;
import com.openclassrooms.yourcaryourway.repository.UserRepository;
import com.openclassrooms.yourcaryourway.service.ChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {
    private ChatRepository chatRepository;
    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private ChatMapper chatMapper;
    private MessageMapper messageMapper;

    @Override
    public Chat createChat(NewChat newChat) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return chatRepository.save(Chat.builder()
                .author(user)
                .subject(newChat.getSubject())
                .date(new Date())
                .build());
    }

    @Override
    public ChatResponse getById(Integer id) {
        Optional<Chat> optionalChat = chatRepository.findById(id);
        if (optionalChat.isEmpty()) {
            throw new NotFoundException();
        }
        Chat chat = optionalChat.get();
        chat.setMessages(messageRepository.findAllByChatId(id));
        return chatMapper.toDto(chat);
    }

    @Override
    public MessageResponse addMessage(Integer chatId, Integer userId, String content) {
        Optional<Chat> optionalChat = chatRepository.findById(chatId);
        if (optionalChat.isEmpty()) {
            throw new NotFoundException();
        }
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException();
        }
        return messageMapper.toDto(messageRepository.save(Message.builder()
                .chat(optionalChat.get())
                .sender(optionalUser.get())
                .content(content)
                .date(new Date())
                .build()));
    }

    @Override
    public List<ChatResponse> findAll() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.isEmployee()) {
            return chatMapper.toDto(chatRepository.findAll());
        }
        return chatMapper.toDto(chatRepository.findAllByAuthorId(user.getId()));
    }
}
