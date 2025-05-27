package com.property.platform.dto;

import com.property.platform.exception.CommandException;
import com.property.platform.model.ListingType;
import com.property.platform.util.Utilities.PriceParser;
import com.property.platform.util.Utilities.SizeParser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class ListingRequest {
    @NotBlank private String title;
    @NotBlank private String location;
    @NotBlank @Pattern(regexp="\\d+(k|K|l|L|cr|CR)?") private String price;
    private ListingType type;
    @NotBlank private String size;
    @NotBlank private String rooms;
    private List<String> nearby;

    public static ListingRequest parse(String input) {
        try {
            Map<String, String> map = Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(s -> s.split("=", 2))
                    .filter(a -> a.length == 2)
                    .collect(Collectors.toMap(a -> a[0].toLowerCase(), a -> a[1]));

            ListingRequest r = new ListingRequest();
            r.title = map.getOrDefault("title", "");
            r.location = map.getOrDefault("location", "");
            r.price = map.getOrDefault("price", "");
            r.type = ListingType.valueOf(map.getOrDefault("type", "SELL").toUpperCase());
            r.size = map.getOrDefault("size", "");
            r.rooms = map.getOrDefault("rooms", "");
            String nb = map.getOrDefault("nearby", "");
            r.nearby = nb.isEmpty() ? List.of() :
                    Arrays.stream(nb.split("\\|"))
                            .map(String::trim)
                            .map(String::toLowerCase)
                            .collect(Collectors.toList());
            return r;
        } catch (Exception e) {
            throw new CommandException("Invalid LISTPROPERTY args");
        }
    }
    public long getParsedPrice() { return PriceParser.parse(price); }
    public double getParsedSizeSqFt() { return SizeParser.toSqFt(size); }
    public List<String> getNearbyLocations() { return nearby; }
}
