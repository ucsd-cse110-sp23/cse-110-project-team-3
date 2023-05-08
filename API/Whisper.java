import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.*;
import java.lang.InterruptedException;




public class Whisper implements IWhisper {
    private static final String API_ENDPOINT = "https://api.openai.com/v1/audio/transcriptions";
    private static final String TOKEN = "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";
    private static final String MODEL = "whisper-1";

    private static void writeParameterToOutputStream( OutputStream outputStream, 
        String parameterName, String parameterValue,String boundary) throws IOException {
    outputStream.write(("--" + boundary + "\r\n").getBytes());
    outputStream.write((
        "Content-Disposition: form-data; name=\"" + parameterName + "\"\r\n\r\n"
        ).getBytes()
    );
        outputStream.write((parameterValue + "\r\n").getBytes());
    }






   private static void writeFileToOutputStream(OutputStream outputStream, File file, String boundary) throws IOException {
       // Helper method to write a file to the output stream in multipart form data format private static void writeFileToOutputStream(
       outputStream.write(("--" + boundary + "\r\n").getBytes());
       outputStream.write(
           (
               "Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\n").getBytes()
       );


       outputStream.write(("Content-Type: audio/mpeg\r\n\r\n").getBytes());
      


       FileInputStream fileInputStream = new FileInputStream(file);
       byte[] buffer = new byte[1024];
       int bytesRead;
       while ((bytesRead = fileInputStream.read(buffer)) != -1) {
           outputStream.write(buffer, 0, bytesRead);
       }
       fileInputStream.close();
   }


   private static String handleSuccessResponse(HttpURLConnection connection) throws IOException, JSONException{
       BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
       String inputLine;
       StringBuilder repsonse = new StringBuilder();


       while ((inputLine = in.readLine()) != null) {
           repsonse.append(inputLine);
       }


       in.close();


       JSONObject responseJson = new JSONObject(repsonse.toString());


       String generatedText = responseJson.getString("text");


       // Print the transcrition result
       return generatedText;
      
   }


    public String generate(String filePath) throws IOException, InterruptedException{
        File file = new File(filePath);

        //create a URL connection
        URL url = new URL(API_ENDPOINT);

        // Establish connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // Set headers
        String boundary = "Boudnary-" + System.currentTimeMillis();
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        connection.setRequestProperty("Authorization", "Bearer " + TOKEN);

        // Setup output stream to write request body
       OutputStream outputStream = connection.getOutputStream();


       // WRite model parameter to request body
       writeParameterToOutputStream(outputStream, "model", MODEL, boundary);


       // WRite file parameter to request body
       writeFileToOutputStream(outputStream, file, boundary);


       // Write closing boundary to request body
       outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());


       // Flush and close output stream
       outputStream.flush();
       outputStream.close();


       // Get response code
       int responseCode = connection.getResponseCode();


       // Check response code and handle respoonse accordingly
       if (responseCode == HttpURLConnection.HTTP_OK) {
           return handleSuccessResponse(connection);
       } else {
           return "ERROR";
       }
    }
}

