package com.beadando.tarskereso.services;


import com.beadando.tarskereso.model.Nem_enum;
import com.beadando.tarskereso.model.User;

import java.util.ArrayList;
import java.util.List;

public interface Tarskereso_interface {
    public void toFile(String path, User user);
    public ArrayList<User> fromFile(String path);

    public ArrayList<User> userFilter(String gender, Integer minAge, Integer maxAge);

    public void flushDB(String path);
}
