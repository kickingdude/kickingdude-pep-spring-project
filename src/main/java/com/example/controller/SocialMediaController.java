package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;
    
    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }
    @PostMapping("/register")
    public @ResponseBody ResponseEntity<Account> postRegister(@RequestBody Account account){
        Account returnedAccount = accountService.addAccount(account);
        if (returnedAccount != null){
            return ResponseEntity.status(200).body(returnedAccount);
        }
        else{
            return ResponseEntity.status(409).body(null);
        }
    }

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<Account> getLogin(@RequestBody Account account){
        Account loggedAccount = accountService.loginAccount(account);
        if (loggedAccount != null){
            return ResponseEntity.status(200).body(loggedAccount);
        }
        else{
            return ResponseEntity.status(401).body(null);
        }
    }

    @PostMapping("/messages")
    public @ResponseBody ResponseEntity<Message> postMessage(@RequestBody Message message){
        Message newMessage = messageService.addMessage(message);
        if (newMessage != null){
            return ResponseEntity.status(200).body(newMessage);
        }
        else{
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/messages")
    public @ResponseBody ResponseEntity<List<Message>> getMessage(){
        List<Message> messages = messageService.getMessage();
        return ResponseEntity.status(200).body(messages);
    }

    @GetMapping("/messages/{messageId}")
    public @ResponseBody ResponseEntity<Message> getMessagebyId(@PathVariable int messageId){
        Message message = messageService.getMessagebyId(messageId);
        return ResponseEntity.status(200).body(message);
    }

    @DeleteMapping("/messages/{messageId}")
    public @ResponseBody ResponseEntity<Integer> deleteMessagebyId(@PathVariable int messageId){
        Message message = messageService.deleteMessagebyId(messageId);
        if (message != null){
            return ResponseEntity.status(200).body(1);
        }
        else{
            return ResponseEntity.status(200).body(null);
        }
    }

    @PatchMapping(value = "/messages/{messageId}", consumes = "application/json")
    public @ResponseBody ResponseEntity<Integer> updateMessagebyId(@RequestBody Message messageText, @PathVariable int messageId){
        Message updatedMessage = messageService.updateMessagebyId(messageText.getMessageText(), messageId);
        if (updatedMessage != null){
            return ResponseEntity.status(200).body(1);
        }
        else{
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/accounts/{accountId}/messages")
    public @ResponseBody ResponseEntity<List<Message>> getMessagesbyPostedBy(@PathVariable int accountId){
        List<Message> messages = messageService.getMessagesbyPostedBy(accountId);
        return ResponseEntity.status(200).body(messages);
    }
}
