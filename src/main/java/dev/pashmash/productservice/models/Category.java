package dev.pashmash.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseModel {
    private String name;
    @OneToMany(fetch = jakarta.persistence.FetchType.EAGER, mappedBy = "category")
    private List<Product> products = new ArrayList<>();
}
