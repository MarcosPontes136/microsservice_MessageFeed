package com.microsservice_feedMessage.validation;

import com.microsservice_feedMessage.exceptions.InvalidMessageException;
import com.microsservice_feedMessage.models.MessageModel;

public class MessageValidator {
	
    public static void validate(MessageModel messageModel) {
        if (messageModel == null) {
            throw new InvalidMessageException("A mensagem não pode ser nula.");
        }

        validateNull(messageModel.getNome(), "nome");
        validateNull(messageModel.getMensagem(), "mensagem");
        
        validateLength(messageModel.getNome(), "nome");
        validateLength(messageModel.getMensagem(), "mensagem");
        
    }

    private static void validateNull(String message, String fieldName) {
        if (fieldName == null || message.trim().isEmpty()) {
            throw new InvalidMessageException("O campo '" +fieldName+ "' não pode estar vazio.");
        }
    }
    
    private static void validateLength(String message, String fieldName) {
        if (message.length() < 3 && "nome".equals(fieldName)) {
            throw new InvalidMessageException("O campo "+fieldName+" deve conter pelo menos 3 caracteres.");
            
        } else if (message.length() < 5 && "mensagem".equals(fieldName)) {
        	
        	throw new InvalidMessageException("O campo "+fieldName+" deve conter pelo menos 5 caracteres.");
		}
    }
}
