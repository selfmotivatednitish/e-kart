package com.selfmotivatednitish.ekart.controller;

import com.selfmotivatednitish.ekart.dto.ProductDto;
import com.selfmotivatednitish.ekart.exceptions.ResourceNotFoundException;
import com.selfmotivatednitish.ekart.model.Product;
import com.selfmotivatednitish.ekart.request.AddProductRequest;
import com.selfmotivatednitish.ekart.request.ProductUpdateRequest;
import com.selfmotivatednitish.ekart.response.ApiResponse;
import com.selfmotivatednitish.ekart.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {

    private final IProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtos = productService.getCovertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("success", productDtos));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId);
            ProductDto productDto = productService.convertToDto(product);
            return ResponseEntity.ok(new ApiResponse("success", productDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
        try {
            Product newProduct = productService.addProduct(product);
            ProductDto productDto = productService.convertToDto(newProduct);
            return ResponseEntity.ok(new ApiResponse("Add product success", productDto));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductUpdateRequest request) {
        try {
            Product updateProduct = productService.updateProduct(request, request.getId());
            ProductDto productDto = productService.convertToDto(updateProduct);
            return ResponseEntity.ok(new ApiResponse("Update product success", productDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Delete product success", productId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by/brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName) {
        try {
            List<Product> products = productService.getProductsByBrandAndName(brandName, productName);
            if(products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found", null));
            }
            List<ProductDto> productDtos = productService.getCovertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("success", productDtos));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by/category-and-brand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String categoryName, @RequestParam String brandName) {
        try {
            List<Product> products = productService.getProductsByCategoryAndBrand(categoryName, brandName);
            if(products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found", null));
            }
            List<ProductDto> productDtos = productService.getCovertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("success", productDtos));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by/name")
    public ResponseEntity<ApiResponse> getProductByName(@RequestParam String productName) {
        try {
            List<Product> products = productService.getProductsByName(productName);
            if(products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found", null));
            }
            List<ProductDto> productDtos = productService.getCovertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("success", productDtos));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by/brand")
    public ResponseEntity<ApiResponse> getProductByBrand(@RequestParam String brandName) {
        try {
            List<Product> products = productService.getProductsByBrand(brandName);
            if(products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found", null));
            }
            List<ProductDto> productDtos = productService.getCovertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("success", productDtos));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by/category")
    public ResponseEntity<ApiResponse> getProductByCategory(@RequestParam String category) {
        try {
            List<Product> products = productService.getProductsByCategory(category);
            if(products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found", null));
            }
            List<ProductDto> productDtos = productService.getCovertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("success", productDtos));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse> countProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName) {
        try {
            var productCount = productService.countProductsByBrandAndName(brandName, productName);
            return ResponseEntity.ok(new ApiResponse("Product count", productCount));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
        }
    }
}
