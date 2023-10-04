package com.openclassrooms.yourcaryourway.repository;

import com.openclassrooms.yourcaryourway.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllByChatId(Integer chatId);
}
