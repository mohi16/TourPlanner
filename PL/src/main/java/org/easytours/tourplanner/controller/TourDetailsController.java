package org.easytours.tourplanner.controller;

import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.easytours.tourplanner.App;
import org.easytours.tourplanner.dialog.AddTourLogDialogHandler;
import org.easytours.tourplanner.dialog.DialogHandler;
import org.easytours.tourplanner.utils.Wrapper;
import org.easytours.tourplanner.viewmodel.TourDetailsViewModel;
import org.easytours.tpmodel.Tour;
import org.easytours.tpmodel.TourLog;

import java.io.*;
import java.util.Arrays;
import java.util.Base64;

public class TourDetailsController {
    private final TourDetailsViewModel tourDetailsViewModel;
    private final AddTourLogDialogHandler dialogHandler;

    @FXML
    private ImageView tourImageView;

    @FXML
    private TabPane tabPane;

    @FXML
    private TextField fromTextField;

    @FXML
    private TextField toTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField distanceTextField;

    @FXML
    private TextField routeInfoTextField;

    @FXML
    private TextField estTimeTextField;

    @FXML
    private TextField transportTypeTextField;

    @FXML
    private TableView<TourLog> tourLogsTableView;

    @FXML
    private TableColumn<TourLog, String> dateTimeTableColumn;

    @FXML
    private TableColumn<TourLog, String> commentTableColumn;

    @FXML
    private TableColumn<TourLog, String> difficultyTableColumn;

    @FXML
    private TableColumn<TourLog, String> totalTimeTableColumn;

    @FXML
    private TableColumn<TourLog, String> ratingTableColumn;


    public TourDetailsController(TourDetailsViewModel tourDetailsViewModel, AddTourLogDialogHandler dialogHandler) {
        this.tourDetailsViewModel = tourDetailsViewModel;
        this.dialogHandler = dialogHandler;
    }

    public void loadTour(Tour tour) {
        setImage(Base64.getDecoder().decode(tour.getImage()));
        tourDetailsViewModel.getFrom().set(tour.getFrom());
        tourDetailsViewModel.getTo().set(tour.getTo());
        tourDetailsViewModel.getDescription().set(tour.getDescription());
        tourDetailsViewModel.setDistance(tour.getDistance());
        tourDetailsViewModel.getTransportType().set(tour.getTransportType());
        tourDetailsViewModel.setEstTime(tour.getEstTime());
        tourDetailsViewModel.getRouteInfo().set(tour.getRouteInfo());
        tourDetailsViewModel.setTourLogs(tour.getTourLogs());
    }


    public void setImage(byte[] image) {
        System.out.println("image set");
        InputStream inputStream = new ByteArrayInputStream(image);
        /*OutputStream writer = null;
        try {
            writer = new FileOutputStream(new File("./img.png"));
            writer.write(image);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        tourImageView.setImage(new Image(inputStream));
        //tourImageView.setImage(new Image("file:img.png"));
    }

    private TourOverviewController getTourOverviewController() {
        return (TourOverviewController) ControllerFactory.getInstance().create(TourOverviewController.class);
    }

    @FXML
    public void onAddTourLogClicked() {
        TourOverviewController toc = getTourOverviewController();
        String tourName;
        try {
            tourName = toc.getSelectedTourName();
        } catch (IndexOutOfBoundsException e) {
            DialogHandler.showAlert(App.getResourceBundle().getString("NoSelected_Msg"));
            return;
        }
        TourLog tourLog;
        try {
            tourLog = dialogHandler.createTourLog();
        } catch (Exception e) {
            DialogHandler.showAlert(App.getResourceBundle().getString("BadTourLog_NotValid"));
            return;
        }
        if (null != tourLog) {
            try {
                Wrapper<Boolean> badTour = new Wrapper<>(false);
                Wrapper<Integer> id = new Wrapper<>();
                Thread th = new Thread(() -> {
                    try {
                        id.set(App.getBusinessLogic().addTourLog(tourName, tourLog));
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
                    DialogHandler.showAlert(App.getResourceBundle().getString("BadTourLog_NotValid"));
                }
                else{
                    tourLog.setId(id.get());
                    tourDetailsViewModel.tourLogsListProperty().add(tourLog);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int getSelectedTourLogIndex() {
        return tourLogsTableView.getSelectionModel().getSelectedIndex();
    }

    private TourLog getSelectedTourLog() {
        return tourLogsTableView.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void onDeleteTourLogClicked() {
        System.out.println("Delete");

        try {
            TourLog tourLog = null;
            try {
                tourLog = getSelectedTourLog();
            } catch (IndexOutOfBoundsException e) {
                DialogHandler.showAlert(App.getResourceBundle().getString("NoSelected_Msg"));
                return;
            }

            App.getBusinessLogic().deleteTourLog(tourLog.getId());
            tourDetailsViewModel.tourLogsListProperty().remove(getSelectedTourLogIndex());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onEditTourLogClicked() {
        System.out.println("Edit");

        int id;
        try {
            id = getSelectedTourLog().getId();
        } catch (IndexOutOfBoundsException e) {
            DialogHandler.showAlert(App.getResourceBundle().getString("NoSelected_Msg"));
            return;
        }
        int idx = getSelectedTourLogIndex();
        TourLog tourLog = null;
        try {
            tourLog = dialogHandler.editTourLog(App.getBusinessLogic().getTourLog(id));
        } catch (Exception e) {
            e.printStackTrace();
            DialogHandler.showAlert(App.getResourceBundle().getString("BadTourLog_NotValid"));
            return;
        }

        if (null != tourLog) {
            try {
                App.getBusinessLogic().editTourLog(id, tourLog);
                tourDetailsViewModel.tourLogsListProperty().set(idx, tourLog);
            } catch (IllegalArgumentException e) {
                DialogHandler.showAlert(App.getResourceBundle().getString("BadTourLog_NotValid"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void initialize() {
        fromTextField.textProperty().bindBidirectional(tourDetailsViewModel.getFrom());
        toTextField.textProperty().bindBidirectional(tourDetailsViewModel.getTo());
        descriptionTextField.textProperty().bindBidirectional(tourDetailsViewModel.getDescription());
        routeInfoTextField.textProperty().bindBidirectional(tourDetailsViewModel.getRouteInfo());
        estTimeTextField.textProperty().bindBidirectional(tourDetailsViewModel.getEstTime());
        transportTypeTextField.textProperty().bindBidirectional(tourDetailsViewModel.getTransportType());
        distanceTextField.textProperty().bindBidirectional(tourDetailsViewModel.getDistance());

        tourLogsTableView.setItems(tourDetailsViewModel.tourLogsListProperty());
        dateTimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        commentTableColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
        difficultyTableColumn.setCellValueFactory(new PropertyValueFactory<>("difficultyAsString"));
        totalTimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("totalTimeAsString"));
        ratingTableColumn.setCellValueFactory(new PropertyValueFactory<>("ratingAsString"));
    }
}
