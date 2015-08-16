package org.fxpart;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainNoItem extends Application {
    private final static Logger LOG = LoggerFactory.getLogger(MainNoItem.class);

    @Override
    public void start(Stage stage) throws Exception {
        LOG.info("Fx loading");
        Parent root = FXMLLoader.load(getClass().getResource("testNoItem.fxml"));
        stage.setTitle("AutosuggestFxTest");

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });

        Scene scene = new Scene(root, 1200, 700);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
