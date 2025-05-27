package com.property.platform.service;

import com.property.platform.dto.ListingRequest;
import com.property.platform.dto.SearchCriteria;
import com.property.platform.exception.ServiceException;
import com.property.platform.model.Property;
import com.property.platform.model.User;
import com.property.platform.util.Utilities.IdGenerator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PropertyService {
    private static final PropertyService INSTANCE = new PropertyService();
    private final Map<Integer, Property> listings = new ConcurrentHashMap<>();
    private PropertyService() {}
    public static PropertyService getInstance() { return INSTANCE; }
    public void createListing(ListingRequest req, User owner) {
        int id = IdGenerator.next();
        Property p = new Property(id, req.getTitle(), req.getLocation(), req.getParsedPrice(),
                req.getType(), req.getParsedSizeSqFt(), req.getRooms(), req.getNearbyLocations(), owner.getName());
        listings.put(id,p);
        System.out.println("Listing created successfully.");
    }
    public List<Property> search(SearchCriteria crit) {
        return listings.values().stream()
                .filter(p->!p.isSold())
                .filter(crit::matches)
                .sorted(crit.getComparator())
                .collect(Collectors.toList());
    }
    public List<Property> viewListed(String username) {
        return listings.values().stream()
                .filter(p->p.getOwner().equals(username))
                .collect(Collectors.toList());
    }
    public void markSold(int id, String username) {
        var p=listings.get(id);
        if(p==null) throw new ServiceException("No such property");
        if(!p.getOwner().equals(username)) throw new ServiceException("Not your property");
        if(p.isSold()) throw new ServiceException("Already sold");
        p.setSold(true);
        System.out.println("Marked sold");
    }
}
