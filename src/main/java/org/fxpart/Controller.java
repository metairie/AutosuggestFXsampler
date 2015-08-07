package org.fxpart;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        autosuggest.setEditable(true);
        autosuggest.setAcceptFreeTextValue(true);
        autosuggest.setLazyMode(false); // if no item is selected, lazy is useless
        autosuggest.setDelay(300);
        //autosuggest.setColumnSeparator(" - ");
        //autosuggest.setColumnSeparatorVisible(true);
        autosuggest.setupAndStart(o -> new MockDatas().loadLocation(),
                item -> String.format("%s", item.getValue(), item.getKey()),
                item -> String.format("%s" + autosuggest.getColumnSeparator(), item.getValue())
        );

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
                    setText("fsfsd");
                    LOG.debug("button cell factory : empty");
                } else {
                    setText(item.getName());
                    LOG.debug("button cell factory : " + item.getName());
                }
            }
        });

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        autosuggest.stopSearch();
    }

}