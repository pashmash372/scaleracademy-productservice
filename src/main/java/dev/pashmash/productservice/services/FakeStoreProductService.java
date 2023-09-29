package dev.pashmash.productservice.services;

import dev.pashmash.productservice.dtos.FakeStoreProductDto;
import dev.pashmash.productservice.dtos.GenericProductDto;
import dev.pashmash.productservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FakeStoreProductService implements ProductService {

    private final RestTemplateBuilder restTemplateBuilder;
    private final String specificProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private final String productRequestBaseUrl = "https://fakestoreapi.com/products";
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private static GenericProductDto convertFakeStoreProductDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
        return GenericProductDto.builder().id(fakeStoreProductDto.getId()).title(fakeStoreProductDto.getTitle()).price(fakeStoreProductDto.getPrice()).category(fakeStoreProductDto.getCategory()).description(fakeStoreProductDto.getDescription()).image(fakeStoreProductDto.getImage()).build();
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(productRequestBaseUrl, product, FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if (fakeStoreProductDto != null) {
            return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto);
        }
        return null;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Product with id " + id + " not found");
        }
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto);
    }

    @Override
    public List<GenericProductDto> getProducts() {
        restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestBaseUrl, FakeStoreProductDto[].class);
        List<GenericProductDto> answer = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : Objects.requireNonNull(response.getBody())) {
            answer.add(convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto));
        }
        return answer;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        FakeStoreProductDto fakeStoreProductDto = null;
        restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        if (response != null) {
            fakeStoreProductDto = response.getBody();
        }
        if (fakeStoreProductDto != null) {
            return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto);
        }
        return null;
    }

    @Override
    public GenericProductDto updateProduct(GenericProductDto product, Long id) {
        FakeStoreProductDto fakeStoreProductDto = null;
        restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);
        if (response != null) {
            fakeStoreProductDto = response.getBody();
        }
        if (fakeStoreProductDto != null) {
            return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto);
        }
        return null;
    }
}
