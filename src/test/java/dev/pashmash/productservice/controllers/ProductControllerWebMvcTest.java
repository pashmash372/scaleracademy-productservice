package dev.pashmash.productservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.pashmash.productservice.dtos.GenericProductDto;
import dev.pashmash.productservice.exceptions.NotFoundException;
import dev.pashmash.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// not initialize any unnecessary bean
// only initialize dependencies that can be reached from ProductController

@WebMvcTest(ProductController.class)
public class ProductControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductController productController;




    @Test
    void getAllProductsReturnsEmptyListWhenNoProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/products")).andExpect(status().is(404)).andExpect(content().string("[]"));
    }

    @Test
    void returnsListOfProductsWhenProductsExist() throws Exception {
        ArrayList<GenericProductDto> products = new ArrayList<>();
        products.add(new GenericProductDto());
        products.add(new GenericProductDto());
        products.add(new GenericProductDto());

        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products")).andExpect(status().is(200)).andExpect(content().string(objectMapper.writeValueAsString(products)));
    }

    @Test
    void createProductShouldCreateANewProduct() throws Exception {
        GenericProductDto productToCreate = new GenericProductDto();
        productToCreate.setId(1001L);
        productToCreate.setTitle("iPhone 15 Pro Max");
        productToCreate.setImage("some image");
        productToCreate.setCategory("mobile phones");
        productToCreate.setDescription("Best iPhone Ever");

        GenericProductDto expectedProduct = new GenericProductDto();
        expectedProduct.setId(1001L);
        expectedProduct.setTitle("iPhone 15 Pro Max");
        expectedProduct.setImage("some image");
        expectedProduct.setCategory("mobile phones");
        expectedProduct.setDescription("Best iPhone Ever");

        when(productService.createProduct(any())).thenReturn(expectedProduct);

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productToCreate)))
                .andExpect(content().string(objectMapper.writeValueAsString(expectedProduct)))
                .andExpect(status().is(200));
//                .andExpect(jsonPath("$.student.name",is("Naman")))
//                .andExpect(jsonPath("$.length()",is(2)));
    }


}

/*
{
    student: {
        name: "Naman",
        email: "",
        age: 21
    }
}
* */

/*
[
    {
        id:,
        name:,
        email:,
    },
    {
        id:,
        name:,
        email:,
    },
    {
        id:,
        name:,
        email:,
    }

]
* */

// l1 -> [o1, o2, o3]

// l2 -> [o1'(1001L), o2', o3']