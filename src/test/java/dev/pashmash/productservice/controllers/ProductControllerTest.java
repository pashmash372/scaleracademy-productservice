package dev.pashmash.productservice.controllers;

import dev.pashmash.productservice.dtos.GenericProductDto;
import dev.pashmash.productservice.exceptions.NotFoundException;
import dev.pashmash.productservice.services.FakeStoreProductService;
import dev.pashmash.productservice.services.ProductService;
import dev.pashmash.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @MockBean
    private FakeStoreProductServiceClient fakeStoryProductServiceClient;

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @MockBean
    private FakeStoreProductService fakeStoreProductService;

    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Captor
    private ArgumentCaptor<Long> fakeStoreIdCaptor;

//    @Test
//    void returnsNullWhenProductDoesntExist() throws NotFoundException {
//        when(productService.getProductById(121L)).thenReturn(null);
//        GenericProductDto genericProductDto = productController.getProductById(121L);
//        assertNull(genericProductDto);
//    }

    @Test
    void shouldReturnTitleABCWithProductId1() throws NotFoundException {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle("ABC");
        when(productService.getProductById(1L)).thenReturn(genericProductDto);
        GenericProductDto genericProductDto1 = productController.getProductById(1L);
        assertEquals("ABC", genericProductDto1.getTitle());
    }

    @Test
    void throwsExceptionWhenProductDoesntExist() throws NotFoundException {
        when(productService.getProductById(any(Long.class))).thenReturn(null);
        assertThrows(NotFoundException.class, () -> productController.getProductById(121L));
    }

    @Test
    void returnsProductWhenProductExists() throws NotFoundException {
        GenericProductDto genericProductDto = new GenericProductDto();
        when(productService.getProductById(any(Long.class))).thenReturn(new GenericProductDto());
        assertEquals(genericProductDto, productController.getProductById(1L));
    }

    @Test
    void returnsSameProductAsServiceWhenProductExists() throws NotFoundException {
        GenericProductDto genericProductDto = new GenericProductDto();
        when(productService.getProductById(any(Long.class))).thenReturn(genericProductDto);
        assertEquals(genericProductDto.getPrice(), productController.getProductById(123L).getPrice());
    }

//  TODO: Fix this test

//    @Test
//    void productControllerCallsProductServiceWithSameProductId() throws NotFoundException {
//        Long id = 1L;
////        when(productService.getProductById(any()))
////                .thenCallRealMethod();
////                .thenReturn(new GenericProductDto());
//
////        Check that the product service is being called with the exact same
////        param as controller
//        productController.getProductById(id);
//        verify(productService).getProductById(idCaptor.capture());
//        verify(fakeStoreProductService).getProductById(fakeStoreIdCaptor.capture());
//
//        assertEquals(id, idCaptor.getValue());
//        assertEquals(id, fakeStoreIdCaptor.getValue());
//    }
}