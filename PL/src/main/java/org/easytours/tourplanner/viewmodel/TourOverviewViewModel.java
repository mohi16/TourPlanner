package org.easytours.tourplanner.viewmodel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class TourOverviewViewModel {
    private final ObservableList<String> tours = FXCollections.observableArrayList();
    
    public final ObservableList<String> toursListProperty(){
        return tours;
    }
}
