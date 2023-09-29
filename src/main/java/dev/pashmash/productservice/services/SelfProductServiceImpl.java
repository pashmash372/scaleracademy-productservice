package dev.pashmash.productservice.services;

import dev.pashmash.productservice.dtos.GenericProductDto;
import dev.pashmash.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfProductServiceImpl implements  ProductService{
    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }
    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }
    @Override
    public List<GenericProductDto> getProducts() {
        return null;
    }
    @Override
    public GenericProductDto deleteProduct(Long id) {
        return null;
    }
    @Override
    public GenericProductDto updateProduct(GenericProductDto product, Long id) {
        return null;
    }
}
