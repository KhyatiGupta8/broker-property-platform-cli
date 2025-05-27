package com.property.platform.console;

import com.property.platform.console.impl.*;
import com.property.platform.exception.CommandException;
import org.springframework.stereotype.Component;


@Component
public class CommandFactory {
    public Command get(String line) {
        var parts = line.split(" ",2);
        var key = parts[0].toUpperCase();
        var args = parts.length>1 ? parts[1].trim() : "";
        return switch (key) {
            case "REGISTER" -> new RegisterCommand(args);
            case "LOGIN"    -> new LoginCommand(args);
            case "LOGOUT"   -> new LogoutCommand();
            case "LISTPROPERTY" -> new ListPropertyCommand(args);
            case "SEARCH"   -> new SearchCommand(args);
            case "SHORTLIST"-> new ShortlistCommand(args);
            case "VIEWSHORTLISTED" -> new ViewShortlistedCommand();
            case "VIEWLISTED"-> new ViewListedCommand();
            case "MARKSOLD" -> new MarkSoldCommand(args);
            case "HELP"     -> new HelpCommand();
            default -> throw new CommandException("Unknown command: " + key);
        };
    }
}