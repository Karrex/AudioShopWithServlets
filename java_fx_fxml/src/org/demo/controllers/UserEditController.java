package org.demo.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import org.demo.data.User;

import java.net.URL;
import java.util.ResourceBundle;

public class UserEditController implements Initializable {
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleSaveAction(ActionEvent actionEvent) {
        closeCurrentWindow();
    }

    private void closeCurrentWindow() {
        Window window = lastNameField.getScene().getWindow();
        window.hide();
    }

    public void setUser(User user) {
        firstNameField.textProperty().bindBidirectional(user.firstNameProperty());
        lastNameField.textProperty().bindBidirectional(user.lastNameProperty());
    }
}
