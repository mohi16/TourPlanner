package org.easytours.tourplanner;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewModel {
    private final StringProperty searchString = new SimpleStringProperty();

    //methods
    public String getSearchString(){
        return searchString.get();
    }

    public final StringProperty searchStringProperty(){
        return searchString;
    }
}
