package com.example.demo.services;

import com.example.demo.dto.AccountDto;
import com.example.demo.dto.UrlDto;
import com.example.demo.entities.Url;

import java.util.List;

public interface MainService {

    void addAccount(AccountDto dto);

    void addUrl(String email, UrlDto dto);

    List<UrlDto> getUrlsByEmail(String email);

    AccountDto getAccount(String email);

    List<Url> getAllUrls();

}
