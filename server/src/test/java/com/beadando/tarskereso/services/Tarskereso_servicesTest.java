package com.beadando.tarskereso.services;

import com.beadando.tarskereso.model.Nem_enum;
import com.beadando.tarskereso.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Tarskereso_servicesTest {
    @Test
    void shouldFilterWrongGender(){
        ArrayList<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setNem(Nem_enum.no);
        user1.setNev("Test1");
        user1.setKor(19);
        user1.setLeiras("");
        users.add(user1);
        User user = new User();
        user.setId(2);
        user.setNem(Nem_enum.ferfi);
        user.setNev("Test2");
        user.setKor(19);
        user.setLeiras("");
        users.add(user);
        Tarskereso_services ts = new Tarskereso_services();
        ArrayList<User> filtered = ts.userFilter(users, "no", 18, 99);
        assertEquals(1,filtered.size());
    }

    @Test
    void shouldFilterTooOld(){
        ArrayList<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setNem(Nem_enum.no);
        user1.setNev("Test1");
        user1.setKor(19);
        user1.setLeiras("");
        users.add(user1);
        User user = new User();
        user.setId(2);
        user.setNem(Nem_enum.no);
        user.setNev("Test2");
        user.setKor(99);
        user.setLeiras("");
        users.add(user);
        Tarskereso_services ts = new Tarskereso_services();
        ArrayList<User> filtered = ts.userFilter(users, "no", 18, 98);
        assertEquals(1,filtered.size());
    }

    @Test
    void shouldFilterTooYoung(){
        ArrayList<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setNem(Nem_enum.no);
        user1.setNev("Test1");
        user1.setKor(17);
        user1.setLeiras("");
        users.add(user1);
        User user = new User();
        user.setId(2);
        user.setNem(Nem_enum.no);
        user.setNev("Test2");
        user.setKor(18);
        user.setLeiras("");
        users.add(user);
        Tarskereso_services ts = new Tarskereso_services();
        ArrayList<User> filtered = ts.userFilter(users, "no", 18, 99);
        assertEquals(1,filtered.size());
    }
}