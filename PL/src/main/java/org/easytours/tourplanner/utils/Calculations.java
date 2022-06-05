package org.easytours.tourplanner.utils;

import org.easytours.tpmodel.TourLog;

public class Calculations {
    public static int computePopularity(TourLog[] logs) {
        return logs.length;
    }

    public static ChildFriendliness computeChildFriendliness(TourLog[] logs) {
        if (0 == logs.length) {
            return ChildFriendliness.NONE;
        }

        double value = 0.0;
        int logCounter = 0;
        for (TourLog l : logs) {
            if (l.getDifficulty() <= 2) {
                value += 1;
            }
            else if (l.getDifficulty() >= 4) {
                value -= 1;
            }

            if (l.getTotalTime() <= 3600) {
                value += 1;
            } else if (l.getTotalTime() >= 6200) {
                value -= 1;
            }

            ++logCounter;
        }

        value /= logCounter;

        if (0.75 <= value) {
            return ChildFriendliness.VERY_FRIENDLY;
        } else if (0.25 <= value) {
            return ChildFriendliness.FRIENDLY;
        } else if (-0.75 >= value) {
            return ChildFriendliness.BADASS;
        } else if (-0.25 >= value) {
            return ChildFriendliness.NOT_FRIENDLY;
        } else {
            return ChildFriendliness.AVERAGE;
        }
    }
}
