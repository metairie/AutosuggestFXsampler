package org.fxpart.test;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

import java.io.IOException;
import java.util.List;

public class AutosuggestFxItemTest extends ApplicationTest {
    private final static Logger LOG = LoggerFactory.getLogger(AutosuggestFxItemTest.class);
    private static int delay = 500;

    @Override
    public void start(Stage stage) throws IOException {
        LOG.info("Fx loading");
        Parent root = FXMLLoader.load(AutosuggestFxItemTest.class.getClass().getResource("/org/fxpart/testItem.fxml"));
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
        // verify it's Point of view
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is(list.get(1).getName()));
    }

}