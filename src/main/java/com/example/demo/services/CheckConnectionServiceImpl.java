package com.example.demo.services;

import com.example.demo.dto.UrlDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class CheckConnectionServiceImpl implements CheckConnectionService {

    private final MainService mainService;
    private final EmailService emailService;

    @Override
    public void checkAllUrls() {
        mainService.getAllUrls()
                .stream()
                .filter(u -> !testUrl(u.getUrl()))
                .forEach(u -> emailService.sendMessage(
                        UrlDto.builder()
                                .url(u.getUrl())
                                .build(),
                        u.getAccount().getEmail()));
    }

    private boolean testUrl(String url) {
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            return connection.getResponseCode() == 200;
        } catch (IOException e) {
            return false;
        }
    }

}
