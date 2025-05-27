package com.property.platform.console.impl;

import com.property.platform.console.Command;
import com.property.platform.service.UserService;

public class LogoutCommand implements Command {
    private final UserService userService = UserService.getInstance();

    @Override
    public void execute() {
        userService.logout();
    }
}
