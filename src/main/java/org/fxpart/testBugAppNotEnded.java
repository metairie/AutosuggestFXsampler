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

/**
 * Created by metairie on 09-Nov-15.
 */
public class testBugAppNotEnded extends Application {
    private final static Logger LOG = LoggerFactory.getLogger(Main.class);

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("testBugAppNotEnded.fxml"));
        stage.setTitle("AutosuggestFx bug control never ended if not used!");

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
