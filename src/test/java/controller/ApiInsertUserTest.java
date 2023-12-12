package controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import dto.UserDTOInput;

public class ApiInsertUserTest {

    @Test
    public void testUserInsertion() throws Exception {
        URL randomUserUrl = new URL("https://randomuser.me/api/");
        HttpURLConnection randomUserCon = (HttpURLConnection) randomUserUrl.openConnection();
        randomUserCon.setRequestMethod("GET");

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(randomUserCon.getInputStream());
        JsonNode userNode = rootNode.path("results").get(0);

        int id = userNode.path("id").path("value").asInt();
        String firstName = userNode.path("name").path("first").asText();
        String lastName = userNode.path("name").path("last").asText();
        String password = userNode.path("login").path("password").asText();

        UserDTOInput userDTOInput = new UserDTOInput();
        userDTOInput.setId(id);
        userDTOInput.setName(firstName + " " + lastName);
        userDTOInput.setPassword(password);

        randomUserCon.disconnect();

        URL url = new URL("http://localhost:4567/users");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = objectMapper.writeValueAsBytes(userDTOInput);
            os.write(input, 0, input.length);
        }

        int status = con.getResponseCode();
        assertEquals(201, status);

        con.disconnect();
    }
}

