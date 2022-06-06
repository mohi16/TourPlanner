package org.easytours.tourplanner.controller;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import org.easytours.tourplanner.App;
import org.easytours.tourplanner.dialog.AddTourDialogHandler;

import org.easytours.tourplanner.dialog.DialogHandler;
import org.easytours.tourplanner.logging.LogManager;
import org.easytours.tourplanner.utils.Wrapper;
import org.easytours.tourplanner.viewmodel.TourOverviewViewModel;
import org.easytours.tpmodel.Tour;



public class TourOverviewController {
    private final TourOverviewViewModel tourOverviewViewModel;
    private final AddTourDialogHandler dialogHandler;

    @FXML
    private Button addTourButton;

    @FXML
    private Button deleteTourButton;

    @FXML
    private Button editTourButton;

    @FXML
    private ListView<String> toursList;

    @FXML
    private ImageView tourImageView;

    public TourOverviewController(TourOverviewViewModel tourOverviewViewModel, AddTourDialogHandler dialogHandler) {
        this.tourOverviewViewModel = tourOverviewViewModel;
        this.dialogHandler = dialogHandler;

        /*tourOverviewViewModel.toursListProperty().add("Tour 1");
        tourOverviewViewModel.toursListProperty().add("Tour 2");
        tourOverviewViewModel.toursListProperty().add("Tour 3");*/
    }

    @FXML
    public void onAddTourButtonClick() {

        Tour tour;
        try {
            tour = dialogHandler.createTour();
        } catch (Exception e) {
            DialogHandler.showAlert(App.getResourceBundle().getString("BadTour_NotValid"));
            LogManager.getLogger().warn("Tour not valid");
            return;
        }
        if (null != tour) {
            try {
                Wrapper<Boolean> badTour = new Wrapper<>(false);
                Thread th = new Thread(() -> {
                    try {
                        App.getBusinessLogic().addTour(tour);
                        LogManager.getLogger().info("Adding Tour");
                    } catch (IllegalStateException e) {
                        //ignore
                    } catch (Exception e) {
                        badTour.set(true);
                        LogManager.getLogger().warn("Tour not valid");
                    }
                });
                th.start();
                Dialog<String> d = new Dialog<>();
                d.setContentText(App.getResourceBundle().getString("Msg_Wait"));
                d.show();
                th.join();
                // close waiting dialog
                d.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
                d.close();
                if (badTour.get()) {
                    DialogHandler.showAlert(App.getResourceBundle().getString("BadTour_NotValid"));

                }
                else{
                    tourOverviewViewModel.toursListProperty().add(tour.getName());
                    LogManager.getLogger().warn("Tour added");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void onDeleteTourButtonClick() {
        System.out.println("Delete");

        try {
            String tourname = null;
            try {
                tourname = getSelectedTourName();
                LogManager.getLogger().info("Deleting Tour");
            } catch (IndexOutOfBoundsException e) {
                DialogHandler.showAlert(App.getResourceBundle().getString("NoSelected_Msg"));
                LogManager.getLogger().warn("No Tour selected. Please select a valid Tour.");

                return;
            }

            App.getBusinessLogic().deleteTour(tourname);
            tourOverviewViewModel.toursListProperty().remove(getSelectedTourIndex());
            LogManager.getLogger().info("Tour removed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getSelectedTourIndex() {
        return toursList.getSelectionModel().getSelectedIndex();
    }

    public String getSelectedTourName() {
        return tourOverviewViewModel.toursListProperty().get(getSelectedTourIndex());
    }

    @FXML
    public void onEditTourButtonClick() {

        String name = null;
        try {
            name = getSelectedTourName();
        } catch (IndexOutOfBoundsException e) {
            DialogHandler.showAlert(App.getResourceBundle().getString("NoSelected_Msg"));
            LogManager.getLogger().warn("Not a valid Tour");

            return;
        }
        int idx = getSelectedTourIndex();
        Tour tour = null;
        try {
            tour = dialogHandler.editTour(App.getBusinessLogic().getTour(name));
            LogManager.getLogger().info("Editing tour");

        } catch (Exception e) {
            e.printStackTrace();
            DialogHandler.showAlert(App.getResourceBundle().getString("BadTour_NotValid"));
            LogManager.getLogger().warn("Tour not valid");
            return;
        }

        if (null != tour) {
            try {
                App.getBusinessLogic().editTour(name, tour);
                tourOverviewViewModel.toursListProperty().set(idx, tour.getName());
                LogManager.getLogger().info("Tour edited");
            } catch (IllegalArgumentException e) {
                DialogHandler.showAlert(App.getResourceBundle().getString("BadTour_NotValid"));
                LogManager.getLogger().warn("Tour not valid");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void onListViewClicked(){
        try {
            Wrapper<Boolean> badTour = new Wrapper<>(false);
            Wrapper<Tour> tour = new Wrapper<>();
            Thread th = new Thread(() -> {
                try {
                    tour.set(App.getBusinessLogic().getTourWithImage(getSelectedTourName()));
                    LogManager.getLogger().warn("Displaying Tour");

                } catch (IllegalStateException e) {
                    //ignore
                } catch (Exception e) {
                    badTour.set(true);
                    LogManager.getLogger().warn("Tour not valid");
                }
            });
            th.start();
            Dialog<String> d = new Dialog<>();


            d.setContentText(App.getResourceBundle().getString("Msg_Wait"));
            d.setHeaderText("header");
            d.show();
            th.join();

            // close waiting dialog
            d.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
            d.close();
            if (badTour.get()) {
                DialogHandler.showAlert(App.getResourceBundle().getString("Error_GetTour"));
                LogManager.getLogger().warn("Tour not valid");
            }
            else {
                TourDetailsController tdc =
                        (TourDetailsController) ControllerFactory
                                .getInstance()
                                .create(TourDetailsController.class);

                //tdc.setImage(Base64.getDecoder().decode(tour.get().getImage()));
                tdc.loadTour(tour.get());
                LogManager.getLogger().info("Tour displayed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*Tour tour = null;
        try {
            tour = App.getBusinessLogic().getTourWithImage(getSelectedTourName());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

       TourDetailsController tdc =
                (TourDetailsController) ControllerFactory
                        .getInstance()
                        .create(TourDetailsController.class);

        tdc.setImage(Base64.getDecoder().decode(tour.getImage()));*/

/*        InputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(tour.getImage()));
        tourImageView.setImage(new Image(inputStream));*/
        //System.out.println("hello");
    }

    public void loadTours(String filter) {
        tourOverviewViewModel.toursListProperty().clear();

        String[] tournames;
        try {
            tournames = App.getBusinessLogic().getTourNames(filter);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (String jeffrey: tournames) {
            System.out.println(jeffrey);
            tourOverviewViewModel.toursListProperty().add(jeffrey);
        }
        LogManager.getLogger().info("Tours loaded");

    }

    public void loadTours() {
        loadTours(null);
    }

    @FXML
    public void initialize() {
        toursList.setItems(tourOverviewViewModel.toursListProperty());
        loadTours();
    }
}
