package com.cooperativevote.cooperativevotemanager.controller;

import com.cooperativevote.cooperativevotemanager.enums.VoteType;
import com.cooperativevote.cooperativevotemanager.feign.VotingSessionRequest;
import com.cooperativevote.cooperativevotemanager.model.VotingSession;
import com.cooperativevote.cooperativevotemanager.service.VotingSessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VotingSessionControllerTest {

    @Mock
    private VotingSessionService votingSessionService;

    private VotingSessionController votingSessionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        votingSessionController = new VotingSessionController(votingSessionService);
    }

    @Test
    public void testCreateVotingSession() throws Exception {
        VotingSessionRequest request = new VotingSessionRequest();
        request.setTopicId(1L);
        request.setSessionTime(30);

        VotingSession createdSession = new VotingSession();

        when(votingSessionService.createVotingSession(request.getTopicId(), request.getSessionTime()))
                .thenReturn(createdSession);

        ResponseEntity<VotingSession> responseEntity = votingSessionController.createVotingSession(request);

        assertEquals(createdSession, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());

        verify(votingSessionService, times(1))
                .createVotingSession(request.getTopicId(), request.getSessionTime());
        verifyNoMoreInteractions(votingSessionService);
    }

    @Test
    public void testCountVotes() throws Exception {
        Long votingSessionId = 1L;
        Map<VoteType, Integer> voteCount = new HashMap<>();
        voteCount.put(VoteType.SIM, 10);
        voteCount.put(VoteType.NAO, 5);

        when(votingSessionService.countVotes(votingSessionId)).thenReturn(voteCount);

        ResponseEntity<Map<VoteType, Integer>> responseEntity = votingSessionController.countVotes(votingSessionId);

        assertEquals(voteCount, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());

        verify(votingSessionService, times(1)).countVotes(votingSessionId);
        verifyNoMoreInteractions(votingSessionService);
    }

    // Add similar tests for openVotingSession and closeVotingSession methods
}
