package org.fxpart;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.fxpart.combobox.AutosuggestFX;
import org.fxpart.combobox.KeyValueString;
import org.fxpart.mockserver.LocationBean;
import org.fxpart.mockserver.MockDatas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerMatrix implements Initializable {
    private final static Logger LOG = LoggerFactory.getLogger(ControllerWithItem.class);
    private SimpleObjectProperty<LocationBean> myBeanProperty = new SimpleObjectProperty<>(null);
    private List<LocationBean> list = new MockDatas().loadLocationBeans();

    @FXML
    AutosuggestFX<LocationBean, KeyValueString> autosuggestH10V100, autosuggestH10V110;
    @FXML
    AutosuggestFX<LocationBean, KeyValueString> autosuggestH15V100, autosuggestH15V110;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

}