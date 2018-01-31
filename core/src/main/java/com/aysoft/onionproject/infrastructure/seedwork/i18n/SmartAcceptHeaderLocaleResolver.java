package com.aysoft.onionproject.infrastructure.seedwork.i18n;

import org.springframework.web.servlet.LocaleResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Locale;
import java.util.regex.Pattern;

public class SmartAcceptHeaderLocaleResolver implements LocaleResolver {
    private static final Pattern LOCALE_PATTERN = Pattern.compile("([a-z]{2}_[A-Z]{2}$)");

    public SmartAcceptHeaderLocaleResolver() {}

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String header = request.getHeader("Accept-Language");
        if (null != header) {
            Locale locale = findSupportedLocale(request);
            if (null != locale) {
                return new Locale(locale.getLanguage());
            }
            if (LOCALE_PATTERN.matcher(header).matches()) {
                String language = header.substring(0 , 2);
                if (LocaleContex.isSupported(language)) {
                    return new Locale(language);
                }
            }
        }
        return LocaleContex.DEFAULT;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        //Do nothing !!!
    }

    //<editor-fold desc="Support methods">
    private Locale findSupportedLocale(HttpServletRequest request) {
        Locale locale = null;
        Enumeration<Locale> locales = request.getLocales();
        while (locales.hasMoreElements() && !LocaleContex.isSupported(locale)) {
            locale = locales.nextElement();
        }
        return locale;
    }
    //</editor-fold>
}
