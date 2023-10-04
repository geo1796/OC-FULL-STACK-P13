package com.openclassrooms.yourcaryourway.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ChatResponse {
    private Integer id;
    private String subject;
    private String author;
    private Long date;
    private List<MessageResponse> messages;
}
