package com.beadando.tarskereso.Controller;


import com.beadando.tarskereso.model.Nem_enum;
import com.beadando.tarskereso.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;


@RestController
public class UserServiceController {

    @CrossOrigin
    @RequestMapping(value = "/getuser", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(){

        User testuser = new User(1, "Márk King", 20, "vices memik", Boolean.TRUE);
        testuser.setNem(Nem_enum.ferfi);
        return new ResponseEntity<>(testuser, HttpStatus.OK);
    }


}



