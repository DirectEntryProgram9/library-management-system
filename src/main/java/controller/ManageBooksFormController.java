package controller;

import db.Book;
import db.InMemoryDB;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import util.Navigation;
import util.Routes;

import java.io.IOException;

public class ManageBooksFormController {

    public Button btnAddNewBook;
    public AnchorPane pneManageBooksForm;
    public Button btnBack;
    public TableView<Book> tblBooks;

    public void initialize() {
        Platform.runLater(btnAddNewBook::requestFocus);
        FadeTransition fd = new FadeTransition(Duration.millis(1000),pneManageBooksForm);
        fd.setFromValue(0);
        fd.setToValue(1);
        fd.playFromStart();

        tblBooks.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("isbn"));
        tblBooks.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblBooks.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("author"));

        ObservableList<Book> olBooks = FXCollections.observableArrayList(InMemoryDB.getBookDataBase());
        tblBooks.setItems(olBooks);
    }

    public void btnAddNewBook_OnAction(ActionEvent actionEvent) throws IOException {
        tblBooks.getSelectionModel().clearSelection();
        Navigation.navigate(Routes.BOOK_ADDING_FORM);
    }

    public void btnBack_OnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAIN_FORM);
    }

    public void tblBooks_OnMouseClicked(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getClickCount() == 2) {
            BookEditingFormController ctrl = (BookEditingFormController) Navigation.navigate(Routes.BOOK_EDITING_FORM);
            Book selectedBook = tblBooks.getSelectionModel().getSelectedItem();
            ctrl.setData(selectedBook);
        }
    }
}
