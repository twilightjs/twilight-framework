package com.twilight.server.io.components.status.code;

public interface Redirection {
    public static int MULTIPLE_CHOICES = 300;
    public static int MOVED_PERMANENTLY = 301;
    public static int MOVED_TEMPORARILY = 302;
    public static int SEE_OTHER = 303;
    public static int NOT_MODIFIED = 304;
    public static int USE_PROXY = 305;
    public static int SWITCH_PROXY = 306;
    public static int TEMPORARY_REDIRECT = 307;
    public static int PERMANENT_REDIRECT = 308;
}
