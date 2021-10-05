package br.com.andrealoisio.divida.service;

import br.com.andrealoisio.divida.utils.LocalizedException;

import java.util.Locale;

public class DividaNotFoundException extends LocalizedException {
    public DividaNotFoundException(String messageKey) {
        super(messageKey);
    }

    public DividaNotFoundException(String messageKey, Locale locale) {
        super(messageKey, locale);
    }
}
