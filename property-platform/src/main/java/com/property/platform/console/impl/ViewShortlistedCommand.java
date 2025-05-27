package com.property.platform.console.impl;

import com.property.platform.console.Command;
import com.property.platform.exception.CommandException;
import com.property.platform.model.Property;
import com.property.platform.model.User;
import com.property.platform.service.ShortlistService;
import com.property.platform.service.UserService;

import java.util.List;

public class ViewShortlistedCommand implements Command {
    private final UserService userService = UserService.getInstance();
    private final ShortlistService shortlistService = ShortlistService.getInstance();

    @Override
    public void execute() {
        User user = userService.getActiveUser()
                .orElseThrow(() -> new CommandException("Login first."));
        List<Property> list = shortlistService.viewShortlisted(user);

        list.forEach(p -> System.out.println(p));
    }
}
