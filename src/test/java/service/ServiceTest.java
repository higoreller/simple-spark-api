package service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import service.UserService;
import dto.UserDTOInput;

public class ServiceTest {
    @Test
    public void testAddUser() {
        UserService userService = new UserService();

        UserDTOInput userDTOInput = new UserDTOInput();
        userDTOInput.setId(1);
        userDTOInput.setName("Higor");
        userDTOInput.setPassword("123456");

        userService.addUser(userDTOInput);
        assertEquals(1, userService.listAllUsers().size());
    }
}
