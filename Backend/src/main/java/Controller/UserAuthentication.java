package com.project.Controller;

import com.auth0.jwt.algorithms.Algorithm;

import com.project.Helper.HelperMethods;
import com.auth0.jwt.JWT;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.*;
import java.util.Date;

@CrossOrigin
@RestController
public class UserAuthentication {
    @PostMapping(
            value = "/login",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HashMap> login(@RequestBody HashMap<String, String> payload) {
        //Call DB service to check user password
        String token = createJWT(payload.get("role"));
        System.out.println(HelperMethods.verifyToken(token, payload.get("role")));
        System.out.println(HelperMethods.verifyToken(token, "admin"));
        HashMap response = HelperMethods.response(token);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(
            value = "/getRole",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList> getRole(@RequestBody HashMap<String, String> payload) {

        ArrayList result = new ArrayList();
        HashMap response = new HashMap();
        try {
            Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/vetapplication", "root", "Charlotte1");
            String query = "SELECT accessLevel, userID, uRole FROM USERS WHERE username=? AND uPassword = ?;";
            PreparedStatement p1;
            p1 = myConnect.prepareStatement(query);
            p1.setString(1, payload.get("username"));
            p1.setString(2, payload.get("password"));
            ResultSet r1 = p1.executeQuery();
            if (r1.next() == false) {
                response.put("message", "failed");
                result.add(response);
                return ResponseEntity.status(401).body(result);
            } else {
                do {
                    response.put("access", r1.getString("accessLevel"));
                    response.put("userID", r1.getString("userID"));
                    response.put("uRole", r1.getString("uRole"));
                    result.add(response);

                    return ResponseEntity.status(200).body(result);
                }
                while (r1.next());
            }
        } catch (Exception e) {
            response.put("message", "failed");
            response.put("error", e);
            result.add(response);
            return ResponseEntity.status(401).body(result);
        }

    }

    public static String createJWT(String role) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date expire = new Date(nowMillis + 100);
        Algorithm algo = Algorithm.HMAC256("meet");
        String token = JWT.create().withClaim("role", role).withIssuer("auth0").withExpiresAt(expire).sign(algo);
        return token;
    }
}
