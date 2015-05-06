package org.demo.user;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserView extends VBox {

    private final User user;
    private final TextField firstNameLabel;
    private final TextField lastNameLabel;
    private final Button deleteButton;
    private final Button updateButton;

    public UserView(User user) {
        super(10);
        this.user = user;
        setPadding(new Insets(10, 10, 10, 10));
        getChildren().add(new Label("=======User======="));

        HBox firstNameBox = new HBox();
        firstNameBox.setSpacing(10);

        firstNameBox.getChildren().add(new Label("FirstName: "));
        firstNameLabel = new TextField();
        firstNameLabel.setEditable(false);

        firstNameBox.getChildren().add(firstNameLabel);

        getChildren().add(firstNameBox);

        HBox lastNameBox = new HBox();
        lastNameBox.setSpacing(10);

        lastNameBox.getChildren().add(new Label("LastName: "));
        lastNameLabel = new TextField();
        lastNameLabel.setEditable(false);
        lastNameBox.getChildren().add(lastNameLabel);

        getChildren().add(lastNameBox);
        HBox controlBox = new HBox();
        controlBox.setSpacing(10);

        deleteButton = new Button();
        deleteButton.setText("Delete");

        controlBox.getChildren().add(deleteButton);

        updateButton = new Button();
        updateButton.setText("Update");

        controlBox.getChildren().add(updateButton);

        getChildren().add(controlBox);

        update();
    }

    public void setDeleteButtonHandler(EventHandler<ActionEvent> eventHandler){
        deleteButton.setOnAction(eventHandler);
    }

    public void setUpdateButtonHandler(EventHandler<ActionEvent> eventHandler){
        updateButton.setOnAction(eventHandler);
    }

    public void update() {
        firstNameLabel.setText(user.getFirstName());
        lastNameLabel.setText(user.getLastName());
    }
}
