package com.beadando.tarskereso.Controller;


import com.beadando.tarskereso.model.Nem_enum;
import com.beadando.tarskereso.model.User;
import com.beadando.tarskereso.services.Tarskereso_services;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class UserServiceController {
 Tarskereso_services service = new Tarskereso_services();
    public static ArrayList<User> Filtered = new ArrayList<>();

    @CrossOrigin
    @RequestMapping(value = "/getuser", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(){

        User testuser = new User(1, "Márk King", 20, "vices memik", Boolean.TRUE);
        testuser.setNem(Nem_enum.no);
        return new ResponseEntity<>(testuser, HttpStatus.OK);
    }



    @CrossOrigin
    @PostMapping("/prefs")
    public ResponseEntity<String> submit(@RequestBody Map<String, Object> payload) {
        String genderPref="";
        Integer minAge=0;
        Integer maxAge=0;

        for (Map.Entry<String, Object> entry : payload.entrySet()) {
            if(entry.getKey().equals("genderPref")){
                genderPref=entry.getValue().toString();
            }
            else if(entry.getKey().equals("minAge")){
                minAge=Integer.parseInt(entry.getValue().toString());
            }

            else{
                maxAge=Integer.parseInt(entry.getValue().toString());
            }

        }
        String path="server/src/main/resources/filtered.csv";

        service.flushDB(path);
        List<User> filtered=service.userFilter(genderPref, minAge, maxAge);

        for (User user : filtered) {
            service.toFile(path, user);
        }




        return ResponseEntity.ok("success");
    }
}





