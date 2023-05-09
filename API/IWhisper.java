import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.lang.InterruptedException;

public interface IWhisper {
    public String generate(String filePath) throws IOException, InterruptedException;
}