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
        users.add(new User("Jack", "Torrance"));
        users.add(new User("Danny", "Torrance"));
        users.add(new User("Wendy", "Torrance"));
        users.add(new User("Dick", "Hallorann"));
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
            showNewWindow("Create User", root);
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
                showNewWindow("Update User", root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showNewWindow(String title, Parent root) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
