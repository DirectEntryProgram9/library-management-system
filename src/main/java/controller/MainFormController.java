package controller;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import util.Navigation;
import util.Routes;

import java.io.IOException;

public class MainFormController {
    public Button btnManageBooks;
    public Button btnManageMembers;
    public AnchorPane pneMainForm;

    public void initialize() {
        FadeTransition fd = new FadeTransition(Duration.millis(1000),pneMainForm);
        fd.setFromValue(0);
        fd.setToValue(1);
        fd.playFromStart();
    }

    public void btnManageBooks_OnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MANAGE_BOOKS_FORM);
    }

    public void btnManageMembers_OnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MANAGE_MEMBERS_FORM);
    }
}
