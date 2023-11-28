package com.cooperativevote.cooperativevotemanager.utils;

import com.cooperativevote.cooperativevotemanager.enums.VoteType;
import com.cooperativevote.cooperativevotemanager.exception.BadRequestException;

public class VoteUtils {

    public static VoteType mapToVoteType(String vote) {
        if ("SIM".equalsIgnoreCase(vote)) {
            return VoteType.SIM;
        } else if ("NAO".equalsIgnoreCase(vote)) {
            return VoteType.NAO;
        } else {
            throw new BadRequestException("Tipo de voto inválido.");
        }
    }
}
