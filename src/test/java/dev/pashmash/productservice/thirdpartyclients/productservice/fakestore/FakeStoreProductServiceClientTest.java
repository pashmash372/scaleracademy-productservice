package dev.pashmash.productservice.thirdpartyclients.productservice.fakestore;

import dev.pashmash.productservice.dtos.FakeStoreProductDto;
import dev.pashmash.productservice.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FakeStoreProductServiceClientTest {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Test
    void  testNonExistentProductReturnsNull() {}
}