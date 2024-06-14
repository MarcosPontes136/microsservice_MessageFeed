package com.microsservice_feedMessage.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microsservice_feedMessage.models.MessageModel;

public interface MessageRepository extends JpaRepository<MessageModel, UUID> {
}
