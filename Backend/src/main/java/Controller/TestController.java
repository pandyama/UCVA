package com.project.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Model.Animal;
import com.project.Model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
public class TestController {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping(
            value = "/user",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public String postBody(@RequestBody User person) {
        System.out.println(person.getUsername());
        try {
            Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/olympicarchery","root","Charlotte1");
            Statement s1 = myConnect.createStatement();
            String query = "SELECT * FROM TEAM;";
            ResultSet r1 = s1.executeQuery(query);
            while(r1.next()){
                System.out.println(r1.getString("TeamID"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "Good";
    }


    @PostMapping(
            value = "/addAnimalTest",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Animal> addAnimalTest(@RequestBody Animal animal) {
        System.out.println(animal.getAnimalID());
        Animal a = animal;
        return new ResponseEntity(a, HttpStatus.OK);
    }

}
