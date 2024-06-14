package com.microsservice_feedMessage.models;

import java.util.UUID;

import com.microsservice_feedMessage.enums.StatusMessage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "MENSAGEM")
public class MessageModel {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private UUID messageId;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "TEXT")
	private String mensagem;
	
	@Column(name = "STATUS")
	private StatusMessage statusMessage;
		
}
