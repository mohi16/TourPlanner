package org.easytours.tourplanner.utils;

import org.easytours.tourplanner.App;

public enum ChildFriendliness {
    NONE("CF_NA"),
    VERY_FRIENDLY("CF_VeryFriendly"),
    FRIENDLY("CF_Friendly"),
    AVERAGE("CF_Average"),
    NOT_FRIENDLY("CF_NotFriendly"),
    BADASS("CF_Badass");

    private String text;

    private ChildFriendliness(String text) {
        this.text = text;
    }

    public String getText() {
        return App.getResourceBundle().getString(text);
    }
}
