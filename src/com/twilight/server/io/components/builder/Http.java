package com.twilight.server.io.components.builder;

import com.twilight.server.io.components.methods.Methods;
import com.twilight.server.io.components.status.code.*;

public interface Http {
    interface HttpCodes extends Informational, Success, Redirection, ClientError, ServerError {
    }

    interface HttpMethods extends Methods {
    }
}
