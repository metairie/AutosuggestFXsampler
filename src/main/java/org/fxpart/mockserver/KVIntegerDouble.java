package org.fxpart.mockserver;

import org.fxpart.common.bean.KeyValue;

/**
 * Created by metairie on 08-Sep-15.
 */
public class KVIntegerDouble extends KeyValue<Integer, Double> {
    public KVIntegerDouble() {
    }

    public KVIntegerDouble(Long id, Integer key, Double value) {
        this(key, value);
        setId(id);
    }

    public KVIntegerDouble(Integer key, Double value) {
        setKey(key);
        setValue(value);
    }
}
