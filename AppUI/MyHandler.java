import com.sun.net.httpserver.*;
//import com.sun.tools.javac.resources.compiler;

import java.io.*;
import java.net.*;
import java.util.*;
import GPT.*;
import Credentials.*;

import static com.mongodb.client.model.Filters.eq;


import org.bson.Document;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import Mediator.Mediator;

import org.bson.types.ObjectId;

public class MyHandler implements HttpHandler{
    private final ArrayList<String> history;
    String toChange;
    GPT gpt;
    Credentials credentials;

    private final String uri = "mongodb+srv://k2chung:suqNIH8XW2du0NId@sayit.gzgbzwy.mongodb.net/?retryWrites=true&w=majority";

    MyHandler(ArrayList<String> history, Mediator m){
        this.history = history;
        gpt = new GPT();
        credentials = new Credentials(m);
    }

    public void handle(HttpExchange httpExchange) throws IOException{
        String response = "Request Received";
        boolean loginresponse;
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

    public Map<String,String> decodeQuery(String s) throws UnsupportedEncodingException{
        Map<String, String> toReturn = new HashMap<String, String>();
        String[] toDecode;

        //System.out.println(s);
        String[] encodedEqualVals = s.split("&");
        for(int i = 0; i< encodedEqualVals.length; i++){
            //System.out.println(encodedEqualVals[i]);
            toDecode = encodedEqualVals[i].split("=");
            //System.out.println(toDecode[0]);
            //System.out.println(toDecode[1]);
            toReturn.put( 
                URLDecoder.decode(toDecode[0], "UTF-8"), 
                URLDecoder.decode(toDecode[1], "UTF-8")
            );
        }

        return toReturn;
    } 

    private String handlePOST(HttpExchange httpExchange)throws IOException, UnsupportedEncodingException {
        InputStream inStream = httpExchange.getRequestBody();
        Scanner scanner = new Scanner(inStream);
        String postData = scanner.nextLine();
        String question = postData;
        String response = "invalid query";

        Map<String, String> queryInfo = decodeQuery(question);

        if(queryInfo.get("cmdType").equals("chatGpt")){
            response = gpt.generate(queryInfo.get("question"));
        }else if(queryInfo.get("cmdType").equals("createAcc")){
            response = credentials.createAccount(queryInfo.get("username"), queryInfo.get("password")).toString();
            //System.out.println("createaccountcall: " + response);
        }else if(queryInfo.get("cmdType").equals("login")){
            response = credentials.login(queryInfo.get("username"), queryInfo.get("password")).toString();
            //System.out.println("createaccountcall: " + response);
        }
        
        scanner.close();

        return response;
    }

}
