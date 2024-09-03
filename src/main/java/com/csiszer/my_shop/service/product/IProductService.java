package com.csiszer.my_shop.service.product;
import com.csiszer.my_shop.dto.ProductDto;
import com.csiszer.my_shop.model.Product;
import com.csiszer.my_shop.request.AddProductRequest;
import com.csiszer.my_shop.request.ProductUpdateRequest;

import java.util.List;


public interface IProductService {
    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest request, Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategoryName(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryNameAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductByBrandAndName(String category, String name);
    Long countProductsByBrandAndName(String brand, String name);


    List<ProductDto> getConvertedProducts(List<Product> products);

    ProductDto convertToDto(Product product);
}
