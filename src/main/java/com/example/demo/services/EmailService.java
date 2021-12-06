package com.example.demo.services;

import com.example.demo.dto.UrlDto;

public interface EmailService {

    void sendMessage(UrlDto dto, String email);

}
