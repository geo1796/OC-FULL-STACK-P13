package com.openclassrooms.yourcaryourway.controller;

import com.openclassrooms.yourcaryourway.dto.request.NewChat;
import com.openclassrooms.yourcaryourway.dto.response.ChatResponse;
import com.openclassrooms.yourcaryourway.model.Chat;
import com.openclassrooms.yourcaryourway.service.ChatService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    ResponseEntity<ChatResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(chatService.getById(id));
    }

    @GetMapping()
    ResponseEntity<List<ChatResponse>> findAll() {
        return ResponseEntity.ok(chatService.findAll());
    }
}
