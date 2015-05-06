package org.demo.user;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SecondScreen extends VBox {

    private final TextField firstNameLabel;
    private final TextField lastNameLabel;
    private final User currentUser;
    private final UserView userView;

    public SecondScreen(User user, UserView userView) {
        currentUser = user;
        this.userView = userView;
        setPadding(new Insets(10, 10, 10, 10));
        setSpacing(10);

        HBox firstNameBox = new HBox();
        firstNameBox.setSpacing(10);

        firstNameBox.getChildren().add(new Label("FirstName: "));
        firstNameLabel = new TextField(currentUser.getFirstName());

        firstNameBox.getChildren().add(firstNameLabel);

        getChildren().add(firstNameBox);

        HBox lastNameBox = new HBox();
        lastNameBox.setSpacing(10);

        lastNameBox.getChildren().add(new Label("LastName: "));
        lastNameLabel = new TextField(currentUser.getLastName());
        lastNameBox.getChildren().add(lastNameLabel);

        getChildren().add(lastNameBox);

        Button save = new Button();
        save.setText("Save");
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveUser();
            }
        });

        getChildren().add(save);
    }

    private void saveUser() {
        String firstName = firstNameLabel.getText();
        String lastName = lastNameLabel.getText();
        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        getScene().getWindow().hide();
        userView.update();
    }
}
