import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.util.*;
import GPT.*;

public class MyHandler implements HttpHandler{
    private final ArrayList<String> history;
    String toChange;
    GPT gpt;

    MyHandler(ArrayList<String> history){
        this.history = history;
        gpt = new GPT();
    }

    public void handle(HttpExchange httpExchange) throws IOException{
        String response = "Request Received";
        String method = httpExchange.getRequestMethod();
        System.out.println(method);

        try {
            if (method.equals("POST")) {
                response = handlePOST(httpExchange);
            }else {
                throw new Exception("Not Valid Request Method");
            }
          } catch (Exception e) {
            System.out.println("An erroneous request");
            response = e.toString();
            e.printStackTrace();
          }
          //Sending back response to the client
          httpExchange.sendResponseHeaders(200, response.length());
          OutputStream outStream = httpExchange.getResponseBody();
          outStream.write(response.getBytes());
          outStream.close();
    }

    private String handlePOST(HttpExchange httpExchange)throws IOException {
        InputStream inStream = httpExchange.getRequestBody();
        Scanner scanner = new Scanner(inStream);
        String postData = scanner.nextLine();
        String question = postData;
        String response;

        response = gpt.generate(question);

        scanner.close();

        return response;
    }
}
