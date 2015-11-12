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

public class ControllerBugAppNotEnded implements Initializable {
    private final static Logger LOG = LoggerFactory.getLogger(ControllerBugAppNotEnded.class);

    @FXML
    AutosuggestFX<LocationBean, KeyValueString> autosuggest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}