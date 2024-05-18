package com.microsservice_feedMessage.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsservice_feedMessage.dto.MessageDTO;
import com.microsservice_feedMessage.enums.StatusMessage;
import com.microsservice_feedMessage.models.MessageModel;
import com.microsservice_feedMessage.repositories.MessageRepository;
import com.microsservico_email.Service.EmailService;
import com.rabbitmq.client.AMQP.Basic.Get;

import jakarta.mail.MessagingException;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    
	public MessageModel saveMessage(MessageModel messageModel)throws UnsupportedEncodingException, MessagingException {
		
		try {
			logger.info("Tentando montar nome do remetente {}", messageModel.getNome());
			logger.info("Tentando montar mensagem {}", messageModel.getMensagem());
	        MessageDTO messageDTO = new MessageDTO();
	        messageDTO.setNome(messageModel.getNome());
	        messageDTO.setMensagem(messageModel.getMensagem());
	        messageModel.setStatusMessage(StatusMessage.SENT);
	        logger.info("Mensagem montada com sucesso! {}", messageModel.getStatusMessage());
		} catch (Exception e) {
			messageModel.setStatusMessage(StatusMessage.ERROR);
			logger.info("Erro ao montar mensagem! {}", messageModel.getStatusMessage());
		}
		
		return messageRepository.save(messageModel);
	}
	
	
    public List<MessageModel> getAllMessages() {
        return messageRepository.findAll();
    }
    
}
