package Controller;

import Helper.Constants;
import com.sun.net.httpserver.HttpServer;

public class UserController {

    private HttpServer server;

    public UserController(HttpServer s1) {
        this.server = s1;
    }

    public void login() {
        this.server.createContext("/login", (exchange -> {
            if (checkRequest(Constants.POST, exchange.getRequestMethod())) {
                // Authenticate User by generating a JWT and sending it back
            }
        }));
    }

    public void logout(){
        this.server.createContext("/logout", (exchange -> {
            if (checkRequest(Constants.POST, exchange.getRequestMethod())) {
                // Destroy user's JWT token and send success response
            }
        }));
    }

    public boolean checkRequest(String s1, String s2) {
        return s1.equals(s2);
    }

}
