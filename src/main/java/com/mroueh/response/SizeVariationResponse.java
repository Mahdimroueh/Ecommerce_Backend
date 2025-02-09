package com.mroueh.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SizeVariationResponse {

    private Long id;

    private Long sizeOptionId;

    private String sizeName;

    private int quantityInStock;

    private String code;
}
