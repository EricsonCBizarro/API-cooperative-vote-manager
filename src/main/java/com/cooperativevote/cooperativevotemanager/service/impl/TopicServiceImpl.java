package com.cooperativevote.cooperativevotemanager.service.impl;

import com.cooperativevote.cooperativevotemanager.exception.BadRequestException;
import com.cooperativevote.cooperativevotemanager.model.Topic;
import com.cooperativevote.cooperativevotemanager.repository.TopicRepository;
import com.cooperativevote.cooperativevotemanager.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public Topic createTopic(String title) throws Exception {
        Topic topic = new Topic(title);
        return topicRepository.save(topic);
    }

    @Override
    public List<Topic> listTopics() throws Exception {
        return topicRepository.findAll();
    }

    @Override
    public Topic getTopicById(Long topicId) throws Exception {
        return topicRepository.findById(topicId)
                .orElseThrow(() -> new BadRequestException("Pauta não encontrada."));
    }
}
