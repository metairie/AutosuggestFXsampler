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

/**
 * Created by metairie on 11-Jan-16.
 */
public class ControllerGridPane implements Initializable {
    private final static Logger LOG = LoggerFactory.getLogger(ControllerGridPane.class);

    @FXML
    AutosuggestFX<LocationBean, KeyValueString> autosuggest;
    @FXML
    AutosuggestFX<LocationBean, KeyValueString> autosuggest_contextmenu;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
