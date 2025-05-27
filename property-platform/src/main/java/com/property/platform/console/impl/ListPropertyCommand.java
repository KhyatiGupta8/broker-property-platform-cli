package com.property.platform.console.impl;

import com.property.platform.console.Command;
import com.property.platform.dto.ListingRequest;
import com.property.platform.exception.CommandException;
import com.property.platform.model.User;
import com.property.platform.service.PropertyService;
import com.property.platform.service.UserService;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

public class ListPropertyCommand implements Command {
    private final String args;
    private final UserService userService = UserService.getInstance();
    private final PropertyService propertyService = PropertyService.getInstance();
    private final Validator validator;

    public ListPropertyCommand(String args) {
        this.args = args;
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();
    }

    @Override
    public void execute() {
        User user = userService.getActiveUser()
                .orElseThrow(() -> new CommandException("You aren't logged in."));
        ListingRequest req = ListingRequest.parse(args);
        Set<?> violations = validator.validate(req);
        if (!violations.isEmpty()) {
            String msg = violations.iterator().next().toString();
            throw new CommandException(msg);
        } else {
            propertyService.createListing(req, user);
        }
    }
}

