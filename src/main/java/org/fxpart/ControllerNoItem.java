package org.fxpart;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.fxpart.combobox.AutosuggestComboBoxList;
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
    private final static Logger LOG = LoggerFactory.getLogger(ControllerNoItem.class);

    @FXML
    AutosuggestComboBoxList<LocationBean, KeyValueString> autosuggest;

    @FXML
    Label lvisibleRowsCount;

    @FXML
    CheckBox llazyMode, lacceptFreeTextValue, leditable, lisFullSearch, lignoreCase;

    @FXML
    TextField itemOfAs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        autosuggest.setupAndStart(o -> new MockDatas().loadLocation(), item -> String.format("%s", item.getValue()), null);
        refresh();

        // TODO to be removed
        // try javafx properties binding
        int INDEX = 3; // "LO4 Office"
        List<LocationBean> list = new MockDatas().loadLocationBeans();

        // Location bean and property
        LocationBean lb = list.get(INDEX);
        SimpleObjectProperty<LocationBean> locationProperty = new SimpleObjectProperty<>(lb);

        // KeyValue bean and property
        KeyValueString kvbean = new KeyValueStringImpl(lb.getCode(), lb.getName());

        // --- 1 ---
        // set autosuggest manually
        // no link between Location Bean and KeyValue bean
        // [X] it works
        // autosuggest.itemProperty().setValue(kvbean);

        // --- 1 ---
        // set autosuggest
        // no link between Location Bean and KeyValue bean
        // works
        // Bindings.bindBidirectional(autosuggest.itemProperty(), locationProperty);

        // TextField displays the item property after conversion
        StringConverter<KeyValueString> converter = new StringConverter<KeyValueString>() {
            @Override
            public String toString(KeyValueString object) {
                return (object == null ? "" : object.getValue());
            }

            @Override
            public KeyValueString fromString(String string) {
                return new KeyValueStringImpl(null, string);
            }
        };

        Bindings.bindBidirectional(itemOfAs.textProperty(), autosuggest.itemProperty(), converter);
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
        llazyMode.selectedProperty().setValue(autosuggest.isLazyMode());
        lacceptFreeTextValue.selectedProperty().setValue(autosuggest.isAcceptFreeTextValue());
        lvisibleRowsCount.textProperty().setValue(String.valueOf(autosuggest.visibleProperty()));
        leditable.selectedProperty().setValue(autosuggest.isEditable());
        lisFullSearch.selectedProperty().setValue(autosuggest.isFullSearch());
        lignoreCase.selectedProperty().setValue(autosuggest.isIgnoreCase());
    }
}