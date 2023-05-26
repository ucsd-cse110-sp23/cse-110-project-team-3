import com.sun.net.httpserver.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class Server {
    private static final int SERVER_PORT = 8101;
    private static final String SERVER_HOSTNAME = "SayIt host";

    Server(){
        SERVER_PORT = 8101;
        SERVER_HOSTNAME = "SayIt host";
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        // create a server
        HttpServer server = HttpServer.create(
            new InetSocketAddress(SERVER_HOSTNAME, SERVER_PORT),
            0
        );

        server.createContext("/", new MyHandler());
        server.setExecutor(threadPoolExecutor);
        server.start();
    }
}
