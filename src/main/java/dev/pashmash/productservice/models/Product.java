package dev.pashmash.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends  BaseModel{
    private String title;
    private String description;
    private String image;
    @ManyToOne
    private Category category;
    private Double price;
}

//            P : C
// => L to R: 1 : 1
// => R to L: m : 1
// => Ans:    m : 1