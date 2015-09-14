package org.fxpart.test;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.fxpart.combobox.AutosuggestFX;
import org.fxpart.common.bean.KeyValue;
import org.fxpart.mockserver.KeyValueString;
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

/**
 * Created by metairie on 07-Sep-15.
 */
public class MatrixTest extends ApplicationTest {
    private final static Logger LOG = LoggerFactory.getLogger(MatrixTest.class);
    private static int delay = 500;
    private List<LocationBean> list = new MockDatas().loadLocationBeans();
    private static int count = 3;

    @Override
    public void start(Stage stage) throws IOException {
        LOG.info("Fx loading");
        Parent root = FXMLLoader.load(AutosuggestFxIsAcceptFreeValueTest.class.getClass().getResource("/org/fxpart/testMatrix.fxml"));
        stage.setTitle("AutosuggestFxTest");
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        Scene scene = new Scene(root, 1280, 800);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void scenario_autosuggestH10V100() {
        String ASFX = buildName("H10", "V100");
        // ---------------------------------------
        // test if num rows = 3
        TextField visibleRow = lookup("#visibleRow").queryFirst();
        AutosuggestFX autosuggest = lookup("#" + ASFX).queryFirst();
        autosuggest.setCacheDataMode();
        autosuggest.setVisibleRowsCount(3);
        autosuggest.setDelay(100);
        autosuggest.setupFilter(ds -> new MockDatas().loadLocation(), item -> String.format("%s", ((KeyValue) item).getValue()));
        clickOn("#" + ASFX);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        // open
        push(KeyCode.DOWN);
        // select 3rd - push more
        push(KeyCode.DOWN);
        push(KeyCode.DOWN);
        push(KeyCode.DOWN);
        push(KeyCode.DOWN);
        push(KeyCode.DOWN);

        push(KeyCode.ENTER);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        // verify it's 3rd
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is(list.get(2).getName()));
        // clear
        push(KeyCode.ENTER);
        clear();

        // ---------------------------------------
        // number to -1 (=ALL)
        clickOn("#visibleRow");
        push(KeyCode.BACK_SPACE);
        push(KeyCode.BACK_SPACE);
        push(KeyCode.BACK_SPACE);
        write("-1");
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        clickOn("#" + ASFX);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.ESCAPE);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.ESCAPE);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.ESCAPE);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        // open
        push(KeyCode.DOWN);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        // select 8th
        push(KeyCode.DOWN);
        push(KeyCode.DOWN);
        push(KeyCode.DOWN);
        push(KeyCode.DOWN);
        push(KeyCode.DOWN);
        push(KeyCode.DOWN);
        push(KeyCode.DOWN);
        push(KeyCode.DOWN);
        push(KeyCode.ENTER);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        // verify it's 8th
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is(list.get(7).getName()));
        // clear
        push(KeyCode.ENTER);
        clear();

    }


    @Test
    public void scenario_autosuggestH15V100() {
        String ASFX = buildName("H15", "V100");
        // ---------------------------------------
        // lazy
        AutosuggestFX autosuggest = lookup("#" + ASFX).queryFirst();
        autosuggest.setSearchEngineMode();
        autosuggest.setVisibleRowsCount(10);
        autosuggest.setDelay(100);
        autosuggest.setLazyMode(true);
        autosuggest.setupFilter(ds -> new MockDatas().loadLocation(), item -> String.format("%s", ((KeyValue) item).getValue()));
        MatcherAssert.assertThat(autosuggest.getItems().size(), Matchers.is(0));
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        // open
        clickOn("#" + ASFX);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.DOWN);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        MatcherAssert.assertThat(autosuggest.getItems().size(), Matchers.is(new MockDatas().loadLocation().size()));

    }

    @Test
    public void scenario_autosuggestH20V100() {
        String ASFX = buildName("H20", "V100");
        String txt = "qwertz";
        // ---------------------------------------
        // free value = false
        AutosuggestFX autosuggest = lookup("#" + ASFX).queryFirst();
        autosuggest.setSearchEngineMode();
        autosuggest.setVisibleRowsCount(10);
        autosuggest.setDelay(100);
        autosuggest.setLazyMode(false);
        autosuggest.setAcceptFreeTextValue(false);
        autosuggest.setupFilter(ds -> new MockDatas().loadLocation(), item -> String.format("%s", ((KeyValue) item).getValue()));
        // open
        clickOn("#" + ASFX);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        write(txt);
        push(KeyCode.ENTER);
        push(KeyCode.ENTER);
        push(KeyCode.ENTER);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is(""));

        // ---------------------------------------
        // free value = true
        autosuggest.setAcceptFreeTextValue(true);
        write(txt);
        autosuggest.newInstanceOfT = o -> new KeyValueString("", txt);
        push(KeyCode.ENTER);
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is(txt));

    }

    @Test
    public void scenario_autosuggestH25V100() {
        String ASFX = buildName("H25", "V100");
        // ---------------------------------------
        // limit character when typing. by default it's 0
        AutosuggestFX autosuggest = lookup("#" + ASFX).queryFirst();
        autosuggest.setSearchEngineMode();
        autosuggest.setVisibleRowsCount(10);
        autosuggest.setDelay(100);
        autosuggest.setLazyMode(false);
        autosuggest.setAcceptFreeTextValue(false);
        autosuggest.setupFilter(ds -> new MockDatas().loadLocation(), item -> String.format("%s", ((KeyValue) item).getValue()));
        // open
        clickOn("#" + ASFX);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        write("P");
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.DOWN);
        push(KeyCode.ENTER);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is(list.get(0).getName()));
        push(KeyCode.ENTER);
        clear();

        // ---------------------------------------
        // 3 not triggers a search
        autosuggest.setLimitSearch(4);
        //write("Poi"); // nothing
        push(KeyCode.P, KeyCode.SHIFT);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.O);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.I);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.ENTER);
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is(""));

        // ---------------------------------------
        // 4 charact = search
        clickOn("#" + ASFX);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.P, KeyCode.SHIFT);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.O);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.I);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.N);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.DOWN);
        push(KeyCode.ENTER);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is(list.get(0).getName()));
        push(KeyCode.ENTER);
        clear();
    }

    @Test
    public void scenario_autosuggestH30V100() {
        String ASFX = buildName("H30", "V100");
        // ---------------------------------------
        // no full search
        AutosuggestFX autosuggest = lookup("#" + ASFX).queryFirst();
        autosuggest.setSearchEngineMode();
        autosuggest.setVisibleRowsCount(10);
        autosuggest.setDelay(100);
        autosuggest.setLazyMode(false);
        autosuggest.setAcceptFreeTextValue(false);
        autosuggest.setIsFullSearch(false);
        autosuggest.setupFilter(ds -> new MockDatas().loadLocation(), item -> String.format("%s", ((KeyValue) item).getValue()));
        // open
        clickOn("#" + ASFX);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        write("6");
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.DOWN);
        push(KeyCode.DOWN);
        push(KeyCode.ENTER);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is(list.get(6).getName()));
        push(KeyCode.ENTER);
        clear();

        // ---------------------------------------
        //  full search
        autosuggest.setIsFullSearch(true);
        // open
        clickOn("#" + ASFX);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        write("6");
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.DOWN);
        push(KeyCode.ENTER);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is(list.get(5).getName()));
        push(KeyCode.ENTER);
        clear();
    }

    @Test
    public void scenario_autosuggestH35V100() {
        String ASFX = buildName("H35", "V100");
        // ---------------------------------------
        // ignore case FALSE
        AutosuggestFX autosuggest = lookup("#" + ASFX).queryFirst();
        autosuggest.setSearchEngineMode();
        autosuggest.setVisibleRowsCount(10);
        autosuggest.setDelay(100);
        autosuggest.setLazyMode(false);
        autosuggest.setAcceptFreeTextValue(false);
        autosuggest.setIsFullSearch(false);
        autosuggest.setIgnoreCase(false);
        autosuggest.setupFilter(ds -> new MockDatas().loadLocation(), item -> String.format("%s", ((KeyValue) item).getValue()));
        // open
        clickOn("#" + ASFX);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        write("po");
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.DOWN);
        push(KeyCode.DOWN);
        push(KeyCode.ENTER);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is(list.get(4).getName()));
        push(KeyCode.ENTER);
        clear();

        // ---------------------------------------
        // ignore case
        autosuggest.setIgnoreCase(true);
        // open
        clickOn("#" + ASFX);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        write("po");
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.DOWN);
        push(KeyCode.ENTER);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is(list.get(0).getName()));
        push(KeyCode.ENTER);
        clear();
    }

    @Test
    public void scenario_autosuggestH40V100() {
        String ASFX = buildName("H40", "V100");
        // ---------------------------------------
        // ignore case FALSE
        AutosuggestFX autosuggest = lookup("#" + ASFX).queryFirst();
        autosuggest.setSearchEngineMode();
        autosuggest.setVisibleRowsCount(10);
        autosuggest.setDelay(100);
        autosuggest.setLazyMode(false);
        autosuggest.setAcceptFreeTextValue(false);
        autosuggest.setIsFullSearch(false);
        autosuggest.setIgnoreCase(false);
        autosuggest.setupFilter(ds -> new MockDatas().loadLocation(), item -> String.format("%s", ((KeyValue) item).getValue()));
        // open
        clickOn("#" + ASFX);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        write("po");
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.DOWN);
        push(KeyCode.DOWN);
        push(KeyCode.ENTER);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is(list.get(4).getName()));
        push(KeyCode.ENTER);
        clear();

        // ---------------------------------------
        // ignore case
        autosuggest.setIgnoreCase(true);
        // open
        clickOn("#" + ASFX);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        write("po");
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        push(KeyCode.DOWN);
        push(KeyCode.ENTER);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
        MatcherAssert.assertThat(autosuggest.getEditorText(), Matchers.is(list.get(0).getName()));
        push(KeyCode.ENTER);
        clear();
    }

    private void clear() {
        push(KeyCode.ESCAPE);
        push(KeyCode.ESCAPE);
        push(KeyCode.ESCAPE);
        WaitForAsyncUtils.sleep(delay, MILLISECONDS);
    }

    private String buildName(String ylabel, String xlabel) {
        return "autosuggest" + ylabel + xlabel;
    }

}
