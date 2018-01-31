package com.aysoft.onionproject.infrastructure.seedwork.i18n;

import com.aysoft.onionproject.infrastructure.seedwork.util.FileUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

public class MessageContex {
    public static MessageSource SOURCE;

    static {
        SOURCE = new ResourceBundleMessageSource();
        String[] paths = FileUtils.findFilesPaths(
                "classpath*:com/aysoft/onionproject/infrastructure/configuration/i18n/*.properties",
                "messages",
                ".properties",
                7,
                ".");
        ((ResourceBundleMessageSource)SOURCE).setBasenames(paths);
        ((ResourceBundleMessageSource)SOURCE).setDefaultEncoding("UTF-8");
    }

    static public String msg(String code) {
        return SOURCE.getMessage(code, null, LocaleContex.locale());
    }

    static public String msg(String code, Object... args) {
        return SOURCE.getMessage(code, 0 != args.length ? args : null, LocaleContex.locale());
    }

    private MessageContex() {
        throw new AssertionError("No 'MessageContex' instances for you!");
    }
}
