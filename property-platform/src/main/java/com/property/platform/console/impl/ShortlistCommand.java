package com.property.platform.console.impl;

import com.property.platform.console.Command;
import com.property.platform.exception.CommandException;
import com.property.platform.model.User;
import com.property.platform.service.ShortlistService;
import com.property.platform.service.UserService;

public class ShortlistCommand implements Command {
    private final String args;
    private final UserService userService = UserService.getInstance();
    private final ShortlistService shortlistService = ShortlistService.getInstance();

    public ShortlistCommand(String args) {
        this.args = args.trim();
    }

    @Override
    public void execute() {
        User user = userService.getActiveUser()
                .orElseThrow(() -> new CommandException("Login first."));
        int id;
        try {
            id = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new CommandException("Usage: SHORTLIST <propertyId>");
        }
        shortlistService.shortlist(id, user);
    }
}