package org.fxpart;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.fxpart.combobox.AutosuggestFX;
import org.fxpart.mockserver.KeyValueString;
import org.fxpart.mockserver.LocationBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMatrix implements Initializable {
    private final static Logger LOG = LoggerFactory.getLogger(ControllerMatrix.class);

    @FXML
    AutosuggestFX<LocationBean, KeyValueString> autosuggestH10V100, autosuggestH10V110;
    @FXML
    AutosuggestFX<LocationBean, KeyValueString> autosuggestH15V100, autosuggestH15V110;
    @FXML
    AutosuggestFX<LocationBean, KeyValueString> autosuggestH25V100, autosuggestH25V110;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        // don't change this
//        autosuggestH25V100.setCacheDataMode(); // NOT ACCEPTING FREE VALUE
//        autosuggestH25V100.setLimitSearch(3);
//        autosuggestH25V100.setupFilter(o -> new MockDatas().loadLocation(), item -> String.format("%s", item.getValue()));
//        // works well

        autosuggestH10V100.getSkinControl().getCombo().getEditor().selectAll();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

}