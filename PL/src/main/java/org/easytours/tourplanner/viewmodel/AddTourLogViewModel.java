package org.easytours.tourplanner.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

@Getter
public class AddTourLogViewModel {
    private final StringProperty dateTime = new SimpleStringProperty();
    private final StringProperty comment = new SimpleStringProperty() ;
    private final StringProperty difficulty = new SimpleStringProperty();
    private final StringProperty totalTimeHours = new SimpleStringProperty();
    private final StringProperty totalTimeMinutes = new SimpleStringProperty();
    private final StringProperty totalTimeSeconds = new SimpleStringProperty();
    private final StringProperty rating = new SimpleStringProperty();

    public void setDateTime(String dateTime){
        this.dateTime.set(dateTime);
    }
    public void setComment(String comment){
        this.comment.set(comment);
    }
    public void setDifficulty(int difficulty){
        this.difficulty.set(String.valueOf(difficulty));
    }
    public void setTotalTimeHours(int hours) {
        this.totalTimeHours.set(String.valueOf(hours));
    }
    public void setTotalTimeMinutes(int minutes) {
        this.totalTimeMinutes.set(String.valueOf(minutes));
    }
    public void setTotalTimeSeconds(int seconds) {
        this.totalTimeSeconds.set(String.valueOf(seconds));
    }
    public void setRating(int rating){
        this.rating.set(String.valueOf(rating));
    }

    public int getDifficulty(){
        return Integer.parseInt(this.difficulty.get());
    }

    public int getRating(){
        return Integer.parseInt(this.rating.get());
    }

    public int getTotalTimeHours(){
        return Integer.parseInt(this.totalTimeHours.get());
    }

    public int getTotalTimeMinutes(){
        return Integer.parseInt(this.totalTimeMinutes.get());
    }

    public int getTotalTimeSeconds(){
        return Integer.parseInt(this.totalTimeSeconds.get());
    }

    public final StringProperty getDifficultyProperty(){
        return this.difficulty;
    }
    public final StringProperty getRatingProperty(){
        return this.rating;
    }
    public final StringProperty getTotalTimeHoursProperty(){
        return this.totalTimeHours;
    }
    public final StringProperty getTotalTimeMinutesProperty(){
        return this.totalTimeMinutes;
    }
    public final StringProperty getTotalTimeSecondsProperty(){
        return this.totalTimeSeconds;
    }

}
