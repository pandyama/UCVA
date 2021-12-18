package com.project.Controller;

import com.project.Helper.HelperMethods;
import com.project.Model.DiseaseAlert;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

@CrossOrigin
@RestController
public class DiseaseController {

    @GetMapping(value = "/viewAlerts")
    public ResponseEntity<ArrayList<DiseaseAlert>> getDiseaseAlert() {
        System.out.println("Hit /viewAlerts");
        ArrayList<DiseaseAlert> diseaseAlertList = new ArrayList<DiseaseAlert>();
        try{
            Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/vetapplication", "root", "Charlotte1");
            String query = "SELECT * FROM disease_alerts;";
            PreparedStatement p1;
            p1 = myConnect.prepareStatement(query);
            ResultSet rs = p1.executeQuery(query);
            while(rs.next()) {
                DiseaseAlert d = new DiseaseAlert();
                d.setAlertID(rs.getInt("alertID"));
                d.setCareAttendantID(rs.getInt("careAttendantID"));
                d.setReportedDate(rs.getString("reportedDate"));
                d.setAlertDetails(rs.getString("alertDetails"));
                d.setLocationAffected(rs.getString("locationAffected"));
                d.setOutbreakStatus(rs.getString("outbreakStatus"));
                diseaseAlertList.add(d);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
            return ResponseEntity.accepted().body(diseaseAlertList);
    }

    @PostMapping(
            value = "/addAlert",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HashMap> diagnoseAnimal(@RequestBody HashMap<String, Object> payload) {

        LinkedHashMap temp = (LinkedHashMap) payload.get("alert");
        DiseaseAlert dAlert = new DiseaseAlert();
        dAlert.setLocationAffected((String) temp.get("location"));
        dAlert.setAlertDetails((String) temp.get("alert"));
        dAlert.setOutbreakStatus((String) temp.get("status"));
        dAlert.setSubmissionDate((String) temp.get("time"));
        dAlert.setCareAttendantID((Integer) temp.get("user"));

        try{
            Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/vetapplication", "root", "Charlotte1");
            String query = "INSERT INTO DISEASE_ALERTS (careAttendantID, reportedDate,\n" +
                    "alertDetails, locationAffected, outbreakStatus)\n" +
                    "VALUES ('"+dAlert.getCareAttendantID()+"'," +
                    "'"+dAlert.getSubmissionDate()+"'," +
                    "'"+dAlert.getAlertDetails()+"'," +
                    "'"+dAlert.getLocationAffected()+"'," +
                    "'"+dAlert.getOutbreakStatus()+"');";
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
}
