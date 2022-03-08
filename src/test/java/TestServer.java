import org.twilightframework.http.server.Twilight;
import org.twilightframework.http.server.TwilightServers;

public class TestServer {
    public static void main(String[] args) {
        System.out.println("Server started!");
        Twilight server = Twilight.builder()
                .configure(TwilightServers.TWILIGHT_MULTITHREADING_SERVER)
                .setListener("localhost", 80)
                .addHandler(new TestDefaultHandler())
                .addHandler(new TestHandler())
                .addHandler(new TestHandlerAPI())
                .build();
        server.start();
    }
}
