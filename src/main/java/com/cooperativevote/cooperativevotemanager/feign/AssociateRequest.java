package com.cooperativevote.cooperativevotemanager.feign;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssociateRequest {
    private String document;
    private String name;
}
