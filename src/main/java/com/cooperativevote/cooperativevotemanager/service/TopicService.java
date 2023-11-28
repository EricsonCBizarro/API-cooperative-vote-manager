package com.cooperativevote.cooperativevotemanager.service;

import com.cooperativevote.cooperativevotemanager.model.Topic;

import java.util.List;

public interface TopicService {
    Topic createTopic(String title) throws Exception;
    List<Topic> listTopics() throws Exception;
    Topic getTopicById(Long topicId) throws Exception;
}
