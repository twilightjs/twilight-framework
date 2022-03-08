package org.twilightframework.http.servlet.components.methods;

public enum HttpMethods {
    ALL("ALL"),
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE"),
    COPY("COPY"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    LINK("LINK"),
    UNLINK("UNLINK"),
    PURGE("PURGE"),
    LOCK("LOCK"),
    UNLOCK("UNLOCK"),
    PROPFIND("PROPFIND"),
    VIEW("VIEW");
    private final String method;

    HttpMethods(String method) {
        this.method = method;
    }

    public String get() {
        return method;
    }
}
