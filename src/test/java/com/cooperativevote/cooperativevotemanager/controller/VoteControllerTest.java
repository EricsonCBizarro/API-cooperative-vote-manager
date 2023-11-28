package com.cooperativevote.cooperativevotemanager.controller;

import com.cooperativevote.cooperativevotemanager.exception.BadRequestException;
import com.cooperativevote.cooperativevotemanager.feign.VoteRequest;
import com.cooperativevote.cooperativevotemanager.model.Vote;
import com.cooperativevote.cooperativevotemanager.service.VoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class VoteControllerTest {

    @Mock
    private VoteService voteService;

    private VoteController voteController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        voteController = new VoteController(voteService);
    }

    @Test
    public void testRegisterVote() throws Exception {
        VoteRequest voteRequest = new VoteRequest();
        voteRequest.setSessionId(1L);
        voteRequest.setVoteType("SIM");
        voteRequest.setAssociateId(2L);

        Vote registeredVote = new Vote();

        when(voteService.registerVote(voteRequest.getSessionId(), voteRequest.getVoteType(), voteRequest.getAssociateId()))
                .thenReturn(registeredVote);

        ResponseEntity<Vote> responseEntity = voteController.registerVote(voteRequest);

        assertEquals(registeredVote, responseEntity.getBody());
        assertEquals(201, responseEntity.getStatusCodeValue());

        verify(voteService, times(1))
                .registerVote(voteRequest.getSessionId(), voteRequest.getVoteType(), voteRequest.getAssociateId());
        verifyNoMoreInteractions(voteService);
    }

    @Test
    public void testRegisterVoteClosedSession() throws Exception {
        VoteRequest voteRequest = new VoteRequest();
        voteRequest.setSessionId(1L);
        voteRequest.setVoteType("SIM");
        voteRequest.setAssociateId(2L);

        when(voteService.registerVote(anyLong(), anyString(), anyLong()))
                .thenThrow(new BadRequestException("Sessão de votação está fechada."));

        try {
            voteController.registerVote(voteRequest);
            fail("Should have thrown BadRequestException");
        } catch (BadRequestException e) {
            assertEquals("Sess\u00E3o de vota\u00E7\u00E3o est\u00E1 fechada.", e.getMessage());
        }

        verify(voteService, times(1)).registerVote(anyLong(), anyString(), anyLong());
        verifyNoMoreInteractions(voteService);
    }

    @Test
    public void testRegisterVoteAlreadyVoted() throws Exception {
        VoteRequest voteRequest = new VoteRequest();
        voteRequest.setSessionId(1L);
        voteRequest.setVoteType("SIM");
        voteRequest.setAssociateId(2L);

        when(voteService.registerVote(anyLong(), anyString(), anyLong()))
                .thenThrow(new BadRequestException("Associado já votou nesta sessão."));

        try {
            voteController.registerVote(voteRequest);
            fail("Should have thrown BadRequestException");
        } catch (BadRequestException e) {
            assertEquals("Associado já votou nesta sessão.", e.getMessage());
        }

        verify(voteService, times(1)).registerVote(anyLong(), anyString(), anyLong());
        verifyNoMoreInteractions(voteService);
    }
}
