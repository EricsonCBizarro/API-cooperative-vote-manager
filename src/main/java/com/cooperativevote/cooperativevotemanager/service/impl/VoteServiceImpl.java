package com.cooperativevote.cooperativevotemanager.service.impl;

import com.cooperativevote.cooperativevotemanager.enums.StatusVotingSession;
import com.cooperativevote.cooperativevotemanager.enums.VoteType;
import com.cooperativevote.cooperativevotemanager.exception.BadRequestException;
import com.cooperativevote.cooperativevotemanager.exception.ConflictException;
import com.cooperativevote.cooperativevotemanager.exception.ForbiddenException;
import com.cooperativevote.cooperativevotemanager.exception.UnprocessableEntityException;
import com.cooperativevote.cooperativevotemanager.model.Associate;
import com.cooperativevote.cooperativevotemanager.model.Vote;
import com.cooperativevote.cooperativevotemanager.model.VotingSession;
import com.cooperativevote.cooperativevotemanager.repository.VoteRepository;
import com.cooperativevote.cooperativevotemanager.service.AssociateService;
import com.cooperativevote.cooperativevotemanager.service.VoteService;
import com.cooperativevote.cooperativevotemanager.service.VotingSessionService;
import com.cooperativevote.cooperativevotemanager.utils.VoteUtils;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {

    private final VotingSessionService votingSessionService;
    private final VoteRepository voteRepository;
    private final AssociateService associateService; 

    public VoteServiceImpl(VotingSessionService votingSessionService, VoteRepository voteRepository, AssociateService associateService) {
        this.votingSessionService = votingSessionService;
        this.voteRepository = voteRepository;
        this.associateService = associateService;
    }

    @Override
    public Vote registerVote(Long sessionId, String vote, Long associateId) throws Exception {
        VotingSession session = votingSessionService.findById(sessionId);
        Associate associate = associateService.findById(associateId);

        if (session.getStatus() != StatusVotingSession.ABERTA) {
            throw new ForbiddenException("A sessão de votação está fechada.");
        }

        if (hasVoted(sessionId, associateId)) {
            throw new ConflictException("Associado já votou nesta sessão.");
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        if (currentDateTime.isAfter(session.getClosingTime())) {
            // throw new BadRequestException("A sessão de votação está encerrada.");
            throw new UnprocessableEntityException("A sessão de votação está encerrada.");
        }

        VoteType voteType = VoteUtils.mapToVoteType(vote);

        Vote newVote = new Vote(session, associate, voteType);
        return voteRepository.save(newVote);
    }

    private boolean hasVoted(Long sessionId, Long associateId) {
        return voteRepository.existsByVotingSessionIdAndAssociateId(sessionId, associateId);
    }
}
