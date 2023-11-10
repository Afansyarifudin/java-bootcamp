package com.example.springwebrest_ch5.controller;

import com.example.springwebrest_ch5.model.Merchant;
import com.example.springwebrest_ch5.model.Order;
import com.example.springwebrest_ch5.model.dto.MerchantEditNameDto;
import com.example.springwebrest_ch5.model.dto.OrderRequestDto;
import com.example.springwebrest_ch5.service.OrderServiceImpl;
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
        name = "Order Resource",
        description = "Order Resource Description"
)
@RequestMapping("api/order")
public class OrderController {

    private final OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    @Operation(
            summary = "Get All Orders",
            description = "Get All Orders"
    )
    public ResponseEntity<Map<String, Object>> getAll() {
        try {
            List<Order> orders = orderService.getAll();
            return ResponseUtil.successResponseWithData("Get all orders", orders);
        } catch (RuntimeException e) {
            return ResponseUtil.notFoundResponse("Failed to get order");
        }
    }

    @PostMapping
    @Operation(
            summary = "Create New Order",
            description = "Create New Order"
    )
    public ResponseEntity<Map<String, Object>> add(@RequestBody @Valid OrderRequestDto orderRequestDto) {
        try {
            Order createOrder = orderService.create(orderRequestDto);
            return ResponseUtil.successResponseWithData("Order Created Successfully", createOrder);
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("Failed to create product " + e);
        }
    }

    @PostMapping("/{id}/checkout")
    @Operation(
            summary = "Checkout Order",
            description = "Checkout Order"
    )
    public ResponseEntity<Map<String, Object>> checkout(@PathVariable("id") UUID id) {
        try {
            Order checkoutOrder = orderService.checkout(id);
            return ResponseUtil.successResponseWithData("Success checkout order", checkoutOrder);
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("Error checkout User");
        }
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Get Order By Id",
            description = "Get Order By Id"
    )
    public ResponseEntity<Map<String, Object>> show(@PathVariable("id") UUID id) {
        try {
            Optional<Order> orderOptional = orderService.getById(id);
            if (orderOptional.isPresent()) {
                Order order = orderOptional.get();
                return ResponseUtil.successResponseWithData("Success get data order", order);
            } else {
                return ResponseUtil.notFoundResponse("Order not found");
            }
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("Order not found");
        }
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete Order by Id",
            description = "Delete Order By Id"
    )
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") UUID id){
        try {
            orderService.delete(id);
            return ResponseUtil.successResponse("Order Deleted Successfully");
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("Order not found");
        }
    }

}
