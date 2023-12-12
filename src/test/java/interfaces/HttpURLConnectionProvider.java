package interfaces;

import java.net.HttpURLConnection;

public interface HttpURLConnectionProvider {
    HttpURLConnection getConnection(String url) throws Exception;
}
