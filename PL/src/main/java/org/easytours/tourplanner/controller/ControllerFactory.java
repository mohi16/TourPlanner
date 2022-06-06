package org.easytours.tourplanner.controller;

import org.easytours.tourplanner.dialog.AddTourDialogHandler;
import org.easytours.tourplanner.dialog.AddTourLogDialogHandler;
import org.easytours.tourplanner.viewmodel.*;

public class ControllerFactory {
    // Singleton
    private static final ControllerFactory INSTANCE = new ControllerFactory();

    // all ViewModels
    private final MainWindowViewModel mainWindowViewModel;
    private final SearchBarViewModel searchBarViewModel;
    private final TourOverviewViewModel tourOverviewViewModel;
    private final TourDetailsViewModel tourDetailsViewModel;
    private final AddTourViewModel addTourViewModel;
    private final AddTourLogViewModel addTourLogViewModel;

    private final AddTourDialogHandler addTourDialogHandler;
    private final AddTourLogDialogHandler addTourLogDialogHandler;

    private final TourOverviewController tourOverviewController;
    private final TourDetailsController tourDetailsController;
    private final MainWindowController mainWindowController;
    private final SearchBarController searchBarController;
    private final AddTourController addTourController;
    private final AddTourLogController addTourLogController;

    private ControllerFactory() {
        searchBarViewModel = new SearchBarViewModel();
        tourOverviewViewModel = new TourOverviewViewModel();
        tourDetailsViewModel = new TourDetailsViewModel();
        addTourViewModel = new AddTourViewModel();
        addTourLogViewModel = new AddTourLogViewModel();
        mainWindowViewModel = new MainWindowViewModel(
                searchBarViewModel, tourOverviewViewModel, tourDetailsViewModel);

        addTourLogDialogHandler = new AddTourLogDialogHandler();
        addTourDialogHandler = new AddTourDialogHandler();

        tourDetailsController = new TourDetailsController(tourDetailsViewModel, addTourLogDialogHandler);
        tourOverviewController = new TourOverviewController(tourOverviewViewModel, addTourDialogHandler);
        mainWindowController = new MainWindowController(mainWindowViewModel);
        searchBarController = new SearchBarController(searchBarViewModel);
        addTourController = new AddTourController(addTourViewModel);
        addTourLogController = new AddTourLogController(addTourLogViewModel);

    }

    // Get Singleton instance
    public static ControllerFactory getInstance() {
        return INSTANCE;
    }

    // create controller object
    public Object create(Class<?> controllerClass) {
        if (MainWindowController.class == controllerClass) {
            return mainWindowController;
        } else if (SearchBarController.class == controllerClass) {
            return searchBarController;
        } else if (TourOverviewController.class == controllerClass) {
            return tourOverviewController;
        } else if (TourDetailsController.class == controllerClass) {
            return tourDetailsController;
        } else if (AddTourController.class == controllerClass) {
            return addTourController;
        } else if (AddTourLogController.class == controllerClass) {
            return addTourLogController;
        }
        else {
            throw new IllegalArgumentException("no controller class: " + controllerClass);
        }
    }
}
