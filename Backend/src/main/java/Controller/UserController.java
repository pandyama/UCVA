package Controller;

import Helper.Constants;
import Helper.HelperMethods;
import Helper.StatusCode;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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
//                byte[] key = getSignatureKey();
                InputStreamReader is = new InputStreamReader(exchange.getRequestBody());
                BufferedReader br = new BufferedReader(is);
                String s = br.readLine();
                JsonNode j1 = new JsonNode(s);
                System.out.println("Incoming User Data");
                System.out.println(j1.toPrettyString());


                SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
                byte[] key = "meet".getBytes(StandardCharsets.UTF_8);


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
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//        byte[] key = "meet".getBytes();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date expire = new Date(nowMillis+100);
        Algorithm algo = Algorithm.HMAC256("meet");
        String token = JWT.create().withIssuer("auth0").withExpiresAt(expire).sign(algo);
        return token;

    }

    public void logout(){
        this.server.createContext("/logout", (exchange -> {
            if (HelperMethods.checkRequest(Constants.POST, exchange.getRequestMethod())) {
                // Destroy user's JWT token and send success response
            }
        }));
    }



}
