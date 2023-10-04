package com.openclassrooms.yourcaryourway.controller;

import com.openclassrooms.yourcaryourway.dto.request.NewChat;
import com.openclassrooms.yourcaryourway.model.Chat;
import com.openclassrooms.yourcaryourway.service.ChatService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chats")
@AllArgsConstructor
@Slf4j
public class ChatController {
    private ChatService chatService;

    @PostMapping()
    ResponseEntity<Chat> startChat(@RequestBody @Valid NewChat newChat) {
        return new ResponseEntity<>(chatService.createChat(newChat), HttpStatus.CREATED);
    }
}
