package com.selfmotivatednitish.ekart.service.product;

import com.selfmotivatednitish.ekart.dto.ProductDto;
import com.selfmotivatednitish.ekart.model.Product;
import com.selfmotivatednitish.ekart.request.AddProductRequest;
import com.selfmotivatednitish.ekart.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest request);

    Product getProductById(Long id);

    void deleteProductById(Long id);

    Product updateProduct(ProductUpdateRequest request, Long productId);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByCategoryAndBrand(String category, String brand);

    List<Product> getProductsByName(String name);

    List<Product> getProductsByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);

    List<ProductDto> getCovertedProducts(List<Product> products);

    ProductDto convertToDto(Product product);
}
