package org.demo.user;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MultiApplication extends Application {

    private List<User> users = new ArrayList<>();
    {
        users.add(new User("Jack", "Bill"));
        users.add(new User("Jack", "Bob"));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FX Demo");

        FirstScreen screen = new FirstScreen(users);

        Scene scene = new Scene(screen, 720, 550);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
