package Controller;

import Helper.Constants;
import Helper.HelperMethods;
import Helper.StatusCode;
import Model.Animal;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.sun.net.httpserver.HttpServer;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AnimalController {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private HttpServer server;

    public AnimalController(HttpServer s) {
        this.server = s;
    }

    public void addAnimal(HttpServer server) {
        server.createContext("/addAnimal", (exchange -> {
            if (HelperMethods.checkRequest(Constants.POST, exchange.getRequestMethod())) {
                // Get Animal data from request and submit to Database
                System.out.println("Received Request at /addAnimal");

                InputStreamReader is = new InputStreamReader(exchange.getRequestBody());
                BufferedReader br = new BufferedReader(is);
                String s = br.readLine();
                JsonNode j1 = new JsonNode(s);
                System.out.println("Incoming Animal Data is below");
                System.out.println(j1.toPrettyString());
                Animal a1;

                try {
                    a1 = objectMapper.readValue(j1.toString(),Animal.class);

                } catch (Exception e) {
                    System.out.println(e);
                }

                JSONObject msg = new JSONObject();
                msg.put("message", "received");

                exchange.sendResponseHeaders(StatusCode.OK.getCode(), msg.toString().getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(msg.toString().getBytes());
                output.flush();
                output.close();
                System.out.println("Response sent back to client");
            } else if (HelperMethods.checkRequest(Constants.GET, exchange.getRequestMethod())) {
                // Respond back saying request not allowed
                exchange.sendResponseHeaders(StatusCode.METHOD_NOT_ALLOWED.getCode(), "Method not allowed".getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write("Method not allowed".getBytes());
                output.flush();
            }
            exchange.close();
        }));
    }

}
