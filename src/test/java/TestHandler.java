import org.twilightframework.http.handler.Handler;
import org.twilightframework.http.handler.annotation.HttpMethod;
import org.twilightframework.http.handler.annotation.Listener;
import org.twilightframework.http.servlet.components.codes.HttpCodes;
import org.twilightframework.http.servlet.components.methods.HttpMethods;
import org.twilightframework.http.servlet.request.HttpRequest;
import org.twilightframework.http.servlet.response.HttpResponse;

@Listener("/")
public class TestHandler implements Handler {
    @Override
    @HttpMethod(HttpMethods.GET)
    public void handleRequest(HttpRequest request, HttpResponse response) {
        response.write(response.builder()
                .setStatusCode(HttpCodes.OK, "OK")
                .setHeader("Content-Type", "text/html; charset=utf-8")
                .setHeader("Connection", "close")
                .setFile("index OK")
                .build());
    }
}
