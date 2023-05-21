package com.microsservice_feedMessage.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MessageDTO {
	
	private Long messageId;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String mensagem;
	
	
	public Long messageId() {
		return messageId;
	}
}
