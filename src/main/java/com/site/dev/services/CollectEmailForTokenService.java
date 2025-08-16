package com.site.dev.services;

import com.site.dev.core.applications.usecases.users.FindUsersUsecases;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
@Service
public class CollectEmailForTokenService {

    private FindUsersUsecases findUsersUsecases;
    @Autowired
    public CollectEmailForTokenService(FindUsersUsecases findUsersUsecases) {
        this.findUsersUsecases = findUsersUsecases;

    }
    
    public String execute(HttpServletRequest servletRequest) throws JSONException {
        String authHeader = servletRequest.getHeader("Authorization");
        boolean apiKey = false;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            authHeader = servletRequest.getHeader("X-API-Key");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                apiKey = true;
            }
        }
        if (apiKey) {
            return findUsersUsecases.authApiKey(authHeader).getEmail();
        }

        String token = authHeader.substring(7);

        String[] chunks = token.split("\\.");
        String payload = new String(java.util.Base64.getDecoder().decode(chunks[1]));
        JSONObject jsonObject = new JSONObject(payload);
        String sub = jsonObject.getString("sub");

        return sub;
    }
}
