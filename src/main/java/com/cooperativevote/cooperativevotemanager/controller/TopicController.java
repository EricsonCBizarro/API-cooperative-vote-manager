package com.cooperativevote.cooperativevotemanager.controller;

import com.cooperativevote.cooperativevotemanager.feign.TopicRequest;
import com.cooperativevote.cooperativevotemanager.model.Topic;
import com.cooperativevote.cooperativevotemanager.service.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public ResponseEntity<List<Topic>> listTopics() throws Exception {
        List<Topic> topics = topicService.listTopics();
        return ResponseEntity.ok(topics);
    }

    @PostMapping
    public ResponseEntity<Topic> createTopic(@RequestBody TopicRequest request) throws Exception {
        Topic newTopic = topicService.createTopic(request.getTitle());
        return ResponseEntity.ok(newTopic);
    }
}
