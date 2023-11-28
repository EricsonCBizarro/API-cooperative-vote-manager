package com.cooperativevote.cooperativevotemanager.service.impl;

import com.cooperativevote.cooperativevotemanager.enums.StatusVotingSession;
import com.cooperativevote.cooperativevotemanager.enums.VoteType;
import com.cooperativevote.cooperativevotemanager.exception.BadRequestException;
import com.cooperativevote.cooperativevotemanager.model.Topic;
import com.cooperativevote.cooperativevotemanager.model.VotingSession;
import com.cooperativevote.cooperativevotemanager.repository.VoteRepository;
import com.cooperativevote.cooperativevotemanager.repository.VotingSessionRepository;
import com.cooperativevote.cooperativevotemanager.service.TopicService;
import com.cooperativevote.cooperativevotemanager.service.VotingSessionService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class VotingSessionServiceImpl implements VotingSessionService {

    private final VotingSessionRepository votingSessionRepository;
    private final TopicService topicService;
    private final VoteRepository voteRepository;

    public VotingSessionServiceImpl(VotingSessionRepository votingSessionRepository, TopicService topicService, VoteRepository voteRepository) {
        this.votingSessionRepository = votingSessionRepository;
        this.topicService = topicService;
        this.voteRepository = voteRepository;
    }

     @Override
    public VotingSession createVotingSession(Long topicId, Integer sessionTime) throws Exception{
        Topic topic = topicService.getTopicById(topicId);
        
        if (sessionTime == null) {
            sessionTime = 1;
        }

        VotingSession votingSession = new VotingSession(topic, sessionTime);
        return votingSessionRepository.save(votingSession);
    }

    @Override
    public VotingSession openVotingSession(Long votingSessionId, Integer sessionTime) throws Exception {
        VotingSession votingSession = getVotingSessionById(votingSessionId);

        if (votingSession.getStatus() == StatusVotingSession.ABERTA) {
            throw new BadRequestException("A sessão de votação já está aberta.");
        }

        if (sessionTime == null) {
            sessionTime = 1;
        }

        votingSession.open(sessionTime);
        return votingSessionRepository.save(votingSession);
    }

    @Override
    public VotingSession closeVotingSession(Long votingSessionId) throws Exception {
        VotingSession votingSession = getVotingSessionById(votingSessionId);

        if (votingSession.getStatus() == StatusVotingSession.FECHADA) {
            throw new BadRequestException("A sessão de votação não pode ser fechada.");
        }

        votingSession.close();
        return votingSessionRepository.save(votingSession);
    }

    @Override
    public Map<VoteType, Integer> countVotes(Long votingSessionId) throws Exception {
        VotingSession votingSession = getVotingSessionById(votingSessionId);

        int voteCountYes = voteRepository.findByVotingSessionIdAndVoteType(votingSession.getId(), VoteType.SIM).size();
        int voteCountNo = voteRepository.findByVotingSessionIdAndVoteType(votingSession.getId(), VoteType.NAO).size();

        Map<VoteType, Integer> voteCounts = new HashMap<>();
        voteCounts.put(VoteType.SIM, voteCountYes);
        voteCounts.put(VoteType.NAO, voteCountNo);
    
        return voteCounts;
    }
    
    @Override
    public VotingSession findById(Long votingSessionId) throws Exception {
        return getVotingSessionById(votingSessionId);
    }

    private VotingSession getVotingSessionById(Long votingSessionId) throws Exception {
        return votingSessionRepository.findById(votingSessionId)
                .orElseThrow(() -> new BadRequestException("Sessão de votação não encontrada"));
    }
}
