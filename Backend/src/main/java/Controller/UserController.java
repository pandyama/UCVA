package Controller;

import Helper.Constants;
import Helper.HelperMethods;
import com.sun.net.httpserver.HttpServer;

public class UserController {

    private HttpServer server;

    public UserController(HttpServer s1) {
        this.server = s1;
    }

    public void login() {
        this.server.createContext("/login", (exchange -> {
            if (HelperMethods.checkRequest(Constants.POST, exchange.getRequestMethod())) {
                // Authenticate User by generating a JWT and sending it back
            }
        }));
    }

    public void logout(){
        this.server.createContext("/logout", (exchange -> {
            if (HelperMethods.checkRequest(Constants.POST, exchange.getRequestMethod())) {
                // Destroy user's JWT token and send success response
            }
        }));
    }



}
