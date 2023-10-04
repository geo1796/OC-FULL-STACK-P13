package com.openclassrooms.yourcaryourway.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MessageResponse {
    private String content;
    private Long date;
    private Integer senderId;
    private String sender;
}
