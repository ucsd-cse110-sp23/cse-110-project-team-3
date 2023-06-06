import com.sun.net.httpserver.*;

import Mediator.Mediator;

import java.util.ArrayList;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class Server {
    private static final int SERVER_PORT = 8101;
    private static final String SERVER_HOSTNAME = "localhost";
    Mediator m = new Mediator();
    
    Server() throws IOException{
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        ArrayList<String> history = new ArrayList<String>();

        // create a server
        HttpServer server = HttpServer.create(
            new InetSocketAddress(SERVER_HOSTNAME, SERVER_PORT),
            0
        );

        server.createContext("/", new MyHandler(history, m));
        server.setExecutor(threadPoolExecutor);
        server.start();

        System.out.println("Server started on port " + SERVER_PORT);
    }

}
