package org.fxpart;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.fxpart.combobox.AutosuggestFX;
import org.fxpart.combobox.KeyValueString;
import org.fxpart.combobox.KeyValueStringImpl;
import org.fxpart.mockserver.LocationBean;
import org.fxpart.mockserver.MockDatas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerNoItem implements Initializable {
    private final static Logger LOG = LoggerFactory.getLogger(ControllerWithItem.class);
    private SimpleObjectProperty<LocationBean> myBeanProperty = new SimpleObjectProperty<>(null);
    private List<LocationBean> list = new MockDatas().loadLocationBeans();

    @FXML
    AutosuggestFX<LocationBean, KeyValueString> autosuggest;

    @FXML
    AutosuggestFX<LocationBean, KeyValueString> autosuggestLazy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        // if loading a gauge is displayed
        autosuggestLazy.setSearchEngineMode();
        autosuggestLazy.setAcceptFreeTextValue(false);
        autosuggestLazy.setDelay(500); // for having time to see loadindicator
        autosuggestLazy.setupAndStart(o -> new MockDatas().loadProfession(), item -> String.format("%s", item.getValue()), null);
//        // don't change this

        autosuggest.setupAndStart(o -> new MockDatas().loadLocation(), item -> String.format("%s", item.getValue()), null);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        autosuggest.stopSearch();
    }

    // clear
    public void clear(ActionEvent actionEvent) {
        autosuggest.itemProperty().setValue(null);
    }

    // change B property
    public void change(ActionEvent actionEvent) {
        LOG.debug("change B");
        KeyValueStringImpl kv = new KeyValueStringImpl(list.get(3).getCode(), list.get(3).getName());
        autosuggest.itemProperty().setValue(kv);
    }

    // change T KV
    public void changeKV(ActionEvent actionEvent) {
        LOG.debug("change T");
        List<KeyValueString> list = new MockDatas().loadLocation();
        KeyValueString kv = list.get(5);
        autosuggest.itemProperty().setValue(kv);
    }

    public void debug(ActionEvent actionEvent) {
        autosuggest.getSkinControl().debug("from FXML click ");
    }
}