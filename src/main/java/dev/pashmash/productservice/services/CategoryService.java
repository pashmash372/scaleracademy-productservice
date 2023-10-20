package dev.pashmash.productservice.services;

import dev.pashmash.productservice.models.Category;

import java.util.List;


public interface CategoryService {
    Category getCategory(String uuid);
    List<String> getProductTitles(List<String> categoryUUIDs);
}
