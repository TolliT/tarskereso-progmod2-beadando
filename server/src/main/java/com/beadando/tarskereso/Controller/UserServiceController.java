package com.beadando.tarskereso.Controller;


import com.beadando.tarskereso.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
public class UserServiceController {

    @RequestMapping(value = "/getuser", method = RequestMethod.POST)
    public ResponseEntity<Object> getUser(){
        ArrayList<Integer> likeok= new ArrayList<>();
        likeok.add(42);


        User testuser = new User(1, "Márk King", 20, "vices memik", likeok);

        return new ResponseEntity<>(testuser, HttpStatus.OK);
    }

}



