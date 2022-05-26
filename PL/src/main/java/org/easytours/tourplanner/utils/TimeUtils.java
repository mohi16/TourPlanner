package org.easytours.tourplanner.utils;public class TimeUtils {
    public static long constructTime(int hours, int minutes, int seconds) {
        if (0 > hours || 0 > minutes || 0 > seconds || 60 <= minutes || 60 <= seconds) {
            throw new IllegalArgumentException("illegal time");
        }

        return (long)3600 * hours + (long)60 * minutes + (long)seconds;
    }

    public static Triple<Integer, Integer, Integer> deconstructTime(long time) {
        int hours = 0;
        int mins = 0;
        int secs = 0;
        while (time >= 3600) {
            ++hours;
            time -= 3600;
        }
        while (time >= 60) {
            ++mins;
            time -= 60;
        }
        secs = Math.max(0, (int)time);

        return new Triple<>(hours, mins, secs);
    }
}
