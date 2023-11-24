package com.example.springwebrest_ch5.service;

import com.example.springwebrest_ch5.model.Product;
import com.example.springwebrest_ch5.model.dto.ProductEditStockDto;
import com.example.springwebrest_ch5.model.dto.ProductRequestDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    Product create(ProductRequestDto productRequestDto);

    List<Product> getAll();

    Optional<Product> getById(UUID id);

    Product update(Product product, UUID id);

    Product updatePrice(ProductEditStockDto productEditStockDto, UUID id);

    void delete(UUID id);

    List<Product> getAllActive();
}
