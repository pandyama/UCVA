package com.project.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Exception.RequestException;
import com.project.Helper.HelperMethods;
import com.project.Model.Animal;
import com.project.Model.AnimalBorrow;
import com.project.Model.Comment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.*;
import java.sql.Date;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AnimalController {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(
            value = "/addAnimal",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HashMap> addAnimal(@RequestBody HashMap<String, Object> payload) {
        LinkedHashMap temp = (LinkedHashMap) payload.get("animal");
        Animal newAnimal = new Animal();
        newAnimal.setName(temp.get("name").toString());
        newAnimal.setBirthDate((String) temp.get("dob"));
        newAnimal.setSex((String) temp.get("sex"));
        newAnimal.setAge((Integer) temp.get("age"));
        newAnimal.setBreed((String) temp.get("breed"));
        newAnimal.setWeight((Integer) temp.get("weight"));
        newAnimal.setCoatColor((String) temp.get("color"));
        newAnimal.setSpecies((String) temp.get("species"));
        newAnimal.setPermanentLocation((String) temp.get("location"));
        newAnimal.setPurpose((String) temp.get("purpose"));
        newAnimal.setTattooNumber((Integer) temp.get("tattoo"));
        newAnimal.setCityTattoo((String) temp.get("cityTattoo"));
        newAnimal.setRfid((Integer) temp.get("rfid"));
        newAnimal.setMicrochip((Integer) temp.get("microchip"));

        try {
            Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/vetapplication", "root", "Charlotte1");
            String query = "INSERT INTO ANIMAL (tattooNumber, cityTattoo, aName,\n" +
                    "age, weight, sex, birthDate, breed, coatColor, species, purpose, \n" +
                    "permanentLocation, profileImage, activeID, rfid, microchip)\n" +
                    "VALUES ('" + newAnimal.getTattooNumber() + "'," +
                    "'" + newAnimal.getCityTattoo() + "'," +
                    "'" + newAnimal.getName() + "'," +
                    "'" + newAnimal.getAge() + "'," +
                    "'" + newAnimal.getWeight() + "'," +
                    "'" + newAnimal.getSex() + "'," +
                    "'" + newAnimal.getBirthDate() + "'," +
                    "'" + newAnimal.getBreed() + "'," +
                    "'" + newAnimal.getCoatColor() + "'," +
                    "'" + newAnimal.getSpecies() + "'," +
                    "'" + newAnimal.getPurpose() + "'," +
                    "'" + newAnimal.getPermanentLocation() + "'," +
                    "'Image1.png'," +
                    "'" + 0 + "'," +
                    "'" + newAnimal.getRfid() + "'," +
                    "'" + newAnimal.getMicrochip() + "');";
            PreparedStatement p1;
            p1 = myConnect.prepareStatement(query);
            int r = p1.executeUpdate();
            System.out.println("result from update");
            System.out.println(r);
        } catch (Exception e) {
            System.out.println(e);
        }
        HashMap response = HelperMethods.response("added animal");
        return ResponseEntity.accepted().body(response);
    }

    @GetMapping(value = "/allAnimals")
    public ResponseEntity<ArrayList> allAnimals() {
        ArrayList result = new ArrayList();
        try {
            Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/vetapplication", "root", "Charlotte1");
            String query = "SELECT ANIMAL.animalID, borrowStatus, aName, age, sex, breed, healthStatus, specialInstruction, specialDiet \n" +
                    "FROM ANIMAL_HEALTH RIGHT JOIN ANIMAL ON ANIMAL_HEALTH.animalID=ANIMAL.animalID;";
            PreparedStatement p1;
            p1 = myConnect.prepareStatement(query);
            ResultSet rs = p1.executeQuery(query);
            while (rs.next()) {
                HashMap<String, Object> a = new HashMap<String, Object>();
                a.put("animalID", rs.getInt("ANIMAL.animalID"));
                a.put("aName", rs.getString("aName"));
                a.put("age", rs.getInt("age"));
                a.put("sex", rs.getString("sex"));
                a.put("breed", rs.getString("breed"));
                a.put("borrowStatus", rs.getInt("borrowStatus"));
                a.put("healthStatus", rs.getString("healthStatus"));
                a.put("specialInstruction", rs.getString("specialInstruction"));
                a.put("specialDiet", rs.getString("specialDiet"));
                result.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.accepted().body(result);
    }

    @PostMapping(
            value = "/searchAnimal",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList> searchAnimal(@RequestBody HashMap<String, Integer> payload) {
        ArrayList result = new ArrayList();
        int animalId = payload.get("id");
        try {
            Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/vetapplication", "root", "Charlotte1");
            String query = String.format("SELECT aName, age, sex, breed FROM ANIMAL WHERE animalID = %d", animalId);
            PreparedStatement p1;
            p1 = myConnect.prepareStatement(query);
            ResultSet r1 = p1.executeQuery(query);
            ArrayList animal = new ArrayList();
            while (r1.next()) {
                animal.add(r1.getString("aName"));
                animal.add(r1.getInt("age"));
                animal.add(r1.getString("sex"));
                animal.add(r1.getString("breed"));
                result.add(animal);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.accepted().body(result);
    }

    @PostMapping(
            value = "/searchAnimalByName",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList> searchAnimalByName(@RequestBody HashMap<String, String> payload) {
        System.out.println("request came at /searchByName");
        ArrayList result = new ArrayList();
        String animalName = payload.get("name");
        try {
            Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/vetapplication", "root", "Charlotte1");
            String query = "SELECT ANIMAL.animalID, borrowStatus, aName, age, sex, breed, healthStatus, specialInstruction, specialDiet\n" +
                    "                    FROM ANIMAL_HEALTH RIGHT JOIN ANIMAL ON ANIMAL_HEALTH.animalID=ANIMAL.animalID\n" +
                    "                    WHERE aName= '" + animalName + "';";
            PreparedStatement p1;
            p1 = myConnect.prepareStatement(query);
            ResultSet rs = p1.executeQuery(query);
            while (rs.next()) {
                HashMap<String, Object> a = new HashMap<String, Object>();
                a.put("animalID", rs.getInt("ANIMAL.animalID"));
                a.put("aName", rs.getString("aName"));
                a.put("age", rs.getInt("age"));
                a.put("sex", rs.getString("sex"));
                a.put("breed", rs.getString("breed"));
                a.put("borrowStatus", rs.getInt("borrowStatus"));
                a.put("healthStatus", rs.getString("healthStatus"));
                a.put("specialInstruction", rs.getString("specialInstruction"));
                a.put("specialDiet", rs.getString("specialDiet"));
                result.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.accepted().body(result);
    }


    @PostMapping(
            value = "/updateAnimal",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HashMap> updateAnimal(@RequestBody HashMap<String, Object> payload) {
        LinkedHashMap temp = (LinkedHashMap) payload.get("animal");
        Animal newAnimal = new Animal();
        newAnimal.setAge((Integer) temp.get("age"));
        newAnimal.setWeight((Integer) temp.get("weight"));
        newAnimal.setCoatColor((String) temp.get("color"));
        newAnimal.setPermanentLocation((String) temp.get("location"));
        newAnimal.setPurpose((String) temp.get("purpose"));

        try {
            Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/vetapplication", "root", "Charlotte1");
            String query = "UPDATE ANIMAL SET age = ?, weight = ?, coatColor = ?, purpose = ?, permanentLocation = ? WHERE  animalID = 105;";
            PreparedStatement p1;
            p1 = myConnect.prepareStatement(query);
            p1.setInt(1, newAnimal.getAge());
            p1.setInt(2, newAnimal.getWeight());
            p1.setString(3, newAnimal.getCoatColor());
            p1.setString(4, newAnimal.getPurpose());
            p1.setString(5, newAnimal.getPermanentLocation());

            int r = p1.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

        HashMap response = HelperMethods.response("Updated animal");
        return ResponseEntity.accepted().body(response);
    }

    @GetMapping("/animalProfile")
    public ResponseEntity<Animal> viewAnimal(@RequestBody Animal animal) {
        int animalID = animal.getAnimalID();

        // TODO: retrieve animal profile from db from the ID
        boolean animalFound = true;
        if (animalFound) {
            return ResponseEntity.accepted().body(animal);
        } else {
            throw new RequestException("Couldn't find the animal.");
        }
    }

    @PostMapping(value = "/animalComments",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList> getComments(@RequestBody HashMap<String, String> req) {
        System.out.println("--------IN GET COMMENTS----------");
        System.out.println(req.get("animalID"));
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vetapplication", "root", "Charlotte1");
            String query = "SELECT commentText, userID FROM comments WHERE animalID=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(req.get("animalID")));
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                String text = res.getString(1);
                int userID = res.getInt(2);
                String query2 = "SELECT username FROM users WHERE userID = ?";
                PreparedStatement statement2 = conn.prepareStatement(query2);
                statement2.setInt(1, userID);
                ResultSet res2 = statement2.executeQuery();
                while (res2.next()) {
                    String username = res2.getString(1);
                    Comment newComment = new Comment();
                    newComment.setText(text);
                    newComment.setUser(username);
                    comments.add(newComment);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.ok().body(comments);
    }

    @PostMapping(value = "/postComment",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, String> blockUser(@RequestBody HashMap<String, String> req) {
        HashMap<String, String> map = new HashMap<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vetapplication", "root", "Charlotte1");
            String query = "INSERT INTO COMMENTS (animalID, commentText, userID) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(req.get("animalID")));
            statement.setString(2, req.get("text"));
            statement.setInt(3, Integer.parseInt(req.get("userID")));
            statement.executeUpdate();
            map.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "failure");
        }
        return map;
    }

    @PostMapping(value = "/requestAnimal",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HashMap> requestAnimal(@RequestBody HashMap<String, String> req) {
        HashMap<String, String> userFeedback = new HashMap<>();
        System.out.println(req.get("animalID"));
        try {
            long milis_from = Long.valueOf(req.get("fromDate"));
            long milis_to = Long.valueOf(req.get("toDate"));
            Date fromDate = new Date(milis_from);
            Date toDate = new Date(milis_to);

            // Creating animal borrow object
            AnimalBorrow ab = new AnimalBorrow();
            ab.setAnimalID(Integer.parseInt(req.get("animalID")));
            ab.setBorrowDate(fromDate);
            ab.setReturnDate(toDate);
            ab.setBorrowerID(Integer.parseInt(req.get("userID")));
            ab.setPurpose(req.get("purpose"));
            // Write sql query
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vetapplication", "root", "Charlotte1");
            String query = "INSERT into ANIMAL_BORROW (animalID, teachingTechnicianID, borrowDate, returnDate, purpose) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, ab.getAnimalID());
            statement.setInt(2, ab.getBorrowerID());
            statement.setDate(3, (java.sql.Date) ab.getBorrowDate());
            statement.setDate(4, (java.sql.Date) ab.getReturnDate());
            statement.setString(5, ab.getPurpose());
            statement.executeUpdate();
            userFeedback.put("result", "success");
        } catch (Exception e) {
            System.out.println(e);
            userFeedback.put("result", "error");
        }
        return ResponseEntity.ok().body(userFeedback);
    }
}
