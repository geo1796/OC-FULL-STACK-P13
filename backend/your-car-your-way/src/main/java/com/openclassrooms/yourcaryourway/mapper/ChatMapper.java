package com.openclassrooms.yourcaryourway.mapper;

import com.openclassrooms.yourcaryourway.dto.response.ChatResponse;
import com.openclassrooms.yourcaryourway.model.Chat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class ChatMapper {
    private MessageMapper messageMapper;

    public List<ChatResponse> toDto(List<Chat> chats) {
        return chats.stream().map(this::toDto).toList();
    }

    public ChatResponse toDto(Chat chat) {
        return ChatResponse.builder()
                .id(chat.getId())
                .messages(messageMapper.toDto(chat.getMessages()))
                .date(chat.getDate().getTime())
                .author(chat.getAuthor().getUsername())
                .subject(chat.getSubject())
                .build();
    }
}
