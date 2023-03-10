package com.beadando.tarskereso.Controller;


import com.beadando.tarskereso.model.User;
import com.beadando.tarskereso.services.Tarskereso_services;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;


@RestController
public class UserServiceController {
    Tarskereso_services service = new Tarskereso_services();
    String path="server/src/main/resources/filtered.csv";


    /**
     * Function handling the logic of sending user data to the frontend
     * @return  userData ResponseEntity<>() - User data returned in JSON form
     */
    @CrossOrigin
    @RequestMapping(value = "/getuser", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(){
        ArrayList<User> filtered=service.fromFile(path);
        User user=filtered.get(0);
        filtered.remove(0);
        service.flushDB(path);
        for (User value : filtered) {
            service.toFile(path, value);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);

    }


    /**
     * Function handling the logic of getting user preference data from the frontend
     * @param  payload ResponseEntity<>() - User preference data received from the frontend in JSON form
     */
    @CrossOrigin
    @PostMapping("/prefs")
    public ResponseEntity<String> submit(@RequestBody Map<String, Object> payload) {
        String genderPref="";
        int minAge=0;
        int maxAge=0;

        for (Map.Entry<String, Object> entry : payload.entrySet()) {
            if(entry.getKey().equals("genderPref")){
                genderPref=(String)entry.getValue();
            }
            else if(entry.getKey().equals("minAge")){
                minAge=Integer.parseInt((String)entry.getValue());
            }

            else{
                maxAge=Integer.parseInt((String)entry.getValue());
            }
        }

        service.flushDB(path);
        ArrayList<User> filtered=service.userFilter(genderPref, minAge, maxAge);

        for (User user : filtered) {
            service.toFile(path, user);
        }

        return ResponseEntity.ok("success");
    }
}





