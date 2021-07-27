package com.github.carloshh.poc.service.impl;

import com.github.carloshh.poc.service.StringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StringServiceImpl implements StringService {
    private static final Logger LOG = LoggerFactory.getLogger(StringServiceImpl.class);

    @Override
    public String reverse(String text) {
        var stringBuilder = new StringBuilder(text);
        var stringBuilderReverse = stringBuilder.reverse();
        var result = stringBuilderReverse.toString();

        LOG.info("Reversing string from={}, to={}", text, result);
        return result;
    }

    @Override
    public String uppercase(String text) {
        var result = text.toUpperCase();

        LOG.info("Uppercase string from={}, to={}", text, result);
        return result;
    }

}
