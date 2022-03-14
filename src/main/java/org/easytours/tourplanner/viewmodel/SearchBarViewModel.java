package org.easytours.tourplanner.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SearchBarViewModel {
    private final StringProperty searchString = new SimpleStringProperty();

    public String getSearchString(){
        return searchString.get();
    }

    public final StringProperty searchStringProperty(){
        return searchString;
    }
}
