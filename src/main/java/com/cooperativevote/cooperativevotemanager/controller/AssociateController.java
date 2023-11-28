package com.cooperativevote.cooperativevotemanager.controller;

import com.cooperativevote.cooperativevotemanager.feign.AssociateRequest;
import com.cooperativevote.cooperativevotemanager.model.Associate;
import com.cooperativevote.cooperativevotemanager.service.AssociateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/associates")
public class AssociateController {

    private final AssociateService associateService;

    public AssociateController(AssociateService associateService) {
        this.associateService = associateService;
    }

    @PostMapping
    public ResponseEntity<Associate> createAssociate(@RequestBody AssociateRequest request) throws Exception {
        Associate associate = associateService.createAssociate(request.getDocument(), request.getName());
        return new ResponseEntity<>(associate, HttpStatus.CREATED);
    }
}
