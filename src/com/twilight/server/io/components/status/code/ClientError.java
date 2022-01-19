package com.twilight.server.io.components.status.code;

public interface ClientError {
    public static int BAD_REQUEST = 400;
    public static int UNAUTHORIZED = 401;
    public static int PAYMENT_REQUIRED = 402;
    public static int FORBIDDEN = 403;
    public static int NOT_FOUND = 404;
    public static int METHOD_NOT_ALLOWED = 405;
    public static int NOT_ACCEPTABLE = 406;
    public static int PROXY_AUTHENTICATION_REQUIRED = 407;
    public static int REQUEST_TIMEOUT = 408;
    public static int CONFLICT = 409;
    public static int GONE = 410;
    public static int LENGTH_REQUIRED = 411;
    public static int PRECONDITION_FAILED = 412;
    public static int PAYLOAD_TOO_LARGE = 413;
    public static int URI_TOO_LONG = 414;
    public static int UNSUPPORTED_MEDIA_TYPE = 415;
    public static int RANGE_NOT_SATISFIABLE = 416;
    public static int EXPECTATION_FAILED = 417;
    public static int AUTHENTICATION_TIMEOUT = 419;
    public static int MISDIRECTED_REQUEST = 421;
    public static int UNPROCESSABLE_ENTITY = 422;
    public static int LOCKED = 423;
    public static int FAILED_DEPENDENCY = 424;
    public static int TOO_EARLY = 425;
    public static int UPGRADE_REQUIRED = 426;
    public static int PRECONDITION_REQUIRED = 428;
    public static int TOO_MANY_REQUESTS = 429;
    public static int REQUEST_HEADER_FIELDS_TOO_LARGE = 431;
    public static int REQUESTED_HOST_UNAVAILABLE = 434;
    public static int RETRY_WITH = 449;
    public static int UNAVAILABLE_FOR_LEGAL_REASONS = 451;
    public static int CLIENT_CLOSED_REQUEST = 499;
    public static int SWITCH_PROXY = 306;
    public static int TEMPORARY_REDIRECT = 307;
    public static int PERMANENT_REDIRECT = 308;
}
