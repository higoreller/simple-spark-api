package controller;

import interfaces.HttpURLConnectionProvider;
import org.junit.jupiter.api.Test;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ApiControllerTest {

    @Test
    public void testUserListing() throws Exception {
        HttpURLConnection mockedConnection = mock(HttpURLConnection.class);
        when(mockedConnection.getResponseCode()).thenReturn(200);

        HttpURLConnectionProvider provider = mock(HttpURLConnectionProvider.class);
        when(provider.getConnection(anyString())).thenReturn(mockedConnection);

        HttpURLConnection connection = provider.getConnection("http://localhost:4567/users");
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        connection.disconnect();

        assertEquals(200, responseCode, "Se a resposta retornar 200 passa no teste!");
    }
}

