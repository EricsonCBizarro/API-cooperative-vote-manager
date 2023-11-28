package com.cooperativevote.cooperativevotemanager.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.cooperativevote.cooperativevotemanager.exception.BadRequestException;
import com.cooperativevote.cooperativevotemanager.model.Topic;
import com.cooperativevote.cooperativevotemanager.repository.TopicRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TopicServiceImplTest {

    @InjectMocks
    private TopicServiceImpl topicService;

    @Mock
    private TopicRepository topicRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateTopic() {
        // Simulate topic creation
        Topic createdTopic = new Topic("Test Topic");
        when(topicRepository.save(any(Topic.class))).thenReturn(createdTopic);

        try {
            Topic result = topicService.createTopic("Test Topic");
            assertEquals(createdTopic.getTitle(), result.getTitle());
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }

        verify(topicRepository, times(1)).save(any(Topic.class));
    }

    @Test
    public void testListTopics() {
        // Simulate retrieving topics from repository
        List<Topic> topics = new ArrayList<>();
        topics.add(new Topic("Topic 1"));
        topics.add(new Topic("Topic 2"));
        when(topicRepository.findAll()).thenReturn(topics);

        try {
            List<Topic> result = topicService.listTopics();
            assertEquals(topics.size(), result.size());
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }

        verify(topicRepository, times(1)).findAll();
    }

    @Test
    public void testGetTopicById() {
        Long topicId = 1L;
        Topic topic = new Topic("Test Topic");
        when(topicRepository.findById(topicId)).thenReturn(Optional.of(topic));

        try {
            Topic result = topicService.getTopicById(topicId);
            assertNotNull(result);
            assertEquals(topic.getTitle(), result.getTitle());
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }

        verify(topicRepository, times(1)).findById(topicId);
    }

    @Test
    public void testGetTopicByIdNotFound() {
        Long topicId = 1L;
        when(topicRepository.findById(topicId)).thenReturn(Optional.empty());

        assertThrows(BadRequestException.class, () -> {
            topicService.getTopicById(topicId);
        });

        verify(topicRepository, times(1)).findById(topicId);
    }

}

