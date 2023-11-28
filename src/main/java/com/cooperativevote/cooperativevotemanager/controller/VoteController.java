package com.cooperativevote.cooperativevotemanager.controller;

import com.cooperativevote.cooperativevotemanager.feign.VoteRequest;
import com.cooperativevote.cooperativevotemanager.model.Vote;
import com.cooperativevote.cooperativevotemanager.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votes")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<Vote> registerVote(@RequestBody VoteRequest voteRequest) throws Exception {
        Vote newVote = voteService.registerVote(voteRequest.getSessionId(), voteRequest.getVoteType(), voteRequest.getAssociateId());
        return new ResponseEntity<>(newVote, HttpStatus.CREATED);
    }
}
