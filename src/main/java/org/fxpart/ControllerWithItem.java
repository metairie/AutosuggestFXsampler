package org.fxpart;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import org.fxpart.combobox.AutosuggestFX;
import org.fxpart.common.bean.KeyValue;
import org.fxpart.mockserver.KeyValueString;
import org.fxpart.mockserver.LocationBean;
import org.fxpart.mockserver.MockDatas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerWithItem implements Initializable {
    private final static Logger LOG = LoggerFactory.getLogger(ControllerWithItem.class);
    private SimpleObjectProperty<LocationBean> myBeanProperty = new SimpleObjectProperty<>(null);
    private List<LocationBean> list = new MockDatas().loadLocationBeans();

    @FXML
    AutosuggestFX<LocationBean, KeyValueString> autosuggest;
    @FXML
    AutosuggestFX<LocationBean, KeyValueString> autosuggest_contextmenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // don't change this
        autosuggest.setCacheDataMode(); // NOT ACCEPTING FREE VALUE
        autosuggest.setVisibleRowsCount(20);
        autosuggest.setupFilter(() -> new MockDatas().loadLocation(),
                item -> String.format("%s - %s", item.getKey(), item.getValue()),
                item -> String.format("%s", item.getValue())
        );
        // works well

        // TODO BEGIN of temporary code - to be removed
        // try javafx properties binding
        int INDEX = 0; // INDEX = 0 "LO1 Point of view 1"
        // Location bean and property
        LocationBean lb = list.get(INDEX);

        // TWO METHODS TO LOAD AN ITEM

        // --- 1 ---
        // set an item manually
        // no link between Location Bean and KeyValue bean
        // KeyValue bean and property
//        KeyValueString kvbean = new KeyValueString(lb.getCode(), lb.getName());
//        autosuggest.itemProperty().setValue(kvbean);
//        autosuggest.setDelay(100);

        // --- 2 ---
        // this case is near NEOS project
//         mapping between two Observable and setting a mapping B -> T
        autosuggest.setBeanToItemMapping(observable -> {
            ObjectProperty op = (ObjectProperty) observable;
            if (op == null || op.getValue() == null) {
                return autosuggest.newInstanceOfT.apply(observable);
            } else {
                LocationBean lb1 = (LocationBean) op.getValue();
                KeyValue kv = new KeyValueString(lb1.getCode(), lb1.getName());
                return new KeyValueString(lb1.getCode(), lb1.getName());
            }
        });
        // and setting a mapping T -> B
        autosuggest.setItemToBeamMapping(observable -> {
            ObjectProperty op = (ObjectProperty) observable;
            if (op == null || op.getValue() == null) {
                return autosuggest.newInstanceOfB.apply(observable);
            } else {
                KeyValue kv = (KeyValue) op.getValue();
                LocationBean lb1 = new LocationBean(String.valueOf(kv.getKey()), String.valueOf(kv.getValue()));
                return lb1;
            }
        });
        // bean is not known from autosuggest point of view
        autosuggest.newInstanceOfB = observable -> new LocationBean();
        Bindings.bindBidirectional(autosuggest.beanProperty(), myBeanProperty);
        myBeanProperty.addListener((observable, oldValue, newValue) -> {
            System.out.println("o,o,n " + (observable != null ? observable.getValue() : "") + "," + (oldValue != null ? oldValue.getName() : "") + "," + (newValue != null ? newValue.getName() : ""));
        });
        // set a new value
        myBeanProperty.setValue(new LocationBean(list.get(3)));
        // init values
        autosuggest.refreshBeanCascade(myBeanProperty);
        // END of temporary code - to be removed

        //-- context menu
        autosuggest_contextmenu.setCacheDataMode(); // NOT ACCEPTING FREE VALUE
        autosuggest_contextmenu.setVisibleRowsCount(20);
        autosuggest_contextmenu.setupFilter(() -> new MockDatas().loadLocation(),
                item -> String.format("%s - %s", item.getKey(), item.getValue()),
                item -> String.format("%s", item.getValue())
        );

        // ---- context menu
        MenuItem mm = new MenuItem("Open me");
        ContextMenu localMenu = new ContextMenu(mm);
        autosuggest_contextmenu.setContextMenu(localMenu);
        mm.setOnAction(e -> System.out.println("Selected item: " + autosuggest_contextmenu.getEditorText()));

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        autosuggest.stopFiltering();
    }

    // clear
    public void clear(ActionEvent actionEvent) {
        autosuggest.clearAll();
    }

    // change B property
    public void changeB(ActionEvent actionEvent) {
        LocationBean lb = new LocationBean();
        lb.setCode(list.get(3).getCode());
        lb.setName(list.get(3).getName());
       myBeanProperty.setValue(lb);
//        autosuggest.beanProperty().setValue(lb);
    }

    // change KV
    public void changeT(ActionEvent actionEvent) {
        List<KeyValueString> list = new MockDatas().loadLocation();
        KeyValueString kv = list.get(5);
        autosuggest.itemProperty().setValue(kv);
    }

    public void debug(ActionEvent actionEvent) {
        autosuggest.getSkinControl().debug("from FXML click ");
    }

    public void ctx(ActionEvent actionEvent) {

    }
}
