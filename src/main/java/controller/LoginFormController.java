package controller;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import util.Navigation;
import util.Routes;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane pneContainer;
    public Button btnLogin;
    public PasswordField txtPassword;
    private static final String password = "123";
    private static int attempts = 3;

    public void initialize() {
        FadeTransition fd = new FadeTransition(Duration.millis(1000),pneContainer);
        fd.setFromValue(0);
        fd.setToValue(1);
        fd.playFromStart();
    }

    public void btnLogin_OnAction(ActionEvent actionEvent) throws IOException {
        if (!txtPassword.getText().equals(password)) {
            if (attempts > 0) {
                Media media = new Media(this.getClass().getResource("/audio/alertSound.mp3").toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();

                Image image = new Image(this.getClass().getResourceAsStream("/image/AdminLogin.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(45);
                imageView.setFitWidth(45);

                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Admin Password. You have " + attempts + " more attempts to try again");
                attempts--;

                alert.setGraphic(imageView);
                alert.setHeaderText("Invalid Login Credentials");
                alert.setTitle("Access Denied");
                alert.showAndWait();
                mediaPlayer.dispose();
                txtPassword.requestFocus();
                txtPassword.selectAll();
            }
            else {
                new Alert(Alert.AlertType.INFORMATION,"You have reached maximum number of attempts.").showAndWait();
                Platform.exit();
            }
            return;
        }
        Navigation.navigate(Routes.MAIN_FORM);
    }
}
