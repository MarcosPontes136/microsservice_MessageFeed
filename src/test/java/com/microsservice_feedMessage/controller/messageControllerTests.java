package com.microsservice_feedMessage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsservice_feedMessage.enums.StatusMessage;
import com.microsservice_feedMessage.models.MessageModel;
import com.microsservice_feedMessage.service.MessageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class messageControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSendingMessage() throws Exception {
        // Mock data
        MessageModel mockMessage = new MessageModel();
        UUID mockId = UUID.randomUUID();
        StatusMessage status = StatusMessage.SUCCESS;
        
        mockMessage.setMessageId(mockId);
        mockMessage.setNome("John Doe");
        mockMessage.setMensagem("Test message");
        mockMessage.setStatusMessage(status);

        when(messageService.saveMessage(any(MessageModel.class))).thenReturn(mockMessage);

        // Perform POST request
        mockMvc.perform(post("/api/feeds")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockMessage)))
        		.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.messageId").value(mockId.toString()))
                .andExpect(jsonPath("$.nome").value("John Doe"))
                .andExpect(jsonPath("$.mensagem").value("Test message"))
                .andExpect(jsonPath("$.statusMessage").value("SUCCESS")); 

        verify(messageService, times(1)).saveMessage(any(MessageModel.class));
    }
    
    @Test
    public void testGetAllMessages() throws Exception {
        // Mock data
        UUID mockId = UUID.randomUUID();
        UUID mockId1 = UUID.randomUUID();
        StatusMessage status = StatusMessage.SUCCESS;

        MessageModel message1 = new MessageModel();
        message1.setMessageId(mockId);
        message1.setNome("John Doe");
        message1.setMensagem("First message");
        message1.setStatusMessage(status);

        MessageModel message2 = new MessageModel();
        message2.setMessageId(mockId1);
        message2.setNome("Silvia");
        message2.setMensagem("Second message");
        message2.setStatusMessage(status);

        List<MessageModel> mockMessages = Arrays.asList(message1, message2);

        when(messageService.getAllMessages()).thenReturn(mockMessages);

        // Perform GET request
        mockMvc.perform(get("/api/feed")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].messageId").value(mockId.toString()))
                .andExpect(jsonPath("$[0].nome").value("John Doe"))
                .andExpect(jsonPath("$[0].mensagem").value("First message"))
                .andExpect(jsonPath("$[0].statusMessage").value("SUCCESS"))
                .andExpect(jsonPath("$[1].messageId").value(mockId1.toString()))
                .andExpect(jsonPath("$[1].nome").value("Silvia"))
                .andExpect(jsonPath("$[1].mensagem").value("Second message"))
        		.andExpect(jsonPath("$[1].statusMessage").value("SUCCESS"));
        

        verify(messageService, times(1)).getAllMessages();
    }
    
}
