package NameValuePair;
import java.io.*;
import java.net.*;

import ServerPopup.ServerPopup;

public class TalktoServer {
    public final String URL = "http://localhost:8101/";
    URL url;

    public TalktoServer(){
        try {
            URL url = new URL(URL);
            this.url = url;
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String sendAndReceive(String input) throws IOException{
        String answer = "invalid response";
        //try {
            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter out = new OutputStreamWriter(
                    conn.getOutputStream()
                );
                out.write(input);
                out.flush();
                out.close();
                BufferedReader in = new BufferedReader(
                  new InputStreamReader(conn.getInputStream())
                );
                StringBuilder toReturn = new StringBuilder();
                String toCheck = in.readLine();
                while(toCheck !=null){
                    toReturn.append(toCheck);
                    //toReturn.append("\n");
                    toCheck = in.readLine();
                }
                in.close();
                answer = toReturn.toString();
            } catch (Exception ex) {
                new ServerPopup();
            }
        //} catch (Exception e) {
          //  new ServerPopup();
        //}
        return answer;
    } 
}
