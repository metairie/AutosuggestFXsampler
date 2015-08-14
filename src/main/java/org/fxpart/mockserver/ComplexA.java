package org.fxpart.mockserver;

import javafx.beans.property.ObjectProperty;
import org.fxpart.combobox.KeyValue;

/**
 * Created by metairie on 14-Aug-15.
 */
public class ComplexA<B extends CodeNameBean, T extends KeyValue> {
    ComplexA() {
    }

    ObjectProperty<T> item;
    ObjectProperty<B> value;

    <B> ObjectProperty getValue(T x) {
        return value;
    }

    public void test() {
        ComplexA<CodeNameBean, KeyValue> complexa = new ComplexA<>();
    }
}
