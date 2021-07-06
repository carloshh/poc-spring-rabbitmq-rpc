package com.github.carloshh.poc.service.impl;

import com.github.carloshh.poc.service.ReverseStringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReverseStringServiceImpl implements ReverseStringService {
    private static final Logger LOG = LoggerFactory.getLogger(ReverseStringServiceImpl.class);

    @Override
    public String reverse(String text) {
        var stringBuilder = new StringBuilder(text);
        var stringBuilderReverse = stringBuilder.reverse();
        var result = stringBuilderReverse.toString();

        LOG.info("Reversing string from={}, to={}", text, result);
        return result;
    }

}
