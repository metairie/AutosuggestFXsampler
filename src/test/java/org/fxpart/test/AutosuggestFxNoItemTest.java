package org.fxpart.test;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.fxpart.combobox.AutosuggestComboBoxList;
import org.fxpart.combobox.KeyValueString;
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

public class AutosuggestFxNoItemTest extends ApplicationTest {
    private final static Logger LOG = LoggerFactory.getLogger(AutosuggestFxNoItemTest.class);
    private static int delay = 500;

    @Override
    public void start(Stage stage) throws IOException {
        LOG.info("Fx loading");
        Parent root = FXMLLoader.load(AutosuggestFxNoItemTest.class.getClass().getResource("/org/fxpart/testWithNoItem.fxml"));
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
    public void scenario_simple_search() {
        // select Autosuggest
        AutosuggestComboBoxList autosuggest = lookup("#autosuggest").queryFirst();
        autosuggest.setCacheDataMode();
        clickOn("#autosuggest");
        List<LocationBean> list = new MockDatas().loadLocationBeans();
        // and type "Po",
        write("Po");
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
        // verify it's Point of view
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is(list.get(0).getName()));
    }

    @Test
    public void scenario_ignore_case() {
        // select Autosuggest
        AutosuggestComboBoxList autosuggest = lookup("#autosuggest").queryFirst();
        autosuggest.setLiveDataMode();
        clickOn("#autosuggest");
        List<LocationBean> list = new MockDatas().loadLocationBeans();
        // and type "po",
        write("po");
        // select first entry "Swimming pool",
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.DOWN);
        push(KeyCode.DOWN);
        push(KeyCode.DOWN);
        push(KeyCode.ENTER);
        // push enter which switch in Button mode the component,
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.ENTER);
        // push enter again to re-switch to combo mode,
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.ENTER);
        // verify it's Point of view
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is(list.get(4).getName()));
    }

    @Test
    public void scenario_full_search() {
        // select Autosuggest
        AutosuggestComboBoxList autosuggest = lookup("#autosuggest").queryFirst();
        autosuggest.setLiveDataMode();
        clickOn("#autosuggest");
        List<LocationBean> list = new MockDatas().loadLocationBeans();
        // and type "6",
        write("6");
        // select first entry "LO6", "Tribune",
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.DOWN);
        push(KeyCode.ENTER);
        // push enter which switch in Button mode the component,
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.ENTER);
        // push enter again to re-switch to combo mode,
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.ENTER);
        // verify it's Point of view
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is(list.get(5).getName()));
    }

}