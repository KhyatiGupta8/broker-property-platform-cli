package com.property.platform.service;

import com.property.platform.exception.ServiceException;
import com.property.platform.model.Property;
import com.property.platform.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class ShortlistService {
    private static final ShortlistService INSTANCE = new ShortlistService();
    private ShortlistService() {}
    public static ShortlistService getInstance() { return INSTANCE; }
    public void shortlist(int id, User user) {
        if(user.getShortlist().contains(id)) throw new ServiceException("Already shortlisted");
        user.getShortlist().add(id);
        System.out.println("Shortlisted");
    }
    public List<Property> viewShortlisted(User user) {
        var all=PropertyService.getInstance().viewListed(user.getName());
        return all.stream().filter(p->user.getShortlist().contains(p.getId())).collect(Collectors.toList());
    }
}