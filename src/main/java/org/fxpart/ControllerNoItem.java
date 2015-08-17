package org.fxpart;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import java.util.function.Function;

public class ControllerNoItem implements Initializable {
    private final static Logger LOG = LoggerFactory.getLogger(ControllerWithItem.class);
    private SimpleObjectProperty<LocationBean> myBeanProperty = new SimpleObjectProperty<>(null);
    private List<LocationBean> list = new MockDatas().loadLocationBeans();

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
        // don't change this
        autosuggest.setCacheDataMode();
        autosuggest.setupAndStart(o -> new MockDatas().loadLocation(), item -> String.format("%s", item.getValue()), null);
        refresh();
        // works well
        autosuggest.setBeanToItemMapping(new Function<Observable, KeyValueString>() {
            @Override
            public KeyValueString apply(Observable observable) {
                ObjectProperty op = (ObjectProperty) observable;
                LocationBean lb = (LocationBean) op.getValue();
                KeyValue kv = new KeyValueStringImpl(lb.getCode(), lb.getName());
                LOG.debug(" B2T mapping returns : " + kv.getKey() + " - " + kv.getValue());
                return new KeyValueStringImpl(lb.getCode(), lb.getName());
            }
        });
        // and setting a mapping T -> B
        autosuggest.setItemToBeamMapping(new Function<Observable, LocationBean>() {
            @Override
            public LocationBean apply(Observable observable) {
                ObjectProperty op = (ObjectProperty) observable;
                KeyValue kv = (KeyValue) op.getValue();
                LocationBean lb = new LocationBean(String.valueOf(kv.getKey()), String.valueOf(kv.getValue()));
                LOG.debug(" T2B mapping returns : " + lb.getCode() + " - " + lb.getName());
                return lb;
            }
        });
        Bindings.bindBidirectional(autosuggest.beanProperty(), myBeanProperty);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        autosuggest.stopSearch();
    }

    // clear
    public void clear(ActionEvent actionEvent) {
//        autosuggest.itemProperty().setValue(null);
        refresh();
    }

    // change property
    public void change(ActionEvent actionEvent) {
        LocationBean lb = new LocationBean();
        lb.setCode(list.get(3).getCode());
        lb.setName(list.get(3).getName());
        myBeanProperty.setValue(lb);
        //autosuggest.beanProperty().setValue(lb);
    }

    // change KV
    public void changeKV(ActionEvent actionEvent) {
        List<KeyValueString> list = new MockDatas().loadLocation();
        KeyValueString kv = list.get(5);
        autosuggest.itemProperty().setValue(kv);
        refresh();
    }

    public void debug(ActionEvent actionEvent) {
        autosuggest.getSkinControl().debug("from FXML click ");
        LOG.debug(" myBean getName               : " + myBeanProperty.getValue().getName());
        LOG.debug(" control bean getName         : " + autosuggest.beanProperty().getValue().getName());
        LOG.debug(" control item getValue        : " + autosuggest.itemProperty().getValue().getValue());
        LOG.debug(" combo value getValue : " + autosuggest.getSkinControl().getCombo().valueProperty().getValue().getValue());
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
}