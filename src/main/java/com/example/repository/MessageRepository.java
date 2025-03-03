package com.example.repository;

import com.example.entity.Message;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer>{

    /**
     * 
     * @param messageId
     * @return
     */
    Message findMessageByMessageId(Integer messageId);

    List<Message> findMessagesByPostedBy(Integer postedBy);
}
