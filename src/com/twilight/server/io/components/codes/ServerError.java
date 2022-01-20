package com.twilight.server.io.components.codes;

public interface ServerError {
    public static int INTERNAL_SERVER_ERROR = 500;
    public static int NOT_IMPLEMENTED = 501;
    public static int BAD_GATEWAY = 502;
    public static int SERVICE_UNAVAILABLE = 503;
    public static int GATEWAY_TIMEOUT = 504;
    public static int HTTP_VERSION_NOT_SUPPORTED = 505;
    public static int VARIANT_ALSO_NEGOTIATES = 506;
    public static int INSUFFICIENT_STORAGE = 507;
    public static int LOOP_DETECTED = 508;
    public static int BANDWIDTH_LIMIT_EXCEEDED = 509;
    public static int NOT_EXTENDED = 510;
    public static int NETWORK_AUTHENTICATION_REQUIRED = 511;
    public static int UNKNOWN_ERROR = 520;
    public static int WEB_SERVER_IS_DOWN = 521;
    public static int CONNECTION_TIMED_OUT = 522;
    public static int ORIGIN_IS_UNREACHABLE = 523;
    public static int A_TIMEOUT_OCCURRED = 524;
    public static int SSL_HANDSHAKE_FAILED = 525;
    public static int INVALID_SSL_CERTIFICATE = 526;
}
