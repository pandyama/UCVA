package com.project.Controller;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;

import java.util.Map;

@CrossOrigin
@RestController
public class UserController {

    @PostMapping(
            value = "/addUser",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, String> addUserSubmit(@RequestBody HashMap<String, String> userData) {
        HashMap<String, Integer> accessMap = new HashMap<>();
        accessMap.put("student", 1);
        accessMap.put("health_technician", 2);
        accessMap.put("care_attendant", 3);
        accessMap.put("teaching_technician", 4);
        accessMap.put("admin", 5);

        HashMap<String, String> operationResult = new HashMap<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vetapplication", "root", "Charlotte1");
            String query1 = "INSERT into USERS (username, email, uPassword, uRole, accessLevel) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement p1 = conn.prepareStatement(query1);
            p1.setString(1, userData.get("username"));
            p1.setString(2, userData.get("email"));
            p1.setString(3, userData.get("password"));
            p1.setString(4, userData.get("role"));
            p1.setInt(5, accessMap.get(userData.get("role")));
//            String query2;
//            switch (userData.get("role")) {
//                case "student":
//                    query2 = "INSERT into STUDENT (studentID) values (null)";
//                    break;
//                case "health_technician":
//                    query2 = "INSERT into STUDENT (studentID) values (null)";
//                    break;
//                case "care_attendant":
//                    query2 = "INSERT into care_attendant (careAttendantID) "+
//                            "values (null)";
//                    break;
//                case "teaching_technician":
//                    query2 = "INSERT into TEACHING_TECHNICIAN (teachingTechnicianID) "+
//                            "values (null)";
//                    break;
//                case "admin":
//                    query2 = "INSERT into STUDENT (studentID) values (null)";
//                    break;
//            }
            p1.executeUpdate();
            operationResult.put("result", "success");
        } catch(Exception e) {
            e.printStackTrace();
            operationResult.put("result", "failure");
        }
        return operationResult;
    }

    @PostMapping(value = "/blockUser")
    public Map<String, String> blockUser(@RequestBody HashMap<String, String> userEmail) {
        HashMap<String, String> map = new HashMap<>();
        try {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/vetapplication", "root", "Charlotte1");
            String query="DELETE from USERS WHERE email=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, userEmail.get("email"));
            statement.executeUpdate();
            map.put("result", "success");
        } catch(Exception e) {
            e.printStackTrace();
            map.put("result", "failure");
        }
        return map;
    }
}
