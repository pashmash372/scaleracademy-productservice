package dev.pashmash.productservice.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class Product extends  BaseModel{
    private String title;
    private String description;
    private String image;
    private Category category;
    private Double price;
}
