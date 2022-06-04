package org.easytours.tourplanner.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import org.easytours.tourplanner.App;
import org.easytours.tourplanner.dialog.AddTourDialogHandler;

import org.easytours.tourplanner.dialog.DialogHandler;
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
        System.out.println("Add");

        Tour tour;
        try {
            tour = dialogHandler.createTour();
        } catch (Exception e) {
            DialogHandler.showAlert(App.getResourceBundle().getString("BadTour_NotValid"));
            return;
        }
        if (null != tour) {
            try {
                Wrapper<Boolean> badTour = new Wrapper<>(false);
                Thread th = new Thread(() -> {
                    try {
                        App.getBusinessLogic().addTour(tour);
                        System.out.println("hello test2");
                    } catch (IllegalStateException e) {
                        //ignore
                    } catch (IllegalArgumentException e) {
                        badTour.set(true);
                    } catch (Exception e) {
                        badTour.set(true);
                        e.printStackTrace();
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
            } catch (IndexOutOfBoundsException e) {
                DialogHandler.showAlert(App.getResourceBundle().getString("NoSelected_Msg"));
                return;
            }

            App.getBusinessLogic().deleteTour(tourname);
            tourOverviewViewModel.toursListProperty().remove(getSelectedTourIndex());
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
        System.out.println("Edit");

        String name = null;
        try {
            name = getSelectedTourName();
        } catch (IndexOutOfBoundsException e) {
            DialogHandler.showAlert(App.getResourceBundle().getString("NoSelected_Msg"));
            return;
        }
        int idx = getSelectedTourIndex();
        Tour tour = null;
        try {
            tour = dialogHandler.editTour(App.getBusinessLogic().getTour(name));
        } catch (Exception e) {
            e.printStackTrace();
            DialogHandler.showAlert(App.getResourceBundle().getString("BadTour_NotValid"));
            return;
        }

        if (null != tour) {
            try {
                App.getBusinessLogic().editTour(name, tour);
                tourOverviewViewModel.toursListProperty().set(idx, tour.getName());
            } catch (IllegalArgumentException e) {
                DialogHandler.showAlert(App.getResourceBundle().getString("BadTour_NotValid"));
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
                    //System.out.println("hello test2");
                } catch (IllegalStateException e) {
                    //ignore
                } catch (IllegalArgumentException e) {
                    badTour.set(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    badTour.set(true);
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
            }
            else {
                TourDetailsController tdc =
                        (TourDetailsController) ControllerFactory
                                .getInstance()
                                .create(TourDetailsController.class);

                //tdc.setImage(Base64.getDecoder().decode(tour.get().getImage()));
                tdc.loadTour(tour.get());
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

    @FXML
    public void initialize() {
        toursList.setItems(tourOverviewViewModel.toursListProperty());

        String[] tournames;
        try {
            tournames = App.getBusinessLogic().getTourNames();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (String jeffrey: tournames) {
            System.out.println(jeffrey);
            tourOverviewViewModel.toursListProperty().add(jeffrey);
        }
    }
}
