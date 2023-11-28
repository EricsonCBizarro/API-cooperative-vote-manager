package com.cooperativevote.cooperativevotemanager.service.external;

import com.cooperativevote.cooperativevotemanager.enums.UserStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private static final String USER_INFO_URL = "https://user-info.herokuapp.com/users/";

    private final RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean canUserVote(String document) {
        String url = USER_INFO_URL + document;
        UserInfoResponse response = restTemplate.getForObject(url, UserInfoResponse.class);
        return response != null && UserStatus.ABLE_TO_VOTE.equals(response.getStatus());
    }
}
