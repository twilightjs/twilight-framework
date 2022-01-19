package com.twilight.orm.configuration.properties;

public abstract class MapPropertiesImplementation {
    private Dictionary[] dictionaries;

    protected MapPropertiesImplementation() {
        this.dictionaries = new Dictionary[0];
    }

    protected void putProperty(String key, String value) {
        if (checkKey(this.dictionaries, key)) return;
        this.dictionaries = copyArray(this.dictionaries);
        Dictionary dictionary = new Dictionary();
        dictionary.setKey(key);
        dictionary.setValue(value);
        this.dictionaries[this.dictionaries.length - 1] = dictionary;
    }

    private boolean checkKey(Dictionary[] dict, String key) {
        boolean isHave = false;
        for (Dictionary dictionary : dict) {
            if (dictionary.getKey().equals(key)) isHave = true;
        }
        return isHave;
    }

    protected void setProperty(String key, String value) {
        Dictionary dictionary = getValue(key);
        dictionary.setValue(value);
    }

    protected String getPropertyKey(String value) {
        Dictionary dictionary = getKey(value);
        return dictionary.getKey();
    }

    protected String getPropertyValue(String key) {
        Dictionary dictionary = getValue(key);
        return dictionary.getValue();
    }

    protected Dictionary[] getProperties() {
        return this.dictionaries;
    }

    private Dictionary[] copyArray(Dictionary[] copyDictionaries) {
        Dictionary[] copiedDictionaries = new Dictionary[copyDictionaries.length + 1];
        System.arraycopy(copyDictionaries, 0, copiedDictionaries, 0, copyDictionaries.length);
        return copiedDictionaries;
    }


    private Dictionary getKey(String value) {
        Dictionary dict = new Dictionary();
        for (Dictionary dictionary : this.dictionaries) {
            if (dictionary.getValue().equals(value)) {
                dict = dictionary;
            }
        }
        return dict;
    }

    private Dictionary getValue(String key) {
        Dictionary dict = new Dictionary();
        for (Dictionary dictionary : this.dictionaries) {
            if (dictionary.getKey().equals(key)) {
                dict = dictionary;
            }
        }
        return dict;
    }
}
