package dev.pashmash.productservice.repositories;

import dev.pashmash.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository
        extends JpaRepository<Category, UUID> {
    Optional<Category> findById(UUID uuid);

    List<Category> findAllById(Iterable<UUID> uuids);


}
