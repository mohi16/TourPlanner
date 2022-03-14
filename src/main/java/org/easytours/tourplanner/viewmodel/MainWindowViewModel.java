package org.easytours.tourplanner.viewmodel;

import org.easytours.tourplanner.controller.TourDetailsController;
import org.easytours.tourplanner.controller.TourOverviewController;

public class MainWindowViewModel {
    private SearchBarViewModel searchBarViewModel;
    private TourOverviewViewModel tourOverviewViewModel;
    private TourDetailsViewModel tourDetailsViewModel;


    public MainWindowViewModel(
            SearchBarViewModel searchBarViewModel,
            TourOverviewViewModel tourOverviewViewModel,
            TourDetailsViewModel tourDetailsViewModel
    ) {
        this.searchBarViewModel = searchBarViewModel;
        this.tourOverviewViewModel = tourOverviewViewModel;
        this.tourDetailsViewModel = tourDetailsViewModel;
    }
}
