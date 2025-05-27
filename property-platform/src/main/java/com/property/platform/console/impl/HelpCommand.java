package com.property.platform.console.impl;

import com.property.platform.console.Command;

public class HelpCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Available commands:");
        System.out.println("  REGISTER <username>");
        System.out.println("  LOGIN <username>");
        System.out.println("  LOGOUT");
        System.out.println("  LISTPROPERTY title=<...>,location=<...>,price=<...>,type=<SELL|RENT>,size=<...>,rooms=<...>[,nearby=<a|b|c>]");
        System.out.println("  SEARCH location=<x|y>,pricerange=<min>-<max>,type=<SELL|RENT>,sizerange=<min>-<max>,rooms=<1BHK|2BHK>,sort=<price|size>");
        System.out.println("  SHORTLIST <propertyId>");
        System.out.println("  VIEWSHORTLISTED");
        System.out.println("  VIEWLISTED");
        System.out.println("  MARKSOLD <propertyId>");
        System.out.println("  HELP");
        System.out.println("  EXIT");
    }
}
