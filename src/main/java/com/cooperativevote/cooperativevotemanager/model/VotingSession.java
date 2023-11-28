package com.cooperativevote.cooperativevotemanager.model;

import com.cooperativevote.cooperativevotemanager.enums.StatusVotingSession;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class VotingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    private LocalDateTime openingTime;
    private LocalDateTime closingTime;

    @Enumerated(EnumType.STRING)
    private StatusVotingSession status;

    public VotingSession(Topic topic, int sessionTime) {
        this.topic = topic;        
        this.status = StatusVotingSession.ABERTA;
        this.openingTime = LocalDateTime.now();
        this.closingTime = this.openingTime.plusMinutes(sessionTime);
    }

    public void open(int sessionTime) {
        this.status = StatusVotingSession.ABERTA;
        this.openingTime = LocalDateTime.now();
        this.closingTime = this.openingTime.plusMinutes(sessionTime);
    }

    public void close() {
        this.status = StatusVotingSession.FECHADA;
        this.closingTime = LocalDateTime.now();
    }
}