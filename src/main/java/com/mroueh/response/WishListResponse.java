package com.mroueh.response;

import lombok.Data;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;


@Data
public class WishListResponse {

    private Long sizeId;

    private Long colorId;

    private String productName;

    private double unitPrice;

    private List<String> images;

    private String color;

    private List<AvailableSize> sizes = new ArrayList<>();
}
