package com.cooperativevote.cooperativevotemanager.controller;

import com.cooperativevote.cooperativevotemanager.enums.VoteType;
import com.cooperativevote.cooperativevotemanager.model.VotingSession;
import com.cooperativevote.cooperativevotemanager.service.VotingSessionService;
import com.cooperativevote.cooperativevotemanager.feign.VotingSessionRequest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voting-sessions")
public class VotingSessionController {

    private final VotingSessionService votingSessionService;

    public VotingSessionController(VotingSessionService votingSessionService) {
        this.votingSessionService = votingSessionService;
    }

    @PostMapping
    public ResponseEntity<VotingSession> createVotingSession(@RequestBody VotingSessionRequest request) throws Exception {
        VotingSession votingSession = votingSessionService.createVotingSession(request.getTopicId(), request.getSessionTime());
        return ResponseEntity.ok(votingSession);
    }
    
    @PutMapping("/{votingSessionId}/open")
    public ResponseEntity<VotingSession> openVotingSession(@PathVariable Long votingSessionId, @RequestParam Integer sessionTime) throws Exception {
        VotingSession openedSession = votingSessionService.openVotingSession(votingSessionId, sessionTime);
        return ResponseEntity.ok(openedSession);
    }

    @PutMapping("/{votingSessionId}/close")
    public ResponseEntity<VotingSession> closeVotingSession(@PathVariable Long votingSessionId) throws Exception {
        VotingSession closedSession = votingSessionService.closeVotingSession(votingSessionId);
        return ResponseEntity.ok(closedSession);
    }

    @GetMapping("/{votingSessionId}/vote-count")
    public ResponseEntity<Map<VoteType, Integer>> countVotes(@PathVariable Long votingSessionId) throws Exception {
        Map<VoteType, Integer> voteCount = votingSessionService.countVotes(votingSessionId);
        return ResponseEntity.ok(voteCount);
    }
}
