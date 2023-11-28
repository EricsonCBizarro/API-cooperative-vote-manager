package com.cooperativevote.cooperativevotemanager.service.impl;

import com.cooperativevote.cooperativevotemanager.exception.BadRequestException;
import com.cooperativevote.cooperativevotemanager.exception.UnauthorizedException;
import com.cooperativevote.cooperativevotemanager.model.Associate;
import com.cooperativevote.cooperativevotemanager.repository.AssociateRepository;
import com.cooperativevote.cooperativevotemanager.service.AssociateService;
import com.cooperativevote.cooperativevotemanager.service.external.UserService;

import org.springframework.stereotype.Service;

@Service
public class AssociateServiceImpl implements AssociateService {

    private final AssociateRepository associateRepository;
    private final UserService userService;
    private final Boolean documentValidation = false;

    public AssociateServiceImpl(AssociateRepository associateRepository, UserService userService) {
        this.associateRepository = associateRepository;
        this.userService = userService; 
    }

    @Override
    public Associate createAssociate(String document, String name) throws Exception {
        
        // TODO disable document validation 
        if (this.documentValidation) {
            if (!userService.canUserVote(document)) {
                throw new UnauthorizedException("Documento invalido.");
            }
        }

        if (hasAssociateDocument(document)) {
            throw new BadRequestException("Associado com o documento cadastrado.");
        }

        Associate associate = new Associate(document, name);
        return associateRepository.save(associate);
    }

    @Override
    public Associate findById(Long associateId) throws Exception {
        return associateRepository.findById(associateId)
                .orElseThrow(() -> new BadRequestException("Associado não encontrado ID: " + associateId));
    }

    private boolean hasAssociateDocument(String document) {
        return associateRepository.existsByDocument(document);
    }
}
