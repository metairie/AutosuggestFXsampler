package org.fxpart.test;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.fxpart.combobox.AutosuggestComboBoxList;
import org.fxpart.mockserver.LocationBean;
import org.fxpart.mockserver.MockDatas;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.io.IOException;
import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class AutosuggestFxIsAcceptFreeValueTest extends ApplicationTest {
    private final static Logger LOG = LoggerFactory.getLogger(AutosuggestFxIsAcceptFreeValueTest.class);
    private static int delay = 500;

    @Override
    public void start(Stage stage) throws IOException {
        LOG.info("Fx loading");
        Parent root = FXMLLoader.load(AutosuggestFxIsAcceptFreeValueTest.class.getClass().getResource("/org/fxpart/testNoItem.fxml"));
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
        List<LocationBean> list = new MockDatas().loadLocationBeans();
        // and type "Po",
        write("qwertz");
        // select first entry "Point of View",
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.DOWN);
        push(KeyCode.ENTER);
        // push enter which switch in Button mode the component,
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.ENTER);
        // push enter again to re-switch to combo mode,
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.ENTER);
        // verify it's word input
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is("qwertz"));
    }

}