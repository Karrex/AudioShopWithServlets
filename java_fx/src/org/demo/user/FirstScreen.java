package org.demo.user;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class FirstScreen extends VBox {

    private final List<User> users;

    public FirstScreen(List<User> users) {
        this.users = users;
        VBox uiNewUser = buildUIForNewUser();
        getChildren().add(uiNewUser);
        for (User user : users) {
            VBox uiForUser = buildUIForUser(user);
            getChildren().add(uiForUser);
        }
    }

    private VBox buildUIForNewUser(){
        final VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setSpacing(10);

        HBox firstNameBox = new HBox();
        firstNameBox.setSpacing(10);

        firstNameBox.getChildren().add(new Label("FirstName: "));
        final TextField firstNameLabel = new TextField();

        firstNameBox.getChildren().add(firstNameLabel);

        vBox.getChildren().add(firstNameBox);

        HBox lastNameBox = new HBox();
        lastNameBox.setSpacing(10);

        lastNameBox.getChildren().add(new Label("LastName: "));
        final TextField lastNameLabel = new TextField();
        lastNameBox.getChildren().add(lastNameLabel);

        vBox.getChildren().add(lastNameBox);

        Button save = new Button();
        save.setText("Add");
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createUser(firstNameLabel.getText(), lastNameLabel.getText());
            }
        });

        vBox.getChildren().add(save);
        return vBox;
    }

    private VBox buildUIForUser(final User user) {
        final UserView userView = new UserView(user);
        userView.setDeleteButtonHandler(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                removeUser(user, userView);
            }
        });

        userView.setUpdateButtonHandler(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateUser(user, userView);
            }
        });
        return userView;
    }

    private void updateUser(User user, UserView vBox) {
        Stage stage = new Stage();
        SecondScreen root = new SecondScreen(user, vBox);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void removeUser(User user, VBox vBox) {
        users.remove(user);
        getChildren().remove(vBox);
    }

    private void createUser(String firstName, String lastName){
        User user = new User(firstName, lastName);
        users.add(user);
        VBox uiForUser = buildUIForUser(user);
        getChildren().add(uiForUser);
    }

}
