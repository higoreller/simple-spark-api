import controller.UserController;

import static spark.Spark.port;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();
        port(4567);
        userController.requestResponses();
    }
}