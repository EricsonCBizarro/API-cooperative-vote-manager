package com.cooperativevote.cooperativevotemanager.controller;

import com.cooperativevote.cooperativevotemanager.feign.AssociateRequest;
import com.cooperativevote.cooperativevotemanager.model.Associate;
import com.cooperativevote.cooperativevotemanager.service.AssociateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AssociateControllerTest {

    @InjectMocks
    private AssociateController associateController;

    @Mock
    private AssociateService associateService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(associateController).build();
    }

    @Test
    public void testCreateAssociate() throws Exception {
        AssociateRequest request = new AssociateRequest();
        request.setDocument("76152888000");
        request.setName("Name Random 1");
        
        Associate associate = new Associate(request.getDocument(), request.getName());

        when(associateService.createAssociate(anyString(), anyString())).thenReturn(associate);

        mockMvc.perform(post("/associates")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"document\":\"76152888000\",\"name\":\"Name Random 1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.document").value("76152888000"))
                .andExpect(jsonPath("$.name").value("Name Random 1"));
    }
}
