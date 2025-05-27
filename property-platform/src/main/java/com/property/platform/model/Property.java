package com.property.platform.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
public class Property {
    private final int id;
    private String title;
    private String location;
    private long price;
    private ListingType type;
    private double sizeSqFt;
    private String rooms;
    private List<String> nearby;
    private boolean sold = false;
    private final String owner;

    public Property(int id, @NotBlank String title, @NotBlank String location, long parsedPrice, ListingType type, double parsedSizeSqFt, @NotBlank String rooms, List<String> nearbyLocations, String name) {
        this.id = id;
        this.owner = name;
        this.title = title;
        this.location = location;
        this.price = parsedPrice;
        this.type = type;
        this.sizeSqFt = parsedSizeSqFt;
        this.rooms = rooms;
        this.nearby = nearbyLocations;
    }

    public String toString() {
        return String.format("%d | %s | %s | %d | %.2f sqft | %s | %s | %s",
                id, title, location, price, sizeSqFt, rooms, type, sold ? "Sold" : "Available");
    }
}