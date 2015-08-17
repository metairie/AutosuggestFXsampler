package org.fxpart.test;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.fxpart.combobox.AutosuggestComboBoxList;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.io.IOException;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class AutosuggestFxIsNOTAcceptFreeValueTest extends ApplicationTest {
    private final static Logger LOG = LoggerFactory.getLogger(AutosuggestFxIsNOTAcceptFreeValueTest.class);
    private static int delay = 500;

    @Override
    public void start(Stage stage) throws IOException {
        LOG.info("Fx loading");
        Parent root = FXMLLoader.load(AutosuggestFxIsNOTAcceptFreeValueTest.class.getClass().getResource("/org/fxpart/testWithItem.fxml"));
        stage.setTitle("AutosuggestFxTest");

        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void scenario_simple_load() {
        // select Autosuggest
        AutosuggestComboBoxList autosuggest = lookup("#autosuggest").queryFirst();
        clickOn("#autosuggest");
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.ENTER);
        push(KeyCode.END);
        push(KeyCode.CONTROL, KeyCode.A);
        push(KeyCode.DELETE);
        write("");
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.ENTER);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.ENTER);
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is(""));
    }

}