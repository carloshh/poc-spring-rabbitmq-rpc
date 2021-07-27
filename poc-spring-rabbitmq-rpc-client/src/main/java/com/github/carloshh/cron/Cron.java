package com.github.carloshh.cron;

import com.github.carloshh.service.StringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Cron {
    private static final Logger LOG = LoggerFactory.getLogger(Cron.class);

    private final StringService stringService;

    public Cron(StringService stringService) {
        this.stringService = stringService;
    }

    @Scheduled(fixedDelay = 3_000)
    public void run() {
        LOG.info("Running cron");

        var text = "Hello world";

        LOG.info("Result reverse text: from={}, to={}", text, stringService.reverse(text));
        LOG.info("Result uppercase text: from={}, to={}", text, stringService.uppercase(text));
    }

}
