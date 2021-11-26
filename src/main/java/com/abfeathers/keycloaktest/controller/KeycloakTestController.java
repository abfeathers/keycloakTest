package com.abfeathers.keycloaktest.controller;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("")
public class KeycloakTestController {

    @GetMapping("/test")
    public String test(){
        return "success";
    }

    @GetMapping("/articles")
    public String search(Principal principal) {
        if (principal instanceof KeycloakPrincipal) {
            AccessToken accessToken = ((KeycloakPrincipal) principal).getKeycloakSecurityContext().getToken();
            String preferredUsername = accessToken.getPreferredUsername();
            AccessToken.Access realmAccess = accessToken.getRealmAccess();
            Set<String> roles = realmAccess.getRoles();
            log.info("当前登录用户：{}, 角色：{}", preferredUsername, roles);
        }
        return "xxxx";
    }
}
