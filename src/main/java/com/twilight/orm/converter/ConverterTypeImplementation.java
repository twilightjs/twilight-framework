package com.twilight.orm.converter;

public class ConverterTypeImplementation {
    protected String convert(String str, String type) {
        StringBuilder converted = new StringBuilder();
        switch (type) {
            case "text":
                converted.append("'").append(str).append("'");
                break;
            case "number":
                converted.append(str);
                break;
            default:
                throw new IllegalArgumentException("Incorrect data type");
        }
        return String.valueOf(converted);
    }
}
