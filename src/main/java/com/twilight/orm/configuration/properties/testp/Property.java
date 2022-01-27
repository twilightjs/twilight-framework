package com.twilight.orm.configuration.properties.testp;

import com.twilight.orm.configuration.properties.Dictionary;

public class Property {
    private Dictionary[] dictionaries;

    public Property() {
        this.dictionaries = new Dictionary[0];
    }

    public void put(String key, String value) {
        this.dictionaries = copyArray(this.dictionaries);
        Dictionary dictionary = new Dictionary();
        dictionary.setKey(key);
        dictionary.setValue(value);
        this.dictionaries[this.dictionaries.length - 1] = dictionary;
    }


    public String getProperty(String key) {
        String value = "";
        for (Dictionary dictionary : this.dictionaries) {
            if (dictionary.getKey().equals(key)) {
                value = dictionary.getValue();
            }
        }
        return value;
    }

    public void setProperty(String key, String value) {
        for (Dictionary dictionary : this.dictionaries) {
            if (dictionary.getKey().equals(key)) {
                dictionary.setValue(value);
            }
        }
    }

    public Dictionary[] getDictionary() {
        return this.dictionaries;
    }

    public int getLength() {
        return this.dictionaries.length;
    }

    private Dictionary[] copyArray(Dictionary[] copyDictionaries) {
        Dictionary[] copiedDictionaries = new Dictionary[copyDictionaries.length + 1];
        System.arraycopy(copyDictionaries, 0, copiedDictionaries, 0, copyDictionaries.length);
        return copiedDictionaries;
    }
}
