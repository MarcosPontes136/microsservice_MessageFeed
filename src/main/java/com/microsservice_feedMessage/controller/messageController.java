package com.microsservice_feedMessage.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsservice_feedMessage.dto.MessageDTO;
import com.microsservice_feedMessage.models.MessageModel;
import com.microsservice_feedMessage.service.MessageService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class messageController {
	
	@Autowired
	private MessageService messageService;
		
    
	@PostMapping(value = "/feeds")
	public ResponseEntity<MessageModel> sendingMessage(@RequestBody MessageModel messageModel) throws UnsupportedEncodingException, MessagingException, jakarta.mail.MessagingException{
		
		MessageModel savedMessage = messageService.saveMessage(messageModel);

        return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
	}
	
    @GetMapping(value = "/feed")
    public ResponseEntity<List<MessageModel>> getAllMessages() {
        List<MessageModel> messages = messageService.getAllMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

}
