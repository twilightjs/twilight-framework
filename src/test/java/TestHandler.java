import com.twilight.server.handler.annotation.HttpMethod;
import com.twilight.server.handler.annotation.Listener;
import com.twilight.server.handler.Handler;
import com.twilight.server.io.components.builder.ResponseBuilder;
import com.twilight.server.io.request.HttpRequest;
import com.twilight.server.io.response.HttpResponse;

@Listener(uri = "/")
public class TestHandler implements Handler {
    @Override
    @HttpMethod(method = ResponseBuilder.HttpMethods.GET)
    public void handleRequest(HttpRequest request, HttpResponse response) {
        response.write(response.getResponseBuilder()
                .setStatusCode(ResponseBuilder.HttpCodes.OK, "OK")
                .setHeader("Content-Type", "text/html; charset=utf-8")
                .setHeader("Connection", "close")
                .setFile("D:\\portfolio-v1.5\\index.html")
                .build());
    }
}
