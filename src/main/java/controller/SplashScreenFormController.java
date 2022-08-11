package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.Navigation;

import java.io.IOException;

public class SplashScreenFormController {
    public Rectangle pgbContainer;
    public Rectangle pgbLoad;
    public Label lblStatus;
    public Label lblPercent;

    public void initialize() {
        Timeline timeline = new Timeline();
        KeyFrame frameOne = new KeyFrame(Duration.millis(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblStatus.setText("Welcome to Library Management System");
                pgbLoad.setWidth(pgbLoad.getWidth());
                lblPercent.setText(String.valueOf((int)(pgbLoad.getWidth()/pgbContainer.getWidth()*100)));
            }
        });
        KeyFrame frameTwo = new KeyFrame(Duration.millis(1250), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblStatus.setText("Connecting with the database");
                pgbLoad.setWidth(pgbLoad.getWidth() + 100);
                lblPercent.setText(String.valueOf((int)(pgbLoad.getWidth()/pgbContainer.getWidth()*100)));
            }
        });
        KeyFrame frameThree = new KeyFrame(Duration.millis(2000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblStatus.setText("Loading data");
                pgbLoad.setWidth(pgbLoad.getWidth() + 90);
                lblPercent.setText(String.valueOf((int)(pgbLoad.getWidth()/pgbContainer.getWidth()*100)));
            }
        });
        KeyFrame frameFour = new KeyFrame(Duration.millis(2750), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblStatus.setText("Setting up the UI");
                pgbLoad.setWidth(pgbLoad.getWidth() + 110);
                lblPercent.setText(String.valueOf((int)(pgbLoad.getWidth()/pgbContainer.getWidth()*100)));
            }
        });
        KeyFrame frameFive = new KeyFrame(Duration.millis(3250), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblStatus.setText("Setting up the UI");
                pgbLoad.setWidth(pgbContainer.getWidth());
                lblPercent.setText(String.valueOf((int)(pgbLoad.getWidth()/pgbContainer.getWidth()*100)));
            }
        });
        KeyFrame frameSix = new KeyFrame(Duration.millis(3750), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Parent root = FXMLLoader.load(this.getClass().getResource("/view/LoginForm.fxml"));
                    Scene scene = new Scene(root);
                    AnchorPane pneContainer = (AnchorPane) root.lookup("#pneContainer");
                    Navigation.init(pneContainer);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setMinHeight(400);
                    stage.setMinWidth(600);
                    stage.sizeToScene();
                    stage.setTitle("Library Management System");
                    stage.centerOnScreen();
                    stage.show();
                    lblStatus.getScene().getWindow().hide();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        timeline.getKeyFrames().addAll(frameOne,frameTwo,frameThree,frameFour,frameFive,frameSix);
        timeline.playFromStart();
    }
}
