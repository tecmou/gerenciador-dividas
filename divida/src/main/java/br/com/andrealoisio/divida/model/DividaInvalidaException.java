package br.com.andrealoisio.divida.model;

import br.com.andrealoisio.divida.utils.LocalizedException;

import java.util.Locale;

public class DividaInvalidaException extends LocalizedException {

    public DividaInvalidaException(String messageKey) {
        super(messageKey);
    }

    public DividaInvalidaException(String messageKey, Locale locale) {
        super(messageKey, locale);
    }
}
