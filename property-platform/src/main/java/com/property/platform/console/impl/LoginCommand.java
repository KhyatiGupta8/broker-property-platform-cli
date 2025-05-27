package com.property.platform.console.impl;

import com.property.platform.console.Command;
import com.property.platform.exception.CommandException;
import com.property.platform.service.UserService;

public class LoginCommand implements Command {
    private final String username;
    private final UserService userService = UserService.getInstance();

    public LoginCommand(String args) {
        this.username = args.trim();
    }

    @Override
    public void execute() {
        if (username.isBlank()) {
            throw new CommandException("Usage: LOGIN <username>");
        } else {
            userService.login(username);
        }
    }
}