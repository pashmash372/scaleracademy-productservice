package dev.pashmash.productservice.services;

import dev.pashmash.productservice.dtos.FakeStoreProductDto;
import dev.pashmash.productservice.dtos.GenericProductDto;
import dev.pashmash.productservice.exceptions.NotFoundException;
import dev.pashmash.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private final FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    private static GenericProductDto convertFakeStoreProductDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
        return GenericProductDto.builder().id(fakeStoreProductDto.getId()).title(fakeStoreProductDto.getTitle()).price(fakeStoreProductDto.getPrice()).category(fakeStoreProductDto.getCategory()).description(fakeStoreProductDto.getDescription()).image(fakeStoreProductDto.getImage()).build();
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.createProduct(product));
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.getProductById(id));
    }

    @Override
    public List<GenericProductDto> getProducts() {
        return fakeStoreProductServiceClient.getProducts().stream().map(FakeStoreProductService::convertFakeStoreProductDtoToGenericProductDto).toList();
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.deleteProduct(id));
    }

    @Override
    public GenericProductDto updateProduct(GenericProductDto product, Long id) {
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.updateProduct(product,id));
    }
}
