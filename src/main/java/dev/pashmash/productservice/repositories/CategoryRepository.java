package dev.pashmash.productservice.repositories;

import dev.pashmash.productservice.models.Category;
import dev.pashmash.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository
        extends JpaRepository<Category, UUID> {
}
