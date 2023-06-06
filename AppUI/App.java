import com.sun.net.httpserver.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import Mediator.Mediator;

public class App {
    private static final int SERVER_PORT = 8100;
    private static final String SERVER_HOSTNAME = "SayIt host";

    public static void main(String[] args) throws Exception {
        new Server();
        new AccountPage();
    }
}