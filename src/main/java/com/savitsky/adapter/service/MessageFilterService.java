package com.savitsky.adapter.service;

import com.savitsky.adapter.service.request.FillMessageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class MessageFilterService {

    private static final String RUSSIAN_LANGUAGE_CODE = "ru";
    public static final Predicate<FillMessageRequest> NOT_BLANK_MESSAGE_PREDICATE = message -> !message.message().isBlank();
    public static final Predicate<FillMessageRequest> ONLY_RUSSIAN_PREDICATE = message -> RUSSIAN_LANGUAGE_CODE.equals(message.languageCode());

    public List<FillMessageRequest> filter(List<FillMessageRequest> messages) {
        return messages.stream()
                .filter(NOT_BLANK_MESSAGE_PREDICATE)
                .filter(ONLY_RUSSIAN_PREDICATE)
                .toList();
    }
}
