package org.demo.tt;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.demo.tt.controllers.MainController;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SceneLauncher {

    private static Stage currentWindow;

    public static void init(Stage stage) {
        currentWindow = stage;
    }

    public static void showMain() {

        URL url = Main.class.getResource("views/window_main.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            // TODO add logging
            e.printStackTrace();
            Platform.exit();
        }
        MainController controller = loader.getController();
        controller.setDirectory(new File("C://"));
        Scene scene = new Scene(root, 600, 400);
        currentWindow.setScene(scene);
        currentWindow.setTitle("TotallCommander++");

    }

    public static void showFilesInDirectoryWindow(File currentDir) {

        URL url = Main.class.getResource("views/window_main.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            // TODO add logging
            e.printStackTrace();
            Platform.exit();
        }
        MainController controller = loader.getController();
        controller.setDirectory(currentDir);
        Scene scene = new Scene(root, 600, 400);
        currentWindow.setScene(scene);
        currentWindow.setTitle("TotallCommander++");

    }
}
