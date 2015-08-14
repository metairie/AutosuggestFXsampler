package org.fxpart.test;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fxpart.combobox.AutosuggestComboBoxList;
import org.fxpart.combobox.KeyValueString;
import org.fxpart.combobox.KeyValueStringImpl;
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

public class AutosuggestFxWithItemTest extends ApplicationTest {
    private final static Logger LOG = LoggerFactory.getLogger(AutosuggestFxWithItemTest.class);
    private static int delay = 500;

    @Override
    public void start(Stage stage) throws IOException {
        LOG.info("Fx loading");
        Parent root = FXMLLoader.load(AutosuggestFxWithItemTest.class.getClass().getResource("/org/fxpart/testWithItem.fxml"));
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
        autosuggest.setCacheDataMode();
        clickOn("#autosuggest");
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        List<LocationBean> list = new MockDatas().loadLocationBeans();
        // verify it's Point of view
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is(list.get(0).getName()));
    }

    //@Test
    public void scenario_change_item() {
        // select Autosuggest
        AutosuggestComboBoxList autosuggest = lookup("#autosuggest").queryFirst();
        autosuggest.setCacheDataMode();
        clickOn("#autosuggest");
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        List<LocationBean> list = new MockDatas().loadLocationBeans();
        // verify it's Tribune
        KeyValueString kv = new KeyValueStringImpl(list.get(5).getCode(), list.get(5).getName());
        Platform.runLater(() -> autosuggest.itemProperty().setValue(kv));
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is(list.get(5).getName()));
        // verify it's null
        Platform.runLater(() -> autosuggest.itemProperty().setValue(null));
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.isEmptyOrNullString());
    }

}