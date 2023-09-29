package dev.pashmash.productservice.dtos;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericProductDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}