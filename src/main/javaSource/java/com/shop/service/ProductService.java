package com.shop.service;

import com.shop.dto.ProductDto;
import com.shop.dto.validation.exception.InvalidProductName;
import com.shop.entities.Product;

import java.util.List;

/**
 * Created by ostap on 3/4/17.
 */
public interface ProductService {

    Product findProduct(int id);

    ProductDto findProduct(String name);

    List<ProductDto> allProducts();

    List<ProductDto> findProductsByCategoryAndType(String category,String type);

    void addNewProduct(ProductDto productDto);

    void deleteProduct(String name) throws InvalidProductName;

    Product updateProduct(ProductDto productDto,int id);

    List<ProductDto> searchResultList(String search_param);


}
