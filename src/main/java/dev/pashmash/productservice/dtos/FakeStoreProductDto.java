package dev.pashmash.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private Double price;
    private String category;
    private String description;
    private String image;
}
