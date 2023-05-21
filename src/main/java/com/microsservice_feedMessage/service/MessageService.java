package com.microsservice_feedMessage.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsservice_feedMessage.dto.MessageDTO;
import com.microsservice_feedMessage.enums.StatusMessage;
import com.microsservice_feedMessage.models.MessageModel;
import com.microsservice_feedMessage.repositories.MessageRepository;
import com.rabbitmq.client.AMQP.Basic.Get;

import jakarta.mail.MessagingException;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
    
	public MessageModel saveMessage(MessageModel messageModel)throws UnsupportedEncodingException, MessagingException {
		
		try {
			
	        MessageDTO messageDTO = new MessageDTO();
	        messageDTO.setNome(messageModel.getNome());
	        messageDTO.setMensagem(messageModel.getMensagem());
	        messageModel.setStatusMessage(StatusMessage.SENT);
		} catch (Exception e) {
			messageModel.setStatusMessage(StatusMessage.ERROR);
		}
		
		return messageRepository.save(messageModel);
	}
	
	
    public List<MessageModel> getAllMessages() {
        return messageRepository.findAll();
    }
    
}
