package com.cooperativevote.cooperativevotemanager.service.impl;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.cooperativevote.cooperativevotemanager.model.Associate;
import com.cooperativevote.cooperativevotemanager.repository.AssociateRepository;
import com.cooperativevote.cooperativevotemanager.service.external.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AssociateServiceImplTest {

    private AssociateRepository associateRepository;
    private UserService userService;
    private AssociateServiceImpl associateService;

    @BeforeEach
    public void setUp() {
        associateRepository = mock(AssociateRepository.class);
        userService = mock(UserService.class);
        associateService = new AssociateServiceImpl(associateRepository, userService);
    }

    @Test
    public void testCreateAssociate_ValidDocument() throws Exception {
        when(associateRepository.existsByDocument(anyString())).thenReturn(false);
        when(associateRepository.save(any(Associate.class))).thenReturn(new Associate());

        associateService.createAssociate("validDocument", "Ericson Bizarro");

        verify(associateRepository, times(1)).save(any(Associate.class));
    }

    @Test
    public void testCreateAssociate_DuplicateDocument() {
        when(associateRepository.existsByDocument(anyString())).thenReturn(true);

        try {
            associateService.createAssociate("duplicateDocument", "Ericson Bizarro");
        } catch (Exception e) {
            // Handle exception
        }

        verify(associateRepository, never()).save(any(Associate.class));
    }

    @Test
    public void testFindById_ValidId() throws Exception {
        when(associateRepository.findById(anyLong())).thenReturn(java.util.Optional.of(new Associate()));

        associateService.findById(1L);

        verify(associateRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testFindById_InvalidId() {
        when(associateRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());

        try {
            associateService.findById(2L);
        } catch (Exception e) {
            // Handle exception
        }

        verify(associateRepository, times(1)).findById(anyLong());
    }

}
