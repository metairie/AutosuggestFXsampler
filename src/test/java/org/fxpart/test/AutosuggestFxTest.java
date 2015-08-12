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

public class AutosuggestFxTest extends ApplicationTest {
    private final static Logger LOG = LoggerFactory.getLogger(AutosuggestFxTest.class);

    @Override
    public void start(Stage stage) throws IOException {
        LOG.info("Fx loading");
        Parent root = FXMLLoader.load(AutosuggestFxTest.class.getClass().getResource("/org/fxpart/sample.fxml"));
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
    public void scenario1() {
        // select Autosuggest
        clickOn("#autosuggest");
        List<LocationBean> list = new MockDatas().loadLocationBeans();
        // and type "Po",
        write("Po");
        // select first entry "Point of View",
        WaitForAsyncUtils.sleep(500, MILLISECONDS);
        push(KeyCode.DOWN);
        push(KeyCode.ENTER);
        // push enter which switch in Button mode the component,
        WaitForAsyncUtils.sleep(500, MILLISECONDS);
        push(KeyCode.ENTER);
        // push enter again to re-switch to combo mode,
        WaitForAsyncUtils.sleep(500, MILLISECONDS);
        push(KeyCode.ENTER);
        // verify it's Point of view
        AutosuggestComboBoxList autosuggest = lookup("#autosuggest").queryFirst();
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is(list.get(0).getName()));
    }
}