package dev.pashmash.productservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category extends BaseModel{
    private String name;
}
