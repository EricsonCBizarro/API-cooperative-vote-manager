package com.cooperativevote.cooperativevotemanager.repository;

import com.cooperativevote.cooperativevotemanager.model.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingSessionRepository extends JpaRepository<VotingSession, Long> {
}
