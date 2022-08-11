package controller;

import db.Book;
import db.InMemoryDB;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import util.Navigation;
import util.Routes;

import java.io.IOException;
import java.net.URL;

public class BookAddingFormController {
    public AnchorPane pneBookAddingForm;
    public TextField txtISBN;
    public TextField txtName;
    public TextField txtAuthor;
    public Button btnAdd;
    public Button btnClose;

    public void initialize() {
        FadeTransition fd = new FadeTransition(Duration.millis(1000),pneBookAddingForm);
        fd.setFromValue(0);
        fd.setToValue(1);
        fd.playFromStart();
    }

    public void btnAdd_OnAction(ActionEvent actionEvent) throws IOException {
        if (txtISBN.getText().isBlank()) {
            new Alert(Alert.AlertType.ERROR,"Please add an ISBN number").showAndWait();
            txtISBN.requestFocus();
            txtISBN.selectAll();
            return;
        }
        else if (!isISBN(txtISBN.getText())) {
            new Alert(Alert.AlertType.ERROR,"ISBN number format is wrong!").showAndWait();
            txtISBN.requestFocus();
            txtISBN.selectAll();
            return;
        }
        else if (txtName.getText().isBlank()) {
            new Alert(Alert.AlertType.ERROR,"Please add a book name").showAndWait();
            txtName.requestFocus();
            txtName.selectAll();
            return;
        }
        else if (!isName(txtName.getText())) {
            new Alert(Alert.AlertType.ERROR,"Invalid book name!").showAndWait();
            txtName.requestFocus();
            txtName.selectAll();
            return;
        }
        else if (txtAuthor.getText().isBlank()) {
            new Alert(Alert.AlertType.ERROR,"Please add a author name").showAndWait();
            txtAuthor.requestFocus();
            txtAuthor.selectAll();
            return;
        }
        else if (!isName(txtAuthor.getText())) {
            new Alert(Alert.AlertType.ERROR,"Invalid author name!").showAndWait();
            txtAuthor.requestFocus();
            txtAuthor.selectAll();
            return;
        }
        if (InMemoryDB.findBook(txtISBN.getText()) == null) {
            InMemoryDB.addBook(new Book(txtISBN.getText(),txtName.getText(),txtAuthor.getText()));
            txtISBN.clear();
            txtName.clear();
            txtAuthor.clear();
            Navigation.navigate(Routes.MANAGE_BOOKS_FORM);
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Book is already in the database!").showAndWait();
            txtISBN.requestFocus();
            txtISBN.selectAll();
        }
    }
    public boolean isName(String input) {
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            if (!Character.isLetter(aChar) && aChar != ' ' && aChar != '.') return false;
        }
        return true;
    }
    public boolean isISBN(String isbn) {
        if (isbn.length() != 17) return false;
        if (!(isbn.charAt(3) == '-' && isbn.charAt(5) == '-' && isbn.charAt(8) == '-' && isbn.charAt(15) == '-')) return false;
        String onlyNumbers = isbn.substring(0,3).concat(isbn.substring(4,5)).concat(isbn.substring(6,8)).concat(isbn.substring(9,15)).concat(isbn.substring(16));
        char[] chars = onlyNumbers.toCharArray();
        for (char ch : chars) {
            if (!(Character.isDigit(ch))) {
                return false;
            }
        }
        return true;
    }

    public void btnClose_OnAction(ActionEvent actionEvent) {
        btnClose.getScene().getWindow().hide();
    }
}
