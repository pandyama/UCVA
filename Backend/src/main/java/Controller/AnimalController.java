package Controller;
import Helper.Constants;
import Helper.HelperMethods;
import Helper.StatusCode;
import com.sun.net.httpserver.HttpServer;
import kong.unirest.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class AnimalController {

    private HttpServer server;
    public AnimalController(HttpServer s){
        this.server = s;
    }

    public void addAnimal(){
        this.server.createContext("/addAnimal", (exchange -> {
            if (HelperMethods.checkRequest(Constants.POST, exchange.getRequestMethod())) {
                // Get Animal data from request and submit to Database
                InputStreamReader is = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(is);
                JSONObject requestData = new JSONObject(br.readLine());
                System.out.println(requestData.get("Name"));

            }
            else if(HelperMethods.checkRequest(Constants.GET, exchange.getRequestMethod())){
                // Respond back saying request not allowed
                exchange.sendResponseHeaders(StatusCode.METHOD_NOT_ALLOWED.getCode(),"Method not allowed".getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write("Method not allowed".getBytes());
                output.flush();
            }
        }));
    }

}
