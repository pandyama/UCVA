package Controller;

import Helper.Constants;
import com.sun.net.httpserver.HttpServer;

public class UserController {

    private HttpServer server;
    public UserController(HttpServer s1){
        this.server = s1;
    }

    public void login(){
        this.server.createContext("/login",(exchange -> {
            if(checkRequest(Constants.GET,exchange.getRequestMethod())){
                // Take user to login page
            }
            else if(checkRequest(Constants.POST,exchange.getRequestMethod())){
                // Authenticate User
            }
        }));
    }

    public boolean checkRequest(String s1, String s2){
        return s1.equals(s2);
    }

}
