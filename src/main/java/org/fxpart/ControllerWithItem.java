package org.fxpart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.fxpart.combobox.AutosuggestComboBoxList;
import org.fxpart.combobox.KeyValueString;
import org.fxpart.mockserver.MockDatas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerWithItem implements Initializable {
    private final static Logger LOG = LoggerFactory.getLogger(ControllerWithItem.class);

    @FXML
    AutosuggestComboBoxList<KeyValueString> autosuggest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<KeyValueString> list = new MockDatas().loadLocation();
        KeyValueString kv = list.get(0);
        autosuggest.setItem(kv);
        autosuggest.setLiveDataMode();
        autosuggest.setupAndStart(o -> new MockDatas().loadLocation(), item -> String.format("%s", item.getValue()), null);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        autosuggest.stopSearch();
    }

    // need to init autosuggest by this click
    public void go(ActionEvent actionEvent) {
        autosuggest.setupAndStart(o -> new MockDatas().loadLocation(), item -> String.format("%s", item.getValue()), null);
    }

    public void go2(ActionEvent actionEvent) {
        List<KeyValueString> list = new MockDatas().loadLocation();
        KeyValueString kv = list.get(0);
        autosuggest.setItem(kv);
        autosuggest.setLiveDataMode();
    }
}