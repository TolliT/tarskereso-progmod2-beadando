package com.beadando.tarskereso.services;

import com.beadando.tarskereso.model.User;
import java.util.ArrayList;

public interface Tarskereso_interface {
    void toFile(String path, User user);
    ArrayList<User> fromFile(String path);

    ArrayList<User> userFilter(String gender, Integer minAge, Integer maxAge);

    void flushDB(String path);
}
