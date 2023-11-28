package com.cooperativevote.cooperativevotemanager.controller;

import com.cooperativevote.cooperativevotemanager.feign.TopicRequest;
import com.cooperativevote.cooperativevotemanager.model.Topic;
import com.cooperativevote.cooperativevotemanager.service.TopicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TopicControllerTest {

    @Mock
    private TopicService topicService;

    private TopicController topicController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        topicController = new TopicController(topicService);
    }

    @Test
    public void testListTopics() throws Exception {
        List<Topic> expectedTopics = new ArrayList<>();
        expectedTopics.add(new Topic("Topic 1"));

        when(topicService.listTopics()).thenReturn(expectedTopics);

        ResponseEntity<List<Topic>> responseEntity = topicController.listTopics();

        assertEquals(expectedTopics, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());

        verify(topicService, times(1)).listTopics();
        verifyNoMoreInteractions(topicService);
    }

    @Test
    public void testCreateTopic() throws Exception {
        TopicRequest topicRequest = new TopicRequest();
        topicRequest.setTitle("New Topic");

        Topic createdTopic = new Topic("New Topic");

        when(topicService.createTopic(topicRequest.getTitle())).thenReturn(createdTopic);

        ResponseEntity<Topic> responseEntity = topicController.createTopic(topicRequest);

        assertEquals(createdTopic, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());

        verify(topicService, times(1)).createTopic(topicRequest.getTitle());
        verifyNoMoreInteractions(topicService);
    }
}
