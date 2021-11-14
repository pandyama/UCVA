package Controller;

import Helper.Constants;
import Helper.HelperMethods;
import Helper.StatusCode;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.net.httpserver.HttpServer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.*;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class UserController {

    private HttpServer server;

    public UserController(HttpServer s1) {
        this.server = s1;
    }

    public void login(HttpServer server) {
        server.createContext("/login", (exchange -> {
            if (HelperMethods.checkRequest(Constants.POST, exchange.getRequestMethod())) {
                // Authenticate User by generating a JWT and sending it back
                InputStreamReader is = new InputStreamReader(exchange.getRequestBody());
                BufferedReader br = new BufferedReader(is);
                String s = br.readLine();
                JsonNode j1 = new JsonNode(s);
                System.out.println("Incoming User Data");
                System.out.println(j1.toPrettyString());

                String token = createJWT();

                JSONObject msg = new JSONObject();
                msg.put("token",token);

                exchange.sendResponseHeaders(StatusCode.OK.getCode(), msg.toString().getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(msg.toString().getBytes());
                output.flush();
                output.close();

            }
        }));
    }

    public static String createJWT(){
//        byte[] key = "meet".getBytes();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date expire = new Date(nowMillis+100);
        Algorithm algo = Algorithm.HMAC256("meet");
        String token = JWT.create().withIssuer("auth0").withExpiresAt(expire).sign(algo);
        return token;
    }

    public boolean verifyToken(String token){
        //Verify Token
        try {
            Algorithm algo = Algorithm.HMAC256("meet");
            JWTVerifier verifier = JWT.require(algo)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }
        catch (JWTVerificationException exception){
            // Token is invalid
            return false;
        }
    }

    public void verifyToken(HttpServer server){
        server.createContext("/verifyToken", (exchange -> {
            if (HelperMethods.checkRequest(Constants.POST, exchange.getRequestMethod())) {
                // Destroy user's JWT token and send success response
                InputStreamReader is = new InputStreamReader(exchange.getRequestBody());
                BufferedReader br = new BufferedReader(is);
                String s = br.readLine();
                JsonNode j1 = new JsonNode(s);
                System.out.println("Incoming Token");
                System.out.println(j1.toPrettyString());
            }
        }));
    }

    public void logout(HttpServer server){
        server.createContext("/logout", (exchange -> {
            if (HelperMethods.checkRequest(Constants.POST, exchange.getRequestMethod())) {
                // Destroy user's JWT token and send success response
            }
        }));
    }



}
