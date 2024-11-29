package com.microsservice_feedMessage.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.MessagingException;

import com.microsservice_feedMessage.enums.StatusMessage;
import com.microsservice_feedMessage.models.MessageModel;
import com.microsservice_feedMessage.repositories.MessageRepository;
import com.microsservice_feedMessage.validation.MessageValidator;

import io.netty.handler.codec.AsciiHeadersEncoder.NewlineType;

@SpringBootTest
public class messageServiceTests {
	
    @InjectMocks
    private MessageService messageService;

    @Mock
    private MessageRepository messageRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testSaveMessageSuccess() throws UnsupportedEncodingException, MessagingException {
        MessageModel mockMessage = new MessageModel();
        mockMessage.setMessageId(UUID.randomUUID());
        mockMessage.setNome("John Doe");
        mockMessage.setMensagem("Hello World");

        when(messageRepository.save(any(MessageModel.class))).thenReturn(mockMessage);

        MessageModel savedMessage = messageService.saveMessage(mockMessage);

        assertNotNull(savedMessage);
        assertEquals(StatusMessage.SUCCESS, savedMessage.getStatusMessage());
        assertEquals("John Doe", savedMessage.getNome());
        assertEquals("Hello World", savedMessage.getMensagem());

        verify(messageRepository, times(1)).save(mockMessage);
    }
    
    @Test
    public void testGetAllMessages() {
        MessageModel message1 = new MessageModel();
        message1.setMessageId(UUID.randomUUID());
        message1.setNome("John Doe");
        message1.setMensagem("Message 1");

        MessageModel message2 = new MessageModel();
        message2.setMessageId(UUID.randomUUID());
        message2.setNome("Jane Doe");
        message2.setMensagem("Message 2");

        List<MessageModel> mockMessages = Arrays.asList(message1, message2);

        when(messageRepository.findAll()).thenReturn(mockMessages);

        List<MessageModel> messages = messageService.getAllMessages();

        assertNotNull(messages);
        assertEquals(2, messages.size());
        assertEquals("John Doe", messages.get(0).getNome());
        assertEquals("Jane Doe", messages.get(1).getNome());

        verify(messageRepository, times(1)).findAll();
    }
    
    
}
