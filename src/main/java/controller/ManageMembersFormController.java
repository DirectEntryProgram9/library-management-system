package controller;

import db.Book;
import db.InMemoryDB;
import db.Member;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
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

public class ManageMembersFormController {
    public AnchorPane pneManageMembersForm;
    public TableView<Member> tblMembers;
    public Button btnAddNewMember;
    public Button btnBack;

    public void initialize() {
        Platform.runLater(btnAddNewMember::requestFocus);
        FadeTransition fd = new FadeTransition(Duration.millis(1000),pneManageMembersForm);
        fd.setFromValue(0);
        fd.setToValue(1);
        fd.playFromStart();

        tblMembers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblMembers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblMembers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("contact"));

        ObservableList<Member> olMembers = FXCollections.observableArrayList(InMemoryDB.getMemberDataBase());
        tblMembers.setItems(olMembers);
    }

    public void btnAddNewMember_OnAction(ActionEvent actionEvent) throws IOException {
        tblMembers.getSelectionModel().clearSelection();
        Navigation.navigate(Routes.MEMBER_ADDING_FORM);
    }

    public void btnBack_OnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAIN_FORM);
    }

    public void tblMembers_OnMouseClicked(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getClickCount() == 2) {
            MemberEditingFormController ctrl = (MemberEditingFormController) Navigation.navigate(Routes.MEMBER_EDITING_FORM);
            Member selectedMember = tblMembers.getSelectionModel().getSelectedItem();
            ctrl.setData(selectedMember);
        }
    }
}
