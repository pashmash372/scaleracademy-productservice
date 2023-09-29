package dev.pashmash.productservice.controllers;

import dev.pashmash.productservice.dtos.GenericProductDto;
import dev.pashmash.productservice.exceptions.NotFoundException;
import dev.pashmash.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {

    private final ProductService productService;

    @Value("${productservice.type}")
    private String className;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
        return productService.createProduct(product);
    }

    // GET /products {}
    @GetMapping
    public List<GenericProductDto> getAllProducts() {
        return productService.getProducts();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericProductDto> updateProductById(@RequestBody GenericProductDto product, @PathVariable("id") Long id) {
        return new ResponseEntity<>(productService.updateProduct(product, id), HttpStatus.OK);
    }

}
