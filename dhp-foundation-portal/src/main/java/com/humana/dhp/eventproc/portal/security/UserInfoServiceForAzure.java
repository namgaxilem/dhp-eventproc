package com.humana.dhp.eventproc.portal.security;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserInfoServiceForAzure implements UserInfoService {

    @Override
    public UserInfo getUserInfo(Principal principal) {
        if (principal == null) {
            return null;
        }

        Optional<OAuth2AuthenticationToken> principalOptional = Optional.of((OAuth2AuthenticationToken)principal);
        String userName = principalOptional.map(OAuth2AuthenticationToken::getName).orElse("");
        String email = principalOptional.map(OAuth2AuthenticationToken::getPrincipal)
                .map(OAuth2User::getAttributes)
                .map(attributes -> (String) attributes.get("preferred_username"))

                .orElse("");
        String name = principalOptional.map(OAuth2AuthenticationToken::getPrincipal)
                .map(OAuth2User::getAttributes)
                .map(attributes -> (String) attributes.get("name"))
                .orElse("");

        List<String> rolesFromJWT = principalOptional
                .map(OAuth2AuthenticationToken::getPrincipal)
                .map(OAuth2User::getAttributes)
                .map(attributes -> (List<String>) attributes.getOrDefault("roles", new ArrayList<String>()))
                .orElse(null);

        if (rolesFromJWT == null || rolesFromJWT.isEmpty()) {
            return new UserInfo(userName, email, name, new HashSet<>());
        }

        return new UserInfo(userName, email, name, new HashSet<>(rolesFromJWT));
    }
}
