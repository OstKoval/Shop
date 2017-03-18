package com.shop.dao;

import com.shop.dto.validation.exception.InvalidProductName;
import com.shop.entities.Product;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by ostap on 2/25/17.
 */
public interface ProductDAO {
    Product findProduct(int id);

    Product findProduct(String name);

    List<Product> findProductsByCategoryAndType(String category,String type);

    List<Product> allProducts();

    void saveProduct(Product product);

    void deleteProduct(String name) throws InvalidProductName;

    Product increaseQuantity(String name,int increaseValue);

    Product updateProduct(Product product);

    Session getSession();

    List<Product> searchByName(String name);


}
