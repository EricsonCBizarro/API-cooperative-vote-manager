package com.cooperativevote.cooperativevotemanager.feign;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotingSessionRequest {
    private Long topicId;
    private Integer sessionTime;
}