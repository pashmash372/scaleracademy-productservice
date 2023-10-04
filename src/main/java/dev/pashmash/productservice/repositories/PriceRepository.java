package dev.pashmash.productservice.repositories;

import dev.pashmash.productservice.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository
        extends JpaRepository<Price, Long> {
}