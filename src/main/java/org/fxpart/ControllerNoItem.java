package org.fxpart;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.fxpart.combobox.AutosuggestFX;
import org.fxpart.common.bean.KeyValue;
import org.fxpart.mockserver.KVIntegerDouble;
import org.fxpart.mockserver.KeyValueString;
import org.fxpart.mockserver.LocationBean;
import org.fxpart.mockserver.MockDatas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerNoItem implements Initializable {
    private final static Logger LOG = LoggerFactory.getLogger(ControllerWithItem.class);
    private SimpleObjectProperty<LocationBean> myBeanProperty = new SimpleObjectProperty<>(null);
    private List<LocationBean> list = new MockDatas().loadLocationBeans();

    @FXML
    AutosuggestFX<LocationBean, KeyValueString> autosuggest;

    @FXML
    AutosuggestFX<?, KVIntegerDouble> autosuggestLazy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        // if loading a gauge is displayed
        autosuggestLazy.setSearchEngineMode();
        autosuggestLazy.setAcceptFreeTextValue(false);
        autosuggestLazy.setAlwaysRefresh(false);
        autosuggestLazy.setDelay(500); // for having time to see loadindicator
        List<? extends KeyValue> list = new ArrayList<>();
        autosuggestLazy.setupFilter(o -> new MockDatas().loadKVID(), item -> String.format("%s", item.getValue()));

//        // don't change this
        autosuggest.promptTextProperty().setValue("Type HERE !!!!");
        autosuggest.setupFilter(o -> new MockDatas().loadLocation(), item -> String.format("%s", item.getValue()));
    }

    // clear
    public void clear(ActionEvent actionEvent) {
        autosuggest.itemProperty().setValue(null);
    }

    // change B property
    public void change(ActionEvent actionEvent) {
        KeyValueString kv = new KeyValueString(list.get(3).getCode(), list.get(3).getName());
        autosuggest.itemProperty().setValue(kv);
    }

    // change T KV
    public void changeKV(ActionEvent actionEvent) {
        List<KeyValueString> list = new MockDatas().loadLocation();
        KeyValueString kv = list.get(5);
        autosuggest.itemProperty().setValue(kv);
    }

    public void debug(ActionEvent actionEvent) {
        autosuggest.getSkinControl().debug("from FXML click ");
        //autosuggest.dispose();
    }
}