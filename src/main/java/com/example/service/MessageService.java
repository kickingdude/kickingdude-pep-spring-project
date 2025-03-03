package com.example.service;

import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MessageService {
    MessageRepository messageRepository;
    AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message addMessage(Message message){
        if (message.getMessageText() != "" && message.getMessageText().length() <= 255){
            if (accountRepository.findAccountByAccountId(message.getPostedBy()) != null){
                return messageRepository.save(message);
            }
        }
        return null;
    }

    public List<Message> getMessage(){
        return messageRepository.findAll();
    }

    public Message getMessagebyId(int messageId){
        return messageRepository.findMessageByMessageId(messageId);
    }

    public Message deleteMessagebyId(int messageId){
        Message message = getMessagebyId(messageId);
        if (message != null){
            messageRepository.delete(message);
        }
        return message;
    }

    public Message updateMessagebyId(String messageText, int messageId){
        if (messageText.isBlank() || messageText.length() > 255){
            return null;
        }
        else{
            System.out.println(messageText);
            Message message = getMessagebyId(messageId);
            if (message != null){
                message.setMessageText(messageText);
                messageRepository.save(message);
                return message;
            }
        }
        return null;
    }

    public List<Message> getMessagesbyPostedBy(int postedBy){
        return messageRepository.findMessagesByPostedBy(postedBy);
    }
}
