package com.example.backend.config;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;@Servic

public class CustomOAuth2UserSe


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {       

            

            System.out.println("OAuth2 login attributes: " + user.getAttrib

              return user;
    }
}

    
