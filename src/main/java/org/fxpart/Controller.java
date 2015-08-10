package org.fxpart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.fxpart.combobox.AutosuggestComboBoxList;
import org.fxpart.combobox.KeyValueString;
import org.fxpart.mockserver.LocationBean;
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

    @FXML
    ComboBox<LocationBean> cb;

    @FXML
    Text txt1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        autosuggest.setIgnoreCase(true);
        autosuggest.setIsFullSearch(true); // search is applied to key+value
        autosuggest.setEditable(true);
        autosuggest.setAcceptFreeTextValue(true);
        autosuggest.setLazyMode(false); // if no item is selected, lazy is useless
        autosuggest.setDelay(300);

        // override default Graphical Item Formatter
        //autosuggest.setupAndStart(o -> new MockDatas().loadLocation(),
        //        item -> String.format("%s", item.getValue()),
        //        item -> String.format("%s%s%s", item.getKey(), autosuggest.getKeyValueSeparator(), item.getValue()));

        // default Graphical Item Formatter
        autosuggest.setupAndStart(o -> new MockDatas().loadLocation(), item -> String.format("%s", item.getValue()), null);

        // override default Graphical Item Formatter
//        autosuggest.setupAndStart(o -> new MockDatas().loadLocation(), item -> String.format("%s", item.getValue()), null);

        // test combobox
        cb.setEditable(true);
        List<LocationBean> list = new MockDatas().loadLocationBeans();
        cb.getItems().addAll(list);

        cb.setConverter(new StringConverter<LocationBean>() {
            @Override
            public String toString(LocationBean t) {
                return t == null ? null : t.getName();
            }

            @Override
            public LocationBean fromString(String string) {
                return cb.getValue();
            }
        });

        cb.setButtonCell(new ListCell<LocationBean>() {
            @Override
            protected void updateItem(LocationBean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                } else {
                    setText(item.getName());
                }
            }
        });
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        autosuggest.stopSearch();
    }

    public void click(ActionEvent actionEvent) {
        txt1.textProperty().bind(autosuggest.getSkinControl().getCombo().getEditor().textProperty());
    }
}