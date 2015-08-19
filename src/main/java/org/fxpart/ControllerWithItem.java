package org.fxpart;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class ControllerWithItem implements Initializable {
    private final static Logger LOG = LoggerFactory.getLogger(ControllerWithItem.class);
    private SimpleObjectProperty<LocationBean> myBeanProperty = new SimpleObjectProperty<>(null);
    private List<LocationBean> list = new MockDatas().loadLocationBeans();

    @FXML
    AutosuggestComboBoxList<LocationBean, KeyValueString> autosuggest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        KeyValueString kvbean = new KeyValueStringImpl(lb.getCode(), lb.getName());
//        autosuggest.itemProperty().setValue(kvbean);
//        autosuggest.updateBean(autosuggest.itemProperty());

        // --- 2 ---
//        myBeanProperty.setValue(lb);
//        // mapping between two Observable
//        // and setting a mapping B -> T
//        autosuggest.setBeanToItemMapping(new Function<Observable, KeyValueString>() {
//            @Override
//            public KeyValueString apply(Observable observable) {
//                ObjectProperty op = (ObjectProperty) observable;
//                LocationBean lb = (LocationBean) op.getValue();
//                KeyValue kv = new KeyValueStringImpl(lb.getCode(), lb.getName());
//                return new KeyValueStringImpl(lb.getCode(), lb.getName());
//            }
//        });
//        // and setting a mapping T -> B
//        autosuggest.setItemToBeamMapping(new Function<Observable, LocationBean>() {
//            @Override
//            public LocationBean apply(Observable observable) {
//                ObjectProperty op = (ObjectProperty) observable;
//                KeyValue kv = (KeyValue) op.getValue();
//                LocationBean lb = new LocationBean(String.valueOf(kv.getKey()), String.valueOf(kv.getValue()));
//                return lb;
//            }
//        });
//        autosuggest.newInstanceOfB = new Function<Observable, LocationBean>() {
//            @Override
//            public LocationBean apply(Observable observable) {
//                return new LocationBean();
//            }
//        };
//        Bindings.bindBidirectional(autosuggest.beanProperty(), myBeanProperty);
        // END of temporary code - to be removed

        // don't change this
        autosuggest.setCacheDataMode(); // NOT ACCEPTING FREE VALUE
        autosuggest.setVisibleRowsCount(6);
        autosuggest.setupAndStart(o -> new MockDatas().loadLocation(), item -> String.format("%s", item.getValue()), null);
        // works well
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        autosuggest.stopSearch();
    }

    // clear
    public void clear(ActionEvent actionEvent) {
        autosuggest.itemProperty().setValue(null);
        autosuggest.updateBean(autosuggest.itemProperty());
//        myBeanProperty.setValue(null);
    }


    // change B property
    public void changeB(ActionEvent actionEvent) {
        LocationBean lb = new LocationBean();
        lb.setCode(list.get(3).getCode());
        lb.setName(list.get(3).getName());
//        myBeanProperty.setValue(lb);
        //autosuggest.beanProperty().setValue(lb);
        autosuggest.updateBean(autosuggest.itemProperty());
    }

    // change KV
    public void changeT(ActionEvent actionEvent) {
        List<KeyValueString> list = new MockDatas().loadLocation();
        KeyValueString kv = list.get(5);
        autosuggest.itemProperty().setValue(kv);
        autosuggest.updateBean(autosuggest.itemProperty());
    }

    public void debug(ActionEvent actionEvent) {
        autosuggest.getSkinControl().debug("from FXML click ");
        //LOG.debug(" myBean getName               : " + myBeanProperty.getValue().getName());
       //LOG.debug(" control bean getName         : " + autosuggest.beanProperty().getValue().getName());
        LOG.debug(" control num row              : " + autosuggest.getVisibleRowsCount());

//        LOG.debug(" control item getValue        : " + autosuggest.itemProperty().getValue().getValue());
//        LOG.debug(" combo value getValue : " + autosuggest.getSkinControl().getCombo().valueProperty().getValue().getValue());
    }
}