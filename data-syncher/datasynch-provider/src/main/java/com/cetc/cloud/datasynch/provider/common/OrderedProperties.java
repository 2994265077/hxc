package com.cetc.cloud.datasynch.provider.common;

import java.util.*;

/**
 * 顺序读取Properties文件类
 * @author Clive
 *
 */
public class OrderedProperties extends Properties {
 
    private final LinkedHashSet<Object> keys = new LinkedHashSet<Object>();
 
    @Override
    public Set<String> stringPropertyNames() {
        Set<String> set = new LinkedHashSet<String>();
        for (Object key : keys) {
            set.add((String) key);
        }
        return set;
    }
 
    @Override
    public Set<Object> keySet() {
        return keys;
    }
 
    @Override
    public synchronized Enumeration<Object> keys() {
        return Collections.enumeration(keys);
    }
 
    @Override
    public synchronized Object put(Object key, Object value) {
        keys.add(key);
        return super.put(key, value);
    }
 
}