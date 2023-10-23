package dev.pashmash.productservice.services;

import dev.pashmash.productservice.dtos.GenericProductDto;
import dev.pashmash.productservice.exceptions.NotFoundException;

import java.util.List;


public interface ProductService {
    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto getProductById(Long id) throws NotFoundException;

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProduct(Long id);

    GenericProductDto updateProduct(GenericProductDto product, Long id);

}
