package controller;

import db.Book;
import db.InMemoryDB;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import util.Navigation;
import util.Routes;
import java.io.IOException;

public class BookEditingFormController {
    public TextField txtISBN;
    public TextField txtName;
    public TextField txtAuthor;
    public Button btnUpdate;
    public Button btnDelete;

    public void initialize() {
        txtISBN.setEditable(false);
    }
    public void setData(Book book) {
        txtISBN.setText(book.getIsbn());
        txtName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
    }
    public void btnUpdate_OnAction(ActionEvent actionEvent) throws IOException {
        if (txtName.getText().isBlank()) {
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
        txtISBN.getScene().getWindow().hide();
        Book book = InMemoryDB.updateBook(txtISBN.getText(), txtName.getText(), txtAuthor.getText());
        if (book != null) {
            Navigation.navigate(Routes.MANAGE_BOOKS_FORM);
            new Alert(Alert.AlertType.INFORMATION,"ISBN no: " + book.getIsbn() + " book successfully updated in the database").showAndWait();
        }
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) throws IOException {
        txtISBN.getScene().getWindow().hide();
        Book book = InMemoryDB.removeBook(txtISBN.getText());
        if (book != null) {
            Navigation.navigate(Routes.MANAGE_BOOKS_FORM);
            new Alert(Alert.AlertType.INFORMATION,"ISBN no: " + book.getIsbn() + " book successfully deleted from the database").showAndWait();
        }
    }
    public boolean isName(String input) {
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            if (!Character.isLetter(aChar) && aChar != ' ' && aChar != '.') return false;
        }
        return true;
    }
}
