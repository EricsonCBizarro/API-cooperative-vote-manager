package com.cooperativevote.cooperativevotemanager.service;

import com.cooperativevote.cooperativevotemanager.model.Associate;

public interface AssociateService {

    Associate createAssociate(String document, String name) throws Exception;

    Associate findById(Long associateId) throws Exception;
}
