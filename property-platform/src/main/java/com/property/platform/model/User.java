package com.property.platform.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private final String name;
    // shortlist holds property IDs
    private final List<Integer> shortlist;
    public User(String name) { this.name = name; this.shortlist = new java.util.ArrayList<>(); }
}