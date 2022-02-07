import org.twilightframework.http.handler.Handler;
import org.twilightframework.http.handler.annotation.DefaultListener;
import org.twilightframework.http.handler.annotation.HttpMethod;
import org.twilightframework.http.servlet.components.builder.ResponseBuilder;
import org.twilightframework.http.servlet.request.HttpRequest;
import org.twilightframework.http.servlet.response.HttpResponse;

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
