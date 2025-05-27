package com.property.platform.service;

import com.property.platform.exception.ServiceException;
import com.property.platform.model.User;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private volatile String active = null;
    private UserService() {}
    public static UserService getInstance() { return INSTANCE; }
    public void register(String username) {
        if (username.isBlank())
            System.out.println("Username required");
        else if (users.containsKey(username))
            System.out.println("User exists");
        else {
            users.put(username, new User(username));
            System.out.println("Registered " + username);
        }
    }
    public void login(String username) {
        var user = users.get(username);
        if (user==null) {
            System.out.println("No such user");
        }
        else {
            if (active != null) System.out.println("Another user active! Updating active user.");
            active = username;
            System.out.println("Welcome " + username);
        }
    }
    public void logout() {
        if (active==null) throw new ServiceException("No user logged in");
        System.out.println("Goodbye "+active);
        active=null;
    }
    public Optional<User> getActiveUser() { return Optional.ofNullable(active).map(users::get); }
}