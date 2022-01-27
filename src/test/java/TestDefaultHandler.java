import com.twilight.server.handler.annotation.DefaultListener;
import com.twilight.server.handler.annotation.HttpMethod;
import com.twilight.server.handler.Handler;
import com.twilight.server.io.components.builder.ResponseBuilder;
import com.twilight.server.io.request.HttpRequest;
import com.twilight.server.io.response.HttpResponse;

@DefaultListener
public class TestDefaultHandler implements Handler {
    @Override
    @HttpMethod(method = ResponseBuilder.HttpMethods.ALL)
    public void handleRequest(HttpRequest request, HttpResponse response) {
        System.out.println(request.getHttpParser().getRequest());
        response.write(response.getResponseBuilder()
                .setStatusCode(ResponseBuilder.HttpCodes.OK, "OK")
                .setHeader("Content-Type", "text/plain; charset=utf-8")
                .setHeader("Connection", "close")
                .setMessageBody("OK")
                .build());
    }
}
