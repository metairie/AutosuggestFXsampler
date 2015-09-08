package org.fxpart.mockserver;

import org.fxpart.common.bean.KeyValue;

/**
 * Created by metairie on 08.08.2015.
 */
public class KeyValueString extends KeyValue<String, String> {
    public KeyValueString() {
    }

    public KeyValueString(Long id, String key, String value) {
        this(key, value);
        setId(id);
    }

    public KeyValueString(String key, String value) {
        setKey(key);
        setValue(value);
    }
}
