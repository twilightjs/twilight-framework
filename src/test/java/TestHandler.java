import org.twilightframework.http.handler.Handler;
import org.twilightframework.http.handler.annotation.HttpMethod;
import org.twilightframework.http.handler.annotation.Listener;
import org.twilightframework.http.servlet.components.builder.ResponseBuilder;
import org.twilightframework.http.servlet.request.HttpRequest;
import org.twilightframework.http.servlet.response.HttpResponse;

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
