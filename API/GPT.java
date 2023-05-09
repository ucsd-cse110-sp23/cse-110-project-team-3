import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.lang.InterruptedException;




public class GPT implements IGPT {
    private static final String API_ENDPOINT = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "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";
    private static final String MODEL = "text-davinci-003";

    public String generate(String prompt) {
        try {
            int maxTokens = 100;

            //create a request body which you will pass into the request object

            JSONObject requestBody = new JSONObject();
            requestBody.put("model", MODEL);
            requestBody.put("prompt", prompt);
            requestBody.put("max_tokens", maxTokens);
            requestBody.put("temperature", 1.0);

            //create a HTTP client
            HttpClient client = HttpClient.newHttpClient();

            //create new request object
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_ENDPOINT))
                    .header("Content-Type", "application/json")
                    .header("Authorization", String.format("Bearer %s", API_KEY))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                    .build();

            //send the request and recieve the response
            HttpResponse<String> response = client.send(
                request, 
                HttpResponse.BodyHandlers.ofString()
            );


            //process the response
            String responseBody = response.body();
            JSONObject responseJson = new JSONObject(responseBody);
            JSONArray choices = responseJson.getJSONArray("choices");
            String generatedText = choices.getJSONObject(0).getString("text");

            return generatedText;
        } catch (IOException | InterruptedException e) {
            return "Error: " + e.getMessage();
        }
    }
}



