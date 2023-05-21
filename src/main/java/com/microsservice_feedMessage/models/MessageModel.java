package com.microsservice_feedMessage.models;

import java.util.UUID;

import com.microsservice_feedMessage.enums.StatusMessage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class MessageModel {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID messageId;
	
	private String nome;
	
	@Column(columnDefinition = "TEXT")
	private String mensagem;
	
	private StatusMessage statusMessage;
		
}
