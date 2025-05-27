package com.property.platform.console.impl;

import com.property.platform.console.Command;
import com.property.platform.dto.SearchCriteria;
import com.property.platform.exception.CommandException;
import com.property.platform.model.Property;
import com.property.platform.service.PropertyService;
import com.property.platform.service.UserService;

import java.util.List;

public class SearchCommand implements Command {
    private final String args;
    private final UserService userService = UserService.getInstance();
    private final PropertyService propertyService = PropertyService.getInstance();

    public SearchCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute() {
        userService.getActiveUser()
                .orElseThrow(() -> new CommandException("Login first to search."));
        SearchCriteria crit = SearchCriteria.parse(args);
        List<Property> results = propertyService.search(crit);

        System.out.printf("%-4s %-20s %-12s %-10s %-12s %-6s %-6s %-10s%n",
                "ID", "Title", "Location", "Price", "Size(sqft)", "Rooms", "Type", "Status");
        for (Property p : results) {
            System.out.printf("%-4d %-20s %-12s %-10s %-12.2f %-6s %-6s %-10s%n",
                    p.getId(), p.getTitle(), p.getLocation(),
                    p.getPrice(), p.getSizeSqFt(),
                    p.getRooms(), p.getType(), p.isSold() ? "Sold" : "Avail");
        }
    }
}