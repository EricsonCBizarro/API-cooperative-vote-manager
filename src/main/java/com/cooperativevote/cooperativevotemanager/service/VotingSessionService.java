package com.cooperativevote.cooperativevotemanager.service;

import java.util.Map;

import com.cooperativevote.cooperativevotemanager.enums.VoteType;
import com.cooperativevote.cooperativevotemanager.model.VotingSession;

public interface VotingSessionService {

    VotingSession createVotingSession(Long topicId, Integer sessionTime) throws Exception;

    VotingSession openVotingSession(Long votingSessionId, Integer sessionTime) throws Exception;

    VotingSession closeVotingSession(Long votingSessionId) throws Exception;

    Map<VoteType, Integer> countVotes(Long votingSessionId) throws Exception;

    VotingSession findById(Long votingSessionId) throws Exception;
}
