package com.openclassrooms.yourcaryourway.mapper;

import com.openclassrooms.yourcaryourway.dto.response.MessageResponse;
import com.openclassrooms.yourcaryourway.model.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageMapper {
    public List<MessageResponse> toDto(List<Message> messages) {
        return messages.stream().map(this::toDto).toList();
    }
    public MessageResponse toDto(Message m) {
        return MessageResponse.builder()
                .content(m.getContent())
                .senderId(m.getSender().getId())
                .date(m.getDate().getTime())
                .sender(m.getSender().getUsername())
                .build();
    }
}
