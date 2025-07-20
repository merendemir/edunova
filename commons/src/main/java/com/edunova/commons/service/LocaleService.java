package com.edunova.commons.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleService {

    public static final Locale DEFAULT_LOCALE = Locale.forLanguageTag("tr");

    @Autowired
    private MessageSource messageSource;

    private static MessageSource staticMessageSource;

    @PostConstruct
    private void init() {
        staticMessageSource = this.messageSource;
    }

    public static String getMessage(String code) {
        return staticMessageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    public static String getMessage(String code, Locale locale) {
        return staticMessageSource.getMessage(code, null, locale);
    }

    public static String getMessage(String code, String... params) {
        return staticMessageSource.getMessage(code, params, LocaleContextHolder.getLocale());
    }

}
