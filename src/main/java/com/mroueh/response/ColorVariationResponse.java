package com.mroueh.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Data
@Getter
@Setter
public class ColorVariationResponse {
    private Long id;

    private List<String> images = new ArrayList<>();

    private String ColorName;

    private List<SizeVariationResponse> sizeVariationsResponses = new ArrayList<>();
}
