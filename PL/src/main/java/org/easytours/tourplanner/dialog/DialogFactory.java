package org.easytours.tourplanner.dialog;

import javafx.stage.Stage;

public class DialogFactory {
    //Singleton
    private static final DialogFactory INSTANCE = new DialogFactory();

    private DialogFactory() {
        //TODO
    }

    public Stage create(Class<?> dialogClass) {
        return null;
    }

    public static DialogFactory getInstance() {
        return INSTANCE;
    }
}
