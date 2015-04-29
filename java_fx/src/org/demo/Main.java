package org.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
    private Label label;
    private Button button;
    private TextField textField;
    private Label outInutLabel;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FX Demo");

        HBox root = new HBox();

        label = new Label();
        label.setText("Hello Text");

        root.getChildren().add(label);

        textField = new TextField();
        root.getChildren().add(textField);

        button = new Button();
        button.setText("Click Me");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = textField.getText();
                outInutLabel.setText(text);
            }
        });
        root.getChildren().add(button);

        outInutLabel = new Label();
        root.getChildren().add(outInutLabel);

        Scene scene = new Scene(root, 720, 550);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
