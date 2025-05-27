package com.property.platform.dto;

import com.property.platform.model.ListingType;
import com.property.platform.util.Utilities;

import java.util.*;
import java.util.stream.Collectors;

public class SearchCriteria {
    private List<String> locations;
    private Utilities.Range priceRange;
    private ListingType type;
    private Utilities.Range sizeRange;
    private List<String> rooms;
    private String sortBy;

    public static SearchCriteria parse(String input) {
        SearchCriteria crit = new SearchCriteria();
        String[] parts = input.split(",");
        for (String p : parts) {
            String[] kv = p.split("=", 2);
            if (kv.length < 2) continue;
            String k = kv[0].trim().toLowerCase();
            String v = kv[1].trim();
            switch (k) {
                case "location" -> crit.locations = Arrays.stream(v.split("\\|"))
                        .map(String::trim).map(String::toLowerCase).collect(Collectors.toList());
                case "pricerange" -> crit.priceRange = Utilities.Range.parseLong(v);
                case "type" -> crit.type = ListingType.valueOf(v.toUpperCase());
                case "sizerange" -> crit.sizeRange = Utilities.Range.parseDouble(v);
                case "rooms" -> crit.rooms = Arrays.stream(v.split("\\|"))
                        .map(String::trim).collect(Collectors.toList());
                case "sort" -> crit.sortBy = v.toLowerCase();
            }
        }
        return crit;
    }

    public boolean matches(com.property.platform.model.Property p) {
        if (locations != null && !locations.contains(p.getLocation())) return false;
        if (type != null && p.getType() != type) return false;
        if (priceRange != null && !priceRange.includes(p.getPrice())) return false;
        if (sizeRange != null && !sizeRange.includes(p.getSizeSqFt())) return false;
        if (rooms != null && !rooms.contains(p.getRooms())) return false;
        return true;
    }

    public Comparator<com.property.platform.model.Property> getComparator() {
        return switch (sortBy) {
            case "price" -> Comparator.comparingLong(com.property.platform.model.Property::getPrice);
            case "size" -> Comparator.comparingDouble(com.property.platform.model.Property::getSizeSqFt);
            default -> Comparator.comparingInt(com.property.platform.model.Property::getId);
        };
    }
}