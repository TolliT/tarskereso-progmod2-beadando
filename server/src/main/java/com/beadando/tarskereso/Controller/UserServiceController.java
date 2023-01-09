package com.beadando.tarskereso.Controller;


import com.beadando.tarskereso.model.Nem_enum;
import com.beadando.tarskereso.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Map;


@RestController
public class UserServiceController {

    public static ArrayList<User> Filtered = new ArrayList<>();

    @CrossOrigin
    @RequestMapping(value = "/getuser", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(){

        User testuser = new User(1, "Márk King", 20, "vices memik", Boolean.TRUE);
        testuser.setNem(Nem_enum.ferfi);
        return new ResponseEntity<>(testuser, HttpStatus.OK);
    }



    @CrossOrigin
    @PostMapping("/prefs")
    public ResponseEntity<String> submit(@RequestBody Map<String, Object> payload) {
        for (Map.Entry<String, Object> entry : payload.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());

        }



        return ResponseEntity.ok("success");
    }
}





