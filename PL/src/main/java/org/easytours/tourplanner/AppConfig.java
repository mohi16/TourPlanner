package org.easytours.tourplanner;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AppConfig {
    public static final Locale LOCALE = Locale.getDefault();

    public static final Locale getLocale() {
        return LOCALE;
    }

    public static final DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("HH:mm:ss");
    }
}
