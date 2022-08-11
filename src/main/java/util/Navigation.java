package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class Navigation {

    private static AnchorPane pneContainer;

    public static void init(AnchorPane anchorPane) {
        pneContainer = anchorPane;
    }
    public static Object navigate(Routes route) throws IOException {
        URL resource;
        switch (route) {
            case LOGIN_FORM:
                resource = Navigation.class.getResource("/view/LoginForm.fxml");
                return loadInSameWindow(resource);
            case MAIN_FORM:
                resource = Navigation.class.getResource("/view/MainForm.fxml");
                return loadInSameWindow(resource);
            case MANAGE_BOOKS_FORM:
                resource = Navigation.class.getResource("/view/ManageBooksForm.fxml");
                return loadInSameWindow(resource);
            case MANAGE_MEMBERS_FORM:
                resource = Navigation.class.getResource("/view/ManageMembersForm.fxml");
                return loadInSameWindow(resource);
            case BOOK_ADDING_FORM:
                resource = Navigation.class.getResource("/view/BookAddingForm.fxml");
                return loadInModalWindow(resource,"Add New Book");
            case MEMBER_ADDING_FORM:
                resource = Navigation.class.getResource("/view/MemberAddingForm.fxml");
                return loadInModalWindow(resource,"Add New Member");
            case BOOK_EDITING_FORM:
                resource = Navigation.class.getResource("/view/BookEditingForm.fxml");
                return loadInModalWindow(resource,"Edit Book Details");
            default:
                resource = Navigation.class.getResource("/view/MemberEditingForm.fxml");
                return loadInModalWindow(resource,"Edit Member Details");
        }
    }
    private static Object loadInSameWindow(URL link) throws IOException {
        pneContainer.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(link);
        Parent root = fxmlLoader.load();
        pneContainer.getChildren().add(root);
        AnchorPane.setLeftAnchor(root,0.0);
        AnchorPane.setRightAnchor(root,0.0);
        AnchorPane.setBottomAnchor(root,0.0);
        AnchorPane.setTopAnchor(root,0.0);

        return fxmlLoader.getController();
    }
    private static Object loadInModalWindow(URL url,String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent root = fxmlLoader.load();
        fxmlLoader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.centerOnScreen();
        stage.sizeToScene();
        stage.show();

        return fxmlLoader.getController();
    }
}
