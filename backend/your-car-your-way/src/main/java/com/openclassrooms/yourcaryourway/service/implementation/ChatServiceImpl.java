package com.openclassrooms.yourcaryourway.service.implementation;

import com.openclassrooms.yourcaryourway.dto.request.NewChat;
import com.openclassrooms.yourcaryourway.model.Chat;
import com.openclassrooms.yourcaryourway.model.User;
import com.openclassrooms.yourcaryourway.repository.ChatRepository;
import com.openclassrooms.yourcaryourway.service.ChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {
    private ChatRepository chatRepository;

    @Override
    public Chat createChat(NewChat newChat) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return chatRepository.save(Chat.builder()
                .author(user)
                .subject(newChat.getSubject())
                .date(new Date())
                .build());
    }
}
