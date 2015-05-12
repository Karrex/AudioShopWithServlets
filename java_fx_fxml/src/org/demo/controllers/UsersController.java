package org.demo.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.demo.Main;
import org.demo.data.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UsersController implements Initializable {

    private final ObservableList<User> users = FXCollections.observableArrayList();
    @FXML
    private TableView<User> usersView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        users.add(new User("Jack", "Bill"));
        users.add(new User("Jack", "Bob"));

        usersView.setItems(users);
    }

    public void handleDeleteAction(ActionEvent actionEvent) {
        User selectedUser = usersView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            users.remove(selectedUser);
        }
    }

    public void handleCreateAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/user_edit.fxml"));
            Parent root = fxmlLoader.load();
            UserEditController controller =
                    fxmlLoader.getController();
            User newUser = new User("", "");
            users.add(newUser);
            controller.setUser(newUser);
            Stage stage = new Stage();
            stage.setTitle("Create User");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleUpdateAction(ActionEvent actionEvent) {
        User selectedUser = usersView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/user_edit.fxml"));
                Parent root = fxmlLoader.load();
                UserEditController controller =
                        fxmlLoader.getController();
                controller.setUser(selectedUser);
                Stage stage = new Stage();
                stage.setTitle("Update User");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
