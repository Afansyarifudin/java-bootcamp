package com.example.springwebrest_ch5.controller;

import com.example.springwebrest_ch5.model.Merchant;
import com.example.springwebrest_ch5.model.OrderDetail;
import com.example.springwebrest_ch5.model.dto.OrderDetailRequestDto;
import com.example.springwebrest_ch5.service.OrderDetailService;
import com.example.springwebrest_ch5.service.OrderDetailServiceImpl;
import com.example.springwebrest_ch5.service.OrderServiceImpl;
import com.example.springwebrest_ch5.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Tag(
        name = "Order Details Resource",
        description = "Order Details Resource Description"
)
@RequestMapping("api/order-detail")
public class OrderDetailController {

    private final OrderDetailServiceImpl orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailServiceImpl orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping
    @Operation(
            summary = "Create New Order Detail",
            description = "Create New Order Detail"
    )
    public ResponseEntity<Map<String, Object>> add(@RequestBody @Valid OrderDetailRequestDto orderDetailRequestDto) {
        try {
            OrderDetail createOrderDetail = orderDetailService.create(orderDetailRequestDto);
            return ResponseUtil.successResponseWithData("Succes create order detail", createOrderDetail);
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("Failed create order detail");
        }
    }

}
