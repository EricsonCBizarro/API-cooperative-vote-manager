package com.cooperativevote.cooperativevotemanager.feign;

import lombok.Data;

@Data
public class VoteRequest {
    private Long sessionId;
    private String voteType;
    private Long associateId;
}
