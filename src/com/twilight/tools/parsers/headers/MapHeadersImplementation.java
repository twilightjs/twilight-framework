package com.twilight.tools.parsers.headers;

public abstract class MapHeadersImplementation {
    private Header[] headers;

    protected MapHeadersImplementation() {
        this.headers = new Header[0];
    }

    protected void put(String name, String value) {
        if (checkName(this.headers, name)) return;
        this.headers = copyArray(this.headers);
        Header header = new Header();
        header.setName(name);
        header.setValue(value);
        this.headers[this.headers.length - 1] = header;
    }

    private boolean checkName(Header[] header, String name) {
        boolean isHave = false;
        for (Header dictionary : header) {
            if (dictionary.getName().equals(name)) {
                isHave = true;
                break;
            }
        }
        return isHave;
    }

    private Header[] copyArray(Header[] copyHeader) {
        Header[] copiedHeaders = new Header[copyHeader.length + 1];
        System.arraycopy(copyHeader, 0, copiedHeaders, 0, copyHeader.length);
        return copiedHeaders;
    }

    protected void set(String name, String value) {
        Header header = getValueHeader(name);
        header.setValue(value);
    }


    protected String getValue(String name) {
        Header header = getValueHeader(name);
        return header.getValue();
    }


    protected String getName(String value) {
        Header header = getNameHeader(value);
        return header.getName();
    }

    private Header getNameHeader(String value) {
        Header header = new Header();
        for (Header dictionary : this.headers) {
            if (dictionary.getValue().equals(value)) {
                header = dictionary;
            }
        }
        return header;
    }

    private Header getValueHeader(String name) {
        Header header = new Header();
        for (Header dictionary : this.headers) {
            if (dictionary.getName().equals(name)) {
                header = dictionary;
            }
        }
        return header;
    }


    protected Header[] getHeaders() {
        Header[] array = new Header[this.headers.length];
        System.arraycopy(this.headers, 0, array, 0, this.headers.length);
        return array;
    }
}
