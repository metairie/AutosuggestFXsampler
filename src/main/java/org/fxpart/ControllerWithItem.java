package org.fxpart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

    @FXML
    Label llazyMode, lacceptFreeTextValue, lvisibleRowsCount, leditable, lisFullSearch, lignoreCase;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<KeyValueString> list = new MockDatas().loadLocation();
        KeyValueString kv = list.get(0);
        autosuggest.setCacheDataMode();
        autosuggest.itemProperty().setValue(kv);
        autosuggest.setupAndStart(o -> new MockDatas().loadLocation(), item -> String.format("%s", item.getValue()), null);
        refresh();
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

    public void change(ActionEvent actionEvent) {
        List<KeyValueString> list = new MockDatas().loadLocation();
        KeyValueString kv = list.get(5);
        autosuggest.itemProperty().setValue(kv);
    }

    private void refresh() {
        llazyMode.textProperty().setValue(String.valueOf(autosuggest.getLazyMode()));
        lacceptFreeTextValue.textProperty().setValue(String.valueOf(autosuggest.isAcceptFreeTextValue()));
        lvisibleRowsCount.textProperty().setValue(String.valueOf(autosuggest.visibleProperty()));
        leditable.textProperty().setValue(String.valueOf(autosuggest.isEditable()));
        lisFullSearch.textProperty().setValue(String.valueOf(autosuggest.isFullSearch()));
        lignoreCase.textProperty().setValue(String.valueOf(autosuggest.isIgnoreCase()));
    }

}