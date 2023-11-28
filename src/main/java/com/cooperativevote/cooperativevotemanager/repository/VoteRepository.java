package com.cooperativevote.cooperativevotemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooperativevote.cooperativevotemanager.enums.VoteType;
import com.cooperativevote.cooperativevotemanager.model.Vote;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

     List<Vote> findByVotingSessionIdAndVoteType(Long votingSessionId, VoteType voteType);

     boolean existsByVotingSessionIdAndAssociateId(Long votingSessionId, Long associateId);
}
    