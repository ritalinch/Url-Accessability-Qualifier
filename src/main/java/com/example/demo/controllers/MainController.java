package com.example.demo.controllers;

import com.example.demo.dto.AccountDto;
import com.example.demo.dto.UrlDto;
import com.example.demo.dto.results.BadRequestResult;
import com.example.demo.dto.results.ResultDTO;
import com.example.demo.dto.results.SuccessResult;
import com.example.demo.services.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/account")
    public AccountDto account(OAuth2AuthenticationToken auth) {
        Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        return mainService.getAccount((String) attrs.get("email"));
    }

    @GetMapping("/add")
    public void account(HttpServletResponse response) throws IOException {
        response.sendRedirect("/");
    }

    @PostMapping("/add")
    public ResponseEntity<ResultDTO> addUrl(OAuth2AuthenticationToken auth,
                                            @RequestParam String url) {
        String email = getEmail(auth);

        mainService.addUrl(email, UrlDto.builder()
                .url(url)
                .build());
        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> get(OAuth2AuthenticationToken auth, Model model) {
        Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        model.addAllAttributes(mainService.getUrlsByEmail((String) attrs.get("email")));
        return new ResponseEntity<>(mainService.getUrlsByEmail((String) attrs.get("email")), HttpStatus.OK);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResultDTO> handleException() {
        return new ResponseEntity<>(new BadRequestResult(), HttpStatus.BAD_REQUEST);
    }

    private String getEmail(OAuth2AuthenticationToken auth) {
        return (String) auth.getPrincipal().getAttributes().get("email");
    }

}
