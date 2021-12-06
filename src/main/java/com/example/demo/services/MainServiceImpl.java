package com.example.demo.services;

import com.example.demo.dto.AccountDto;
import com.example.demo.dto.UrlDto;
import com.example.demo.entities.Account;
import com.example.demo.entities.Url;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.repositories.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    private final AccountRepository accountRepository;
    private final UrlRepository urlRepository;

    @Transactional
    @Override
    public void addAccount(AccountDto accountDTO) {
        if (accountRepository.existsByEmail(accountDTO.getEmail()))
            return;
        Account account = Account.fromDto(accountDTO);
        accountRepository.save(account);
    }

    @Transactional
    @Override
    public void addUrl(String email, UrlDto urlDto) {
        Account account = accountRepository.findByEmail(email);
        account.addUrl(Url.fromDto(urlDto));
    }

    @Transactional(readOnly = true)
    @Override
    public List<UrlDto> getUrlsByEmail(String email) {
        List<UrlDto> result = new ArrayList<>();
        List<Url> urls = urlRepository.findByAccount_Email(email);

        urls.forEach((url) -> result.add(UrlDto.builder()
                .url(url.getUrl())
                .build()));
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Url> getAllUrls() {
        return urlRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public AccountDto getAccount(String email) {
        return accountRepository
                .findByEmail(email)
                .toDto();
    }

}
