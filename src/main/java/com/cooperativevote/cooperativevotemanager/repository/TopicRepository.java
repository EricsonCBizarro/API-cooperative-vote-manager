package com.cooperativevote.cooperativevotemanager.repository;

import com.cooperativevote.cooperativevotemanager.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    
}