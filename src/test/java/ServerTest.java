import com.twilight.server.http.Twilight;
import com.twilight.server.http.TwilightServers;

public class ServerTest {
    public static void main(String[] args) {
        System.out.println("Server started!");
        Twilight server = Twilight.builder()
                .configure(TwilightServers.TWILIGHT_MULTITHREADING_SERVER)
                .setListener("localhost", 80)
                .addHandler(new TestHandler())
                .addHandler(new TestDefaultHandler())
                .build();
        server.start();
    }
}
