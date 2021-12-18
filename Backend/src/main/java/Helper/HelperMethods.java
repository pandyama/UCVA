package com.project.Helper;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.HashMap;

public class HelperMethods {
    public static boolean checkRequest(String s1, String s2) {
        return s1.equals(s2);
    }

    public static boolean verifyToken(String token, String role) {
//        System.out.println("Incoming Token");
//        System.out.println(token);
        //Verify Token
        try {
            Algorithm algo = Algorithm.HMAC256("meet");
            JWTVerifier verifier = JWT.require(algo)
                    .withClaim("role",role)
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

    public static HashMap response(String message){
        HashMap<String,String> res = new HashMap<>();
        res.put("message",message);
        return res;
    }
}
