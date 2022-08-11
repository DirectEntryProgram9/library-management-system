package controller;

import db.Book;
import db.InMemoryDB;
import db.Member;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import util.Navigation;
import util.Routes;

import java.io.IOException;

public class MemberEditingFormController {
    public TextField txtNIC;
    public TextField txtName;
    public TextField txtContact;
    public Button btnUpdate;
    public Button btnDelete;

    public void initialize() {
        txtNIC.setEditable(false);
    }

    public void setData(Member member) {
        txtNIC.setText(member.getNic());
        txtName.setText(member.getName());
        txtContact.setText(member.getContact());
    }

    public void btnUpdate_OnAction(ActionEvent actionEvent) throws IOException {
        if (txtName.getText().isBlank()) {
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
        txtNIC.getScene().getWindow().hide();
        Member member = InMemoryDB.updateMember(txtNIC.getText(),txtName.getText(),txtContact.getText());
        if (member != null) {
            Navigation.navigate(Routes.MANAGE_MEMBERS_FORM);
            new Alert(Alert.AlertType.INFORMATION,"NIC no: " + member.getNic() + " member successfully updated in the database").showAndWait();
        }
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) throws IOException {
        txtNIC.getScene().getWindow().hide();
        Member member = InMemoryDB.removeMember(txtNIC.getText());
        if (member != null) {
            Navigation.navigate(Routes.MANAGE_MEMBERS_FORM);
            new Alert(Alert.AlertType.INFORMATION,"NIC no: " + member.getNic() + " member successfully deleted from the database").showAndWait();
        }
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
}
