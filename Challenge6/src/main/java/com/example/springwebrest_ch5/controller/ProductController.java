package com.example.springwebrest_ch5.controller;

import com.example.springwebrest_ch5.model.Merchant;
import com.example.springwebrest_ch5.model.Product;
import com.example.springwebrest_ch5.model.dto.MerchantEditNameDto;
import com.example.springwebrest_ch5.model.dto.ProductEditStockDto;
import com.example.springwebrest_ch5.model.dto.ProductRequestDto;
import com.example.springwebrest_ch5.service.ProductServiceImpl;
import com.example.springwebrest_ch5.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@Tag(
        name = "Product Resource",
        description = "Product Resource Description"
)
@RequestMapping("/api/product")
public class ProductController {
    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService=productService;
    }

    @GetMapping
    @Operation(
            summary = "Get All Products",
            description = "Get All Products Data"
    )
    public ResponseEntity<Map<String, Object>> getAll() {
        try {
            List<Product> products = productService.getAll();
            return ResponseUtil.successResponseWithData("Get all data product", products);
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("Failed to get Product");
        }
    }

    @PostMapping
    @Operation(
            summary = "Create New Product",
            description = "Create New Product"
    )
    public ResponseEntity<Map<String, Object>> add(@RequestBody @Valid ProductRequestDto productRequestDto) {
        try {
            Product createProduct = productService.create(productRequestDto);
            return ResponseUtil.successResponseWithData("Product created successfully", createProduct);
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("Failed to Create Product");
        }
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Get Product By Id",
            description = "Get Product By Id Description"
    )
    public ResponseEntity<Map<String, Object>> show(@PathVariable("id") UUID id) {
        try {
            Optional<Product> optionalProduct = productService.getById(id);
            if (optionalProduct.isPresent()) {
                Product getProduct = optionalProduct.get();
                return ResponseUtil.successResponseWithData("Success get data product by Id", getProduct);
            } else {
                return ResponseUtil.notFoundResponse("Product not found");
            }
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("Product not found");
        }
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Edit Product Data",
            description = "Edit Product Data"
    )
    public ResponseEntity<Map<String, Object>> put(@PathVariable("id") UUID id,
                                                   @RequestBody Product product) {
        try {
            Product updatedProduct = productService.update(product, id);
            return ResponseUtil.successResponseWithData("Merchant successfully updated", updatedProduct);
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("Product not found");
        }
    }

    @PatchMapping("{id}")
    @Operation(
            summary = "Update Product's Name",
            description = "Edit Product's Name"
    )
    public ResponseEntity<Map<String, Object>> patch(@PathVariable("id") UUID id,
                                                     @RequestBody @Valid ProductEditStockDto productEditStockDto) {
        try {
            Product updatedProduct = productService.updatePrice(productEditStockDto, id);
            return ResponseUtil.successResponseWithData("Product successfully updated", updatedProduct);
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("Product Not Found");
        }
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete product by Id",
            description = "Delete product by Id"
    )
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") UUID id) {
        try {
            productService.delete(id);
            return ResponseUtil.successResponse("Product deleted successfully");
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("Product not found");
        }
    }

}
