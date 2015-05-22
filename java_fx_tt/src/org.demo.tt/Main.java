package org.demo.tt;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        SceneLauncher.init(arg0);
        SceneLauncher.showMain();
        arg0.show();
    }

}
