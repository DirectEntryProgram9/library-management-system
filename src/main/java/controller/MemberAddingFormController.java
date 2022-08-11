package controller;

import db.InMemoryDB;
import db.Member;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import util.Navigation;
import util.Routes;

import java.io.IOException;

public class MemberAddingFormController {
    public AnchorPane pneMemberAddingForm;
    public TextField txtNIC;
    public TextField txtName;
    public TextField txtContact;
    public Button btnAdd;
    public Button btnClose;

    public void initialize() {
        FadeTransition fd = new FadeTransition(Duration.millis(1000),pneMemberAddingForm);
        fd.setFromValue(0);
        fd.setToValue(1);
        fd.playFromStart();
    }

    public void btnAdd_OnAction(ActionEvent actionEvent) throws IOException {
        if (txtNIC.getText().isBlank()) {
            new Alert(Alert.AlertType.ERROR,"Please add a NIC number").showAndWait();
            txtNIC.requestFocus();
            txtNIC.selectAll();
            return;
        }
        else if (!isNIC(txtNIC.getText())) {
            new Alert(Alert.AlertType.ERROR,"NIC number format is wrong!").showAndWait();
            txtNIC.requestFocus();
            txtNIC.selectAll();
            return;
        }
        else if (txtName.getText().isBlank()) {
            new Alert(Alert.AlertType.ERROR,"Please add a member name").showAndWait();
            txtName.requestFocus();
            txtName.selectAll();
            return;
        }
        else if (!isName(txtName.getText())) {
            new Alert(Alert.AlertType.ERROR,"Invalid member name!").showAndWait();
            txtName.requestFocus();
            txtName.selectAll();
            return;
        }
        else if (txtContact.getText().isBlank()) {
            new Alert(Alert.AlertType.ERROR,"Please add a contact number").showAndWait();
            txtContact.requestFocus();
            txtContact.selectAll();
            return;
        }
        else if (!isContact(txtContact.getText())) {
            new Alert(Alert.AlertType.ERROR,"Invalid contact number!").showAndWait();
            txtContact.requestFocus();
            txtContact.selectAll();
            return;
        }
        if (InMemoryDB.findMember(txtNIC.getText()) == null) {
            InMemoryDB.addMember(new Member(txtNIC.getText().toUpperCase(),txtName.getText(),txtContact.getText()));
            txtNIC.clear();
            txtName.clear();
            txtContact.clear();
            Navigation.navigate(Routes.MANAGE_MEMBERS_FORM);
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Member is already in the database!").showAndWait();
            txtNIC.requestFocus();
            txtNIC.selectAll();
        }
    }
    public boolean isNIC(String nic) {
        if (nic.length() != 10) return false;
        if (!(nic.charAt(9) == 'v' || nic.charAt(9) == 'V')) return false;
        String onlyNumbers = nic.substring(0,9);
        char[] chars = onlyNumbers.toCharArray();
        for (char ch : chars) {
            if (!(Character.isDigit(ch))) {
                return false;
            }
        }
        return true;
    }
    public boolean isName(String input) {
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            if (!Character.isLetter(aChar) && aChar != ' ' && aChar != '.') return false;
        }
        return true;
    }
    public boolean isContact(String contact) {
        if (contact.length() != 11) return false;
        if (!(contact.charAt(3) == '-' )) return false;
        String onlyNumbers = contact.substring(0,3).concat(contact.substring(4));
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
