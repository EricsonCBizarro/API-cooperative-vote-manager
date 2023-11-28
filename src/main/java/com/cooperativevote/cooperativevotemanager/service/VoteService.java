package com.cooperativevote.cooperativevotemanager.service;

import com.cooperativevote.cooperativevotemanager.model.Vote;

public interface VoteService {

    Vote registerVote(Long sessionId, String vote, Long AssociateId) throws Exception;

}
