package org.fxpart;

import javafx.beans.binding.Bindings;
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

public class ControllerNoItem implements Initializable {
    private final static Logger LOG = LoggerFactory.getLogger(ControllerNoItem.class);

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

        // TODO temporary code - to be removed
        // try javafx properties binding
        int INDEX = 2; // "LO3 Forest"
        List<LocationBean> list = new MockDatas().loadLocationBeans();

        // Location bean and property
        LocationBean lb = list.get(INDEX);
        SimpleObjectProperty<LocationBean> myBeanProperty = new SimpleObjectProperty<>(lb);

        // KeyValue bean and property
        KeyValueString kvbean = new KeyValueStringImpl(lb.getCode(), lb.getName());

        // --- 1 ---
        // set autosuggest manually
        // no link between Location Bean and KeyValue bean
        // [X] it works
        //autosuggest.itemProperty().setValue(kvbean);

        // --- 2 ---
        // mapping between Observable which contains B to T : developer responsability
        /*autosuggest.setBeanToItemMapping(new Function<Observable, KeyValue>() {
            @Override
            public KeyValue apply(Observable o) {
                ObjectProperty op = (ObjectProperty) o;
                LocationBean lb = (LocationBean) op.getValue();
                return new KeyValueStringImpl(lb.getCode(), lb.getName());
            }
        });
        Bindings.bindBidirectional(autosuggest.beanProperty(), myBeanProperty);*/

        System.out.println(" end controller ");
        // TODO END of temporary

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        autosuggest.stopSearch();
    }

    // clear
    public void clear(ActionEvent actionEvent) {
        //autosuggest.itemProperty().setValue(null);
        if (autosuggest.getBean() != null) {
            System.out.println(" - B bean " + autosuggest.getBean().getName());
        }
    }

    public void change(ActionEvent actionEvent) {
        if (autosuggest.getSkinControl().getCombo().valueProperty().getValue() != null){
            System.out.println(" - combo value property " + autosuggest.getSkinControl().getCombo().valueProperty().getValue().getValue());
        }

        if (autosuggest.getItem() != null) {
            System.out.println(" - T item " + autosuggest.getItem().getValue());
        }
//        List<KeyValueString> list = new MockDatas().loadLocation();
//        KeyValueString kv = list.get(5);
//        autosuggest.itemProperty().setValue(kv);
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