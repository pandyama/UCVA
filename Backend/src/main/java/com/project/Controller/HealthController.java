package com.project.Controller;

import com.project.Exception.RequestException;
import com.project.Helper.HelperMethods;
import com.project.Model.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

@CrossOrigin
@RestController
public class HealthController {

    @PostMapping(
            value = "/diagnose",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HashMap> diagnoseAnimal(@RequestBody HashMap<String, Object> payload) {
        LinkedHashMap temp = (LinkedHashMap) payload.get("diagnosisDetail");
        AnimalDiagnosis ad = new AnimalDiagnosis();
        System.out.println((String) temp.get("details"));
        System.out.println((String) temp.get("time"));
        ad.setDiagnosisDetail((String) temp.get("details"));
        ad.setUID((Integer) temp.get("userID"));
        ad.setSubmissionDate((String) temp.get("time"));
        ad.setAnimalID((Integer) temp.get("animalID"));

        try{
            Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/vetapplication", "root", "Charlotte1");
            String query = "INSERT INTO ANIMAL_DIAGNOSIS (animalID, diagnosisDetail,\n" +
                    "submissionDate, healthTechnicianID)\n" +
                    "VALUES ('"+ad.getAnimalID()+"'," +
                    "'"+ad.getDiagnosisDetail()+"'," +
                    "'"+ad.getSubmissionDate()+"'," +
                    "'"+ad.getUID()+"');";
            PreparedStatement p1;
            p1 = myConnect.prepareStatement(query);
            int r = p1.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e);
        }
        HashMap response = HelperMethods.response("Added diagnosis");
        return ResponseEntity.accepted().body(response);
    }

    @PostMapping(
            value = "/allDiagnosis",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList> getAllDiagnosis(@RequestBody HashMap<String, Integer> payload) {

        ArrayList result = new ArrayList();
        HashMap response = new HashMap();

        try{
            Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/vetapplication", "root", "Charlotte1");
            String query = String.format("SELECT * FROM ANIMAL_DIAGNOSIS WHERE animalID = %d",payload.get("id"));
            PreparedStatement p1;
            p1 = myConnect.prepareStatement(query);
            ResultSet r1 = p1.executeQuery(query);
            ArrayList diagnosis = new ArrayList();
            while(r1.next()){
                diagnosis.add(r1.getInt("diagnoseID"));
                diagnosis.add(r1.getString("diagnosisDetail"));
                diagnosis.add(r1.getString("submissionDate"));
                response.put("diagnosis",diagnosis);
                result.add(response);
                diagnosis = new ArrayList();
                response = new HashMap();
            }
            return ResponseEntity.accepted().body(result);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return ResponseEntity.accepted().body(result);
    }

    @PostMapping(
            value = "/prescription",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HashMap> prescribeAnimal(@RequestBody HashMap<String, Object> payload) {

        LinkedHashMap temp = (LinkedHashMap) payload.get("prescriptionDetail");
        AnimalPrescription ap = new AnimalPrescription();
        PrescriptionItem pItem = new PrescriptionItem();
        System.out.println((String) temp.get("details"));
        System.out.println((String) temp.get("time"));
        ap.setDiagnosisID((Integer) temp.get("diagnosisID"));
        ap.setSubmissionDate((String) temp.get("time"));
        pItem.setPrescriptionDetail((String) temp.get("details"));
        try{
            Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/vetapplication", "root", "Charlotte1");
            String query = "INSERT INTO ANIMAL_PRESCRIPTION (diagnoseID, submissionDate)\n" +
                    "VALUES ('"+ap.getDiagnosisID()+"'," +
                    "'"+ap.getSubmissionDate()+"');";
            String query2 = "SELECT LAST_INSERT_ID();";
            PreparedStatement p1, p2, p3;
            p1 = myConnect.prepareStatement(query);
            p2 = myConnect.prepareStatement(query2);
            int r = p1.executeUpdate();
            ResultSet r2 = p2.executeQuery(query2);

            while(r2.next()){
                System.out.println(r2.getInt(1));
                pItem.setPrescriptionID(r2.getInt(1));
                String query3 = "INSERT INTO PRESCRIPTION_ITEM (prescriptionDetail,\n" +
                        "prescriptionID)\n" +
                        "VALUES ('"+pItem.getPrescriptionDetail()+"'," +
                        "'"+pItem.getPrescriptionID()+"');";
                p3 = myConnect.prepareStatement(query3);
                int rt = p3.executeUpdate();
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        HashMap response = HelperMethods.response("Added prescription");
        return ResponseEntity.accepted().body(response);
    }


    @PostMapping(
            value = "/treatmentRequest",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HashMap> treatAnimal(@RequestBody HashMap<String, Object> payload) {

        LinkedHashMap temp = (LinkedHashMap) payload.get("treatmentDetails");
        AnimalTreatment t1 = new AnimalTreatment();
        TreatmentType type = new TreatmentType();
        t1.setAnimalID((Integer) temp.get("animalID"));
        t1.setCareAttendantID((Integer) temp.get("userID"));
        t1.setTreatmentStatus((String) temp.get("status"));
        t1.setSubmissionDate((String) temp.get("time"));
        type.setTreatmentDescription((String) temp.get("details"));

        try{
            Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/vetapplication", "root", "Charlotte1");
            String query = "INSERT INTO ANIMAL_TREATMENT (treatmentStatus,\n" +
                    "submissionDate, animalID, careAttendantID)\n" +
                    "VALUES ('"+t1.getTreatmentStatus()+"'," +
                    "'"+t1.getSubmissionDate()+"'," +
                    "'"+t1.getAnimalID()+"'," +
                    "'"+t1.getCareAttendantID()+"');";
            String query2 = "SELECT LAST_INSERT_ID();";
            PreparedStatement p1, p2, p3;
            p1 = myConnect.prepareStatement(query);
            p2 = myConnect.prepareStatement(query2);
            int r = p1.executeUpdate();
            ResultSet r2 = p2.executeQuery(query2);

            while(r2.next()){
                System.out.println(r2.getInt(1));
                type.setTreatmentID(r2.getInt(1));
                String query3 = "INSERT INTO TREATMENT_TYPE (treatmentDescription,\n" +
                        "treatmentID)\n" +
                        "VALUES ('"+type.getTreatmentDescription()+"'," +
                        "'"+type.getTreatmentID()+"');";
                p3 = myConnect.prepareStatement(query3);
                int rt = p3.executeUpdate();
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        if (1 == 1) {
            HashMap response = HelperMethods.response("Sent Treatment request");
            return ResponseEntity.accepted().body(response);
        } else {
            throw new RequestException("Failed to submit treatment request data");
        }
    }

    @GetMapping(value = "/getHealth")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ArrayList> getHealth(){
        ArrayList b = new ArrayList();
        try {
            Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/vetapplication", "root", "Charlotte1");
            String query = "SELECT healthStatus, specialInstruction, specialDiet FROM ANIMAL_HEALTH;";
            PreparedStatement p1;
            p1 = myConnect.prepareStatement(query);
            ResultSet rs = p1.executeQuery(query);
            while (rs.next()) {
                HashMap<String, Object> a = new HashMap<String, Object>();
                a.put("healthStatus", rs.getString("healthStatus"));
                a.put("specialInstruction", rs.getString("specialInstruction"));
                a.put("specialDiet", rs.getString("specialDiet"));
                b.add(a);
                System.out.println(a);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return ResponseEntity.accepted().body(b);
    }

    @PostMapping(
            value = "/updateHealth",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HashMap> updateHealth(@RequestBody HashMap<String, Object> payload) {
        int animalID = (Integer) payload.get("animalID");
        String healthStatus = (String) payload.get("healthStatus");
        System.out.println(animalID);
        System.out.println(healthStatus);


        try {
            Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/vetapplication", "root", "Charlotte1");
            String query = "UPDATE ANIMAL_HEALTH SET healthStatus = ? WHERE  animalID = ?;";
            PreparedStatement p1;
            p1 = myConnect.prepareStatement(query);
            p1.setString(1, healthStatus);
            p1.setInt(2, animalID);

            int r = p1.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        HashMap response = HelperMethods.response("Updated health");
        return ResponseEntity.accepted().body(response);
    }

    @PostMapping(
            value = "/uploadPhoto",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> updateHealth(@RequestBody String s) {
        if (1 == 1) {
            System.out.println(s);
            String response = "successfully uploaded photo";
            return ResponseEntity.accepted().body(response);
        } else {
            throw new RequestException("Failed to submit treatment request data");
        }
    }

}

