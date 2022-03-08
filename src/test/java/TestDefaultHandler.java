import org.twilightframework.http.handler.Handler;
import org.twilightframework.http.handler.annotation.DefaultListener;
import org.twilightframework.http.handler.annotation.HttpMethod;
import org.twilightframework.http.servlet.components.codes.HttpCodes;
import org.twilightframework.http.servlet.components.methods.HttpMethods;
import org.twilightframework.http.servlet.request.HttpRequest;
import org.twilightframework.http.servlet.response.HttpResponse;

@DefaultListener
public class TestDefaultHandler implements Handler {
    @Override
    @HttpMethod(HttpMethods.ALL)
    public void handleRequest(HttpRequest request, HttpResponse response) {
        System.out.println(request.getHttpParser().getRequest());
        response.write(response.builder()
                .setStatusCode(HttpCodes.OK, "OK")
                .setHeader("Content-Type", "text/plain; charset=utf-8")
                .setHeader("Connection", "close")
                .setMessageBody("default OK")
                .build());
    }
}
