package org.fxpart;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import org.fxpart.combobox.AutosuggestComboBoxList;
import org.fxpart.combobox.KeyValue;
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
    private SimpleObjectProperty<LocationBean> myBeanProperty = new SimpleObjectProperty<>(null);
    private List<LocationBean> list = new MockDatas().loadLocationBeans();

    @FXML
    AutosuggestComboBoxList<LocationBean, KeyValue> autosuggest;

    @FXML
    Label lvisibleRowsCount;

    @FXML
    CheckBox llazyMode, lacceptFreeTextValue, leditable, lisFullSearch, lignoreCase;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        refresh();
    }

    public void change(ActionEvent actionEvent) {
        KeyValue kv = (KeyValue) list.get(5);
        autosuggest.itemProperty().setValue(kv);
        refresh();
    }

    private void refresh() {
        llazyMode.selectedProperty().setValue(autosuggest.isLazyMode());
        lacceptFreeTextValue.selectedProperty().setValue(autosuggest.isAcceptFreeTextValue());
        lvisibleRowsCount.textProperty().setValue(String.valueOf(autosuggest.visibleProperty()));
        leditable.selectedProperty().setValue(autosuggest.isEditable());
        lisFullSearch.selectedProperty().setValue(autosuggest.isFullSearch());
        lignoreCase.selectedProperty().setValue(autosuggest.isIgnoreCase());
    }

    public void debug(ActionEvent actionEvent) {
        autosuggest.getSkinControl().debug("from FXML click ");
        refresh();
    }
}