package dev.pashmash.productservice.thirdpartyclients.productservice.fakestore;

import dev.pashmash.productservice.dtos.FakeStoreProductDto;
import dev.pashmash.productservice.dtos.GenericProductDto;
import dev.pashmash.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Wrapper over the FakeStore API
 */
@Service
public class FakeStoreProductServiceClient {

    private final RestTemplateBuilder restTemplateBuilder;
    private final String specificProductRequestUrl;
    private final String productRequestBaseUrl;
    private RestTemplate restTemplate;

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder, @Value("${fakestore.api.url}") String fakeStoreApiUrl, @Value("${fakestore.api.paths.product}") String fakeStoreProductsApiPath) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.specificProductRequestUrl = fakeStoreApiUrl + fakeStoreProductsApiPath + "/{id}";
        this.productRequestBaseUrl = fakeStoreApiUrl + fakeStoreProductsApiPath;
    }


    public FakeStoreProductDto createProduct(GenericProductDto product) {
        restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(productRequestBaseUrl, product, FakeStoreProductDto.class);
        return response.getBody();
    }


    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Product with id " + id + " not found");
        }
        return fakeStoreProductDto;
    }


    public List<FakeStoreProductDto> getProducts() {
        restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestBaseUrl, FakeStoreProductDto[].class);
        List<FakeStoreProductDto> answer = new ArrayList<>();
        Collections.addAll(answer, Objects.requireNonNull(response.getBody()));
        return answer;
    }


    public FakeStoreProductDto deleteProduct(Long id) {
        FakeStoreProductDto fakeStoreProductDto = null;
        restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        if (response != null) {
            fakeStoreProductDto = response.getBody();
        }
        return fakeStoreProductDto;
    }


    public FakeStoreProductDto updateProduct(GenericProductDto product, Long id) {
        FakeStoreProductDto fakeStoreProductDto = null;
        restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);
        if (response != null) {
            fakeStoreProductDto = response.getBody();
        }
        return fakeStoreProductDto;
    }
}
