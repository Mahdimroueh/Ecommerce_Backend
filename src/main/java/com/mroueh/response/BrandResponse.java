package com.mroueh.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandResponse {

    private Long id;
    private String name;
    private String brandDescription;
    private String logo;
}
