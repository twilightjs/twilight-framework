import com.twilight.server.dispatcher.handlers.annotation.DefaultListener;
import com.twilight.server.dispatcher.handlers.annotation.HttpMethod;
import com.twilight.server.handler.Handler;
import com.twilight.server.io.components.builder.Http;
import com.twilight.server.io.request.HttpRequest;
import com.twilight.server.io.response.HttpResponse;

@DefaultListener
public class TestDefaultHandler implements Handler {
    @Override
    @HttpMethod(method = Http.HttpMethods.ALL)
    public void handleRequest(HttpRequest request, HttpResponse response) {
        System.out.println(request.getHttpParser().getRequest());
        response.write(response.getResponseBuilder()
                .setStatusCode(Http.HttpCodes.OK, "OK")
                .setHeader("Content-Type", "text/plain; charset=utf-8")
                .setHeader("Connection", "close")
                .setMessageBody("OK")
                .build());
    }
}
