package com.example.demo.schedulers;

import com.example.demo.services.CheckConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final CheckConnectionService checkConnectionService;

    @Scheduled(timeUnit = TimeUnit.SECONDS, fixedDelay = 3)
    public void doChecking() {
        checkConnectionService.checkAllUrls();
    }

}
