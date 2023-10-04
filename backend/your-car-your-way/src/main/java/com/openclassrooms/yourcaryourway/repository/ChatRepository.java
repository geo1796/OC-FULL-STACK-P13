package com.openclassrooms.yourcaryourway.repository;

import com.openclassrooms.yourcaryourway.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    List<Chat> findAllByAuthorId(Integer authorId);
}
