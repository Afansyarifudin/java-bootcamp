package com.example.springwebrest_ch5.service;

import com.example.springwebrest_ch5.model.Merchant;
import com.example.springwebrest_ch5.model.Product;
import com.example.springwebrest_ch5.model.dto.ProductEditStockDto;
import com.example.springwebrest_ch5.model.dto.ProductRequestDto;
import com.example.springwebrest_ch5.repository.MerchantRepository;
import com.example.springwebrest_ch5.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    private final static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


    @Override
    public Product create(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());

        Merchant merchant = merchantRepository.findById(productRequestDto.getMerchantId())
                .orElseThrow(() -> new EntityNotFoundException("Merchant Id " + productRequestDto.getMerchantId()+ " not found"));
        product.setMerchant(merchant);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getById(UUID id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            logger.info("There's no product available");
            throw new RuntimeException();
        }
        return productOptional;
    }

    @Override
    public Product update(Product product, UUID id) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isEmpty()) {
            logger.info("Merchant Not Found");
            throw new RuntimeException("Merchant Not Found");
        }

        Product updatedProduct = existingProduct.get();
        if (!updatedProduct.isDeleted()) {
            updatedProduct.setId(id)
                    .setName(product.getName())
                    .setPrice(product.getPrice());
            return productRepository.save(updatedProduct);
        } else {
            logger.info("Product has been deleted");
            throw new RuntimeException("Product has been deleted and cannot be updated");

        }
    }

    @Override
    public Product updatePrice(ProductEditStockDto productEditStockDto, UUID id) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isEmpty()) {
            logger.info("Product not found");
            throw new RuntimeException("Product Not Found");
        }

        Product updatedProduct = existingProduct.get();

        if (!updatedProduct.isDeleted()) {
            updatedProduct.setPrice(productEditStockDto.getPrice());
            return productRepository.save(updatedProduct);
        } else {
            logger.info("Product has been deleted");
            throw new RuntimeException("Product Not Found");
        }
    }

    @Override
    public void delete(UUID id) {
        Optional<Product> productOptional = getById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            LocalDateTime currentDate = LocalDateTime.now();
            product.setDeletedDate(currentDate);
            productRepository.save(product);
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product Not Found");
        }
    }
}
