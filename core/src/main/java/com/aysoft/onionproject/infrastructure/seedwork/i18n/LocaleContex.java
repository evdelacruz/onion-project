package com.aysoft.onionproject.infrastructure.seedwork.i18n;

import org.springframework.context.i18n.LocaleContextHolder;
import java.util.Locale;

public class LocaleContex {
    public static final Locale DEFAULT = Locale.ENGLISH;
    private static final String[] SUPPORTED_LANGUAGUES = new String[]{"es", "en"};

    static {
        LocaleContextHolder.setDefaultLocale(DEFAULT);
    }

    static public Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

    static public boolean isSupported(String language) {
        return null != language && (SUPPORTED_LANGUAGUES[0].equals(language) || SUPPORTED_LANGUAGUES[1].equals(language));
    }

    static public boolean isSupported(Locale locale) {
        return null != locale && isSupported(locale.getLanguage());
    }

    private LocaleContex() {
        throw new AssertionError("No 'LocaleContex' instances for you!");
    }
}
