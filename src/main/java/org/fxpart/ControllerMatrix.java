package org.fxpart;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.fxpart.combobox.AutosuggestFX;
import org.fxpart.mockserver.KeyValueString;
import org.fxpart.mockserver.LocationBean;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMatrix implements Initializable {

    @FXML
    TextField visibleRow;
    @FXML
    AutosuggestFX<LocationBean, KeyValueString> autosuggestH10V100, autosuggestH15V100, autosuggestH20V100, autosuggestH25V100, autosuggestH30V100, autosuggestH35V100;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // for number of rows
        ChangeListener<Boolean> focusListener = (o, old, n) -> {
            // lost focus
            if (old && !n) {
                autosuggestH10V100.setVisibleRowsCount(Integer.valueOf(visibleRow.getText()));
            }
        };
        visibleRow.focusedProperty().addListener(focusListener);

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

}