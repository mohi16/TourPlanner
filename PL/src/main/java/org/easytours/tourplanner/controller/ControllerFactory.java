package org.easytours.tourplanner.controller;

import org.easytours.tourplanner.viewmodel.MainWindowViewModel;
import org.easytours.tourplanner.viewmodel.SearchBarViewModel;
import org.easytours.tourplanner.viewmodel.TourDetailsViewModel;
import org.easytours.tourplanner.viewmodel.TourOverviewViewModel;

public class ControllerFactory {
    private static final ControllerFactory INSTANCE = new ControllerFactory();

    private final MainWindowViewModel mainWindowViewModel;
    private final SearchBarViewModel searchBarViewModel;
    private final TourOverviewViewModel tourOverviewViewModel;
    private final TourDetailsViewModel tourDetailsViewModel;

    private ControllerFactory() {
        searchBarViewModel = new SearchBarViewModel();
        tourOverviewViewModel = new TourOverviewViewModel();
        tourDetailsViewModel = new TourDetailsViewModel();
        mainWindowViewModel = new MainWindowViewModel(
                searchBarViewModel, tourOverviewViewModel, tourDetailsViewModel);
    }

    public static ControllerFactory getInstance() {
        return INSTANCE;
    }

    public Object create(Class<?> controllerClass) {
        if (MainWindowController.class == controllerClass) {
            return new MainWindowController(mainWindowViewModel);
        }
        if (SearchBarController.class == controllerClass) {
            return new SearchBarController(searchBarViewModel);
        } else if (TourOverviewController.class == controllerClass) {
            return new TourOverviewController(tourOverviewViewModel);
        } else if (TourDetailsController.class == controllerClass) {
            return new TourDetailsController(tourDetailsViewModel);
        } else {
            throw new IllegalArgumentException("no controller class: " + controllerClass);
        }
    }
}
