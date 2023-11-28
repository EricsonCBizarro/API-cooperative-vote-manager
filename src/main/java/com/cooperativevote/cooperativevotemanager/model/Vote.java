package com.cooperativevote.cooperativevotemanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.cooperativevote.cooperativevotemanager.enums.VoteType;

@Data
@NoArgsConstructor
@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "voting_session_id")
    private VotingSession votingSession;
    
    @ManyToOne
    @JoinColumn(name = "associate_id")
    private Associate associate;

    @Enumerated(EnumType.STRING)
    private VoteType voteType;

    public Vote(VotingSession votingSession, Associate associate, VoteType voteType) {
        this.votingSession = votingSession;
        this.associate = associate;
        this.voteType = voteType;
    }
}

