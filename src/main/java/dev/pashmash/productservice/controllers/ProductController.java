package dev.pashmash.productservice.controllers;

import dev.pashmash.productservice.dtos.GenericProductDto;
import dev.pashmash.productservice.exceptions.NotFoundException;
import dev.pashmash.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable Long id) throws NotFoundException {
        if (productService.getProductById(id) == null)
            throw new NotFoundException("Product with id " + id + " not found");
        return productService.getProductById(id);
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
        return productService.createProduct(product);
    }

    // GET /products {}
    @GetMapping
    public ResponseEntity<List<GenericProductDto>> getAllProducts() {
        List<GenericProductDto> productDtos = productService.getAllProducts();
        if (productDtos.isEmpty()) {
            return new ResponseEntity<>(
                    productDtos,
                    HttpStatus.NOT_FOUND
            );
        }

        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        for (GenericProductDto gpd: productDtos) {
            genericProductDtos.add(gpd);
        };

//        genericProductDtos.remove(genericProductDtos.get(0));

        return new ResponseEntity<>(genericProductDtos, HttpStatus.OK);

//        productDtos.get(0).setId(1001L);
//
//        return new ResponseEntity<>(productDtos, HttpStatus.OK);
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
