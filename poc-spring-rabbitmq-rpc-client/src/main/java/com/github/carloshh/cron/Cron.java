package com.github.carloshh.cron;

import com.github.carloshh.service.ReverseStringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Cron {
    private static final Logger LOG = LoggerFactory.getLogger(Cron.class);

    private final ReverseStringService reverseStringService;

    public Cron(ReverseStringService reverseStringService) {
        this.reverseStringService = reverseStringService;
    }

    @Scheduled(fixedDelay = 3_000)
    public void run() {
        LOG.info("Running cron");

        var text = "Hello world";
        var reverseText = reverseStringService.reverse(text);

        LOG.info("Result reverse text: from={}, to={}", text, reverseText);
    }

}
