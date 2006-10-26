package com.sun.xml.bind.v2.util;

import java.util.Map;

/**
 * @author Kohsuke Kawaguchi
 */
public class TypeCast {
    /**
     * Makes sure that a map contains the right type, and returns it to the desirable type.
     */
    public static <K,V> Map<K,V> checkedCast( Map<?,?> m, Class<K> keyType, Class<V> valueType ) {
        for (Map.Entry e : m.entrySet()) {
            if(!keyType.isInstance(e.getKey()))
                throw new ClassCastException(e.getKey().getClass().toString());
            if(!valueType.isInstance(e.getValue()))
                throw new ClassCastException(e.getValue().getClass().toString());
        }
        return (Map<K,V>)m;
    }
}