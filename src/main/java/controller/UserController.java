package controller;

import dto.UserDTOInput;
import service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static spark.Spark.*;

public class UserController {
    private final UserService userService = new UserService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void requestResponses () {
        get(("/users"), (request, response) -> {
            response.type("application/json");
            return objectMapper.writeValueAsString(userService.listAllUsers());
        });

        get(("/users/:id"),(request, response)->{
            int id = Integer.parseInt(request.params(":id"));
            var user = userService.findUserById(id);
            if(user != null){
                response.type("application/json");
                return objectMapper.writeValueAsString(user);
            } else {
                response.status(404);
                return "Usuário não encontrado.";
            }
        });
        delete(("/users/:id"), (request, response)->{
            int id = Integer.parseInt(request.params(":id"));
            userService.deleteUser(id);
            return "Usuário deletado com sucesso.";
        });
        post(("/users"),(request, response) -> {
            UserDTOInput userDTOInput = objectMapper.readValue(request.body(), UserDTOInput.class);
            userService.addUser(userDTOInput);
            response.status(201);
            return "Usuário criado com sucesso";
        });
        put(("/users"), (request, response) -> {
            UserDTOInput userDTOInput = objectMapper.readValue(request.body(), UserDTOInput.class);
            userService.updateUser(userDTOInput);
            response.status();
            return "Usuário atualizado com sucesso.";
        });
    }
}
