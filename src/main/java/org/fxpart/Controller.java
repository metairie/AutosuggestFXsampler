package org.fxpart;

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

public class Controller implements Initializable {
    private final static Logger LOG = LoggerFactory.getLogger(Controller.class);

    @FXML
    AutosuggestComboBoxList<KeyValueString> autosuggest = new AutosuggestComboBoxList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // data
        List<KeyValueString> list = new MockDatas().loadLocation();

        // choose an item, the first
        KeyValueString kv = list.get(0);

        // set up autosuggest in mode "data live"
        autosuggest.setLiveDataMode();

        // default Graphical Item Formatter
        autosuggest.setupAndStart(o -> list, item -> String.format("%s", item.getValue()), null);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        autosuggest.stopSearch();
    }

}