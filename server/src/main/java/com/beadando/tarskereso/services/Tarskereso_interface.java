package com.beadando.tarskereso.services;


import com.beadando.tarskereso.model.User;

import java.util.List;

public interface Tarskereso_interface {
    public void toFile(String path, User user);
    public List<User> fromFile(String path);

}
