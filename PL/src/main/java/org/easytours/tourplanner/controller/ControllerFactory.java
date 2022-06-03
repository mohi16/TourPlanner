package org.easytours.tourplanner.controller;

import org.easytours.tourplanner.dialog.AddTourDialogHandler;
import org.easytours.tourplanner.viewmodel.MainWindowViewModel;
import org.easytours.tourplanner.viewmodel.SearchBarViewModel;
import org.easytours.tourplanner.viewmodel.TourDetailsViewModel;
import org.easytours.tourplanner.viewmodel.TourOverviewViewModel;
import org.easytours.tourplanner.viewmodel.AddTourViewModel;

public class ControllerFactory {
    // Singleton
    private static final ControllerFactory INSTANCE = new ControllerFactory();

    // all ViewModels
    private final MainWindowViewModel mainWindowViewModel;
    private final SearchBarViewModel searchBarViewModel;
    private final TourOverviewViewModel tourOverviewViewModel;
    private final TourDetailsViewModel tourDetailsViewModel;
    private final AddTourViewModel addTourViewModel;
    private final TourDetailsController tourDetailsController;
    private final AddTourDialogHandler addTourDialogHandler;

    private ControllerFactory() {
        searchBarViewModel = new SearchBarViewModel();
        tourOverviewViewModel = new TourOverviewViewModel();
        tourDetailsViewModel = new TourDetailsViewModel();
        addTourViewModel = new AddTourViewModel();
        tourDetailsController = new TourDetailsController(tourDetailsViewModel);
        mainWindowViewModel = new MainWindowViewModel(
                searchBarViewModel, tourOverviewViewModel, tourDetailsViewModel);

        addTourDialogHandler = new AddTourDialogHandler();
    }

    // Get Singleton instance
    public static ControllerFactory getInstance() {
        return INSTANCE;
    }

    // create controller object
    public Object create(Class<?> controllerClass) {
        if (MainWindowController.class == controllerClass) {
            return new MainWindowController(mainWindowViewModel);
        } else if (SearchBarController.class == controllerClass) {
            return new SearchBarController(searchBarViewModel);
        } else if (TourOverviewController.class == controllerClass) {
            return new TourOverviewController(tourOverviewViewModel, addTourDialogHandler);
        } else if (TourDetailsController.class == controllerClass) {
            return tourDetailsController;
        } else if (AddTourController.class == controllerClass) {
            return new AddTourController(addTourViewModel);
        } else {
            throw new IllegalArgumentException("no controller class: " + controllerClass);
        }
    }
}
