package com.example.demo.config;

import com.example.demo.dto.AccountDto;
import com.example.demo.services.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthHandler implements AuthenticationSuccessHandler {

    private final MainService mainService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User user = token.getPrincipal();

        Map<String, Object> attributes = user.getAttributes();

        AccountDto accountDTO = AccountDto.builder()
                .email((String) attributes.get("email"))
                .name((String) attributes.get("name"))
                .build();

        mainService.addAccount(accountDTO);

        httpServletResponse.sendRedirect("/");
    }
}