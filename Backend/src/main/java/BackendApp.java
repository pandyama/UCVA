import Controller.AnimalController;
import Controller.UserController;
import Helper.Constants;
import Model.Animal;
import Model.User;
import com.sun.net.httpserver.HttpServer;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;

public class BackendApp {
    public static void main(String[] args) {
        int serverPort = 8100;
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(serverPort), 0);
            server.setExecutor(null);
            server.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

        AnimalController a1 = new AnimalController(server);
        UserController u1 = new UserController(server);
        a1.addAnimal(server);
        u1.login(server);
        //Create a Dummy Animal
        Animal animal = new Animal(
                1, "Calgary1", "Buddy", 2,
                10.5, Constants.MALE, "10", "10", "2019",
                "husky", "white", "none", "none",
                new String[]{"Pain Killer"}, "None", "None",
                0, 1001, 29001
        );
        JSONObject obj = new JSONObject();
        obj.put("tattooNumber",animal.getTattooNumber());
        obj.put("cityTattoo",animal.getCityTattoo());
        obj.put("name",animal.getName());
        obj.put("age",animal.getAge());
        obj.put("weight",animal.getWeight());
        obj.put("sex",animal.getSex());
        obj.put("bDay",animal.getBDay());
        obj.put("bMonth",animal.getBMonth());
        obj.put("bYear",animal.getBYear());
        obj.put("breed",animal.getBreed());
        obj.put("coatColor",animal.getCoatColor());
        obj.put("problem",animal.getProblem());
        obj.put("comment",animal.getComment());
        obj.put("continuousMedication",animal.getContinuousMedication());
        obj.put("specialInstructions",animal.getSpecialInstructions());
        obj.put("specialDiet",animal.getSpecialDiet());
        obj.put("active",animal.getActive());
        obj.put("rfid",animal.getRfid());
        obj.put("microchip",animal.getMicrochip());

        User testUser = new User("Meet",
                "Student",
                "Student",
                "mp1234");

        JSONObject user = new JSONObject();
        user.put("username","meet");
        user.put("role","student");
        user.put("designation","nAdmin");
        user.put("password","mp1234");


        HttpResponse<JsonNode> jsonPost = Unirest.post("http://localhost:8100/addAnimal")
                .header("accept", "application/json").body(obj).asJson();
        System.out.println(jsonPost.getBody().getObject().get("message"));

        HttpResponse<JsonNode> jsonLogin = Unirest.post("http://localhost:8100/login")
                .header("accept", "application/json").body(user).asJson();
        System.out.println(jsonLogin.getBody().getObject().get("token"));
    }
}
