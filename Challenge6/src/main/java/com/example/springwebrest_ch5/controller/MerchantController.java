package com.example.springwebrest_ch5.controller;


import com.example.springwebrest_ch5.model.Merchant;
import com.example.springwebrest_ch5.model.dto.MerchantEditNameDto;
import com.example.springwebrest_ch5.model.dto.MerchantViewDTO;
import com.example.springwebrest_ch5.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.example.springwebrest_ch5.service.MerchantServiceImpl;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@Tag(
        name = "Merchant Resource",
        description = "Merchant Resource Description"
)
@RequestMapping("api/merchant")
public class MerchantController {

    private final MerchantServiceImpl merchantService;

    @Autowired
    public MerchantController(MerchantServiceImpl merchantService) {
        this.merchantService=merchantService;
    }

    private final static Logger logger = LoggerFactory.getLogger(MerchantController.class);

    @GetMapping
    @Operation(
            summary = "Get All Merchant",
            description = "Get All Merchant Data"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all data merchant",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Failed to get Merchant",
                    content = {@Content(mediaType = "application/json")}),
    })
    public ResponseEntity<Map<String, Object>> getAll() {
        try {
            List<Merchant> merchants = merchantService.getAll();
            return ResponseUtil.successResponseWithData("Get all data merchant", merchants);
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("Failed to get Merchant");
        }
    }

    @GetMapping("active")
    @Operation(
            summary = "Get All Open Merchant",
            description = "Get All Open merchant"
    )
    public ResponseEntity<Map<String, Object>> getAllOpenMerchant() {
        try {
            List<Merchant> openMerchant = merchantService.getAllOpen();
            return ResponseUtil.successResponseWithData("Get All Open Merchant", openMerchant);
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("Failed to get Open Merchant");
        }
    }

    @PostMapping
    @Operation(
            summary = "Create New Merchant",
            description = "Create New Merchant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Merchant created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Failed to create Merchant",
                    content = {@Content(mediaType = "application/json")}),
    })
    public ResponseEntity<Map<String, Object>> add(@RequestBody @Valid Merchant merchant) {
        try {
            Merchant createMerchant = merchantService.create(merchant);
            return ResponseUtil.successResponseWithData("Merchant created successfully", createMerchant);
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("Failed to create Merchant");
        }
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Get Merchant By Id",
            description = "Get Merchant By Id Description"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success get data merchant by Id",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Merchant not found",
                    content = {@Content(mediaType = "application/json")}),
    })
    public ResponseEntity<Map<String, Object>> show(@PathVariable("id") UUID id) {
        try {
            Optional<Merchant> optionalMerchant = merchantService.getById(id);
            if (optionalMerchant.isPresent()) {
                Merchant getMerchant = optionalMerchant.get();
                return ResponseUtil.successResponseWithData("Success get data merchant by Id", getMerchant);
            } else {
                return ResponseUtil.notFoundResponse("Merchant not found");
            }
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("Merchant Not Found");
        }
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Edit Merchant Data",
            description = "Edit Merchant Data"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Merchant successfully updated",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Merchant not found",
                    content = {@Content(mediaType = "application/json")}),
    })
    public ResponseEntity<Map<String, Object>> put(@PathVariable("id") UUID id,
                        @RequestBody Merchant merchant) {
        try {
            Merchant updatedMerchant = merchantService.update(merchant, id);
            return ResponseUtil.successResponseWithData("Merchant successfully updated", updatedMerchant);
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("Merchant Not Found");
        }
    }

    @PatchMapping("{id}")
    @Operation(
            summary = "Update Merchant's Name",
            description = "Edit Merchant's Name"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Merchant successfully updated",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Merchant Not Found",
                    content = {@Content(mediaType = "application/json")}),
    })
    public ResponseEntity<Map<String, Object>> patch(@PathVariable("id") UUID id,
                                                     @RequestBody @Valid MerchantEditNameDto merchantEditNameDto) {
        try {
            Merchant updatedMerchant = merchantService.updateByName(merchantEditNameDto, id);
            return ResponseUtil.successResponseWithData("Merchant successfully updated", updatedMerchant);
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("Merchant Not Found");
        }
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete merchant by Id",
            description = "Delete merchant by Id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Merchant deleted successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Merchant Not Found",
                    content = {@Content(mediaType = "application/json")}),
    })
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id")UUID id){
        try {
            merchantService.delete(id);
            return ResponseUtil.successResponse("Merchant deleted successfully");

        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("Merchant Not Found");
        }
    }


}
