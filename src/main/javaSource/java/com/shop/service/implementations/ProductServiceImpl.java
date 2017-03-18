package com.shop.service.implementations;


import com.shop.dao.ProductDAO;
import com.shop.dao.UserDAO;
import com.shop.dto.ProductDto;
import com.shop.dto.validation.exception.InvalidProductName;
import com.shop.entities.Product;
import com.shop.entities.User;
import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by ostap on 3/4/17.
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public Product findProduct(int id) {
        return productDAO.findProduct(id);
    }

    @Override
    public ProductDto findProduct(String name) {
        Product product = productDAO.findProduct(name);

        ProductDto temp = new ProductDto();

        temp.setName(product.getName());

        temp.setCategory(product.getCategory());

        temp.setDescription(product.getDescription());

        temp.setManufacturer(product.getManufacturer());

        temp.setType(product.getType());

        temp.setQuantity(product.getQuantity());

        temp.setImage(product.getImage());

        temp.setPrice(product.getPrice());

        return temp;
    }


    @Override
    public List<ProductDto> allProducts() {

        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList = convert(productDAO.allProducts(),productDtoList);
        shuffle(productDtoList);
        return productDtoList;
    }

    @Override
    public List<ProductDto> findProductsByCategoryAndType(String category, String type) {
        List<Product> productList = productDAO.findProductsByCategoryAndType(category,type);
        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList = convert(productList,productDtoList);
        shuffle(productDtoList);
        return productDtoList;
    }

    @Override
    public void addNewProduct(ProductDto productDto){
        Product coincidence=productDAO.findProduct(productDto.getName());
        if(coincidence == null) {
            Product product = new Product();

            product.setName(productDto.getName());

            product.setCategory(productDto.getCategory());

            product.setDescription(productDto.getDescription());

            product.setManufacturer(productDto.getManufacturer());

            product.setType(productDto.getType());

            product.setQuantity(productDto.getQuantity());

            product.setImage(productDto.getImage());

            product.setPrice(productDto.getPrice());

            productDAO.saveProduct(product);
        }
        else productDAO.increaseQuantity(productDto.getName(),productDto.getQuantity());

    }

    @Override
    public void deleteProduct(String name) throws InvalidProductName {
        List<User> allUsers = userDAO.findAllUsers();
        for(User user:allUsers){
            Iterator<Product> it = user.getProducts().iterator();
            while(it.hasNext()) {
                Product product = it.next();
                if (product.getName().equals(name)) {
                    it.remove();
                }
            }
            userDAO.merge(user);
        }
        productDAO.deleteProduct(name);

    }

    @Override
    public Product updateProduct(ProductDto productDto,int id) {

            Product product = new Product();

            product.setName(productDto.getName());

            product.setCategory(productDto.getCategory());

            product.setDescription(productDto.getDescription());

            product.setManufacturer(productDto.getManufacturer());

            product.setType(productDto.getType());

            product.setQuantity(productDto.getQuantity());

            product.setImage(productDto.getImage());

            product.setPrice(productDto.getPrice());

            product.setId(id);

            return productDAO.updateProduct(product);

    }

    @Override
    public List<ProductDto> searchResultList(String search_param){
        List<Product> productList = productDAO.searchByName(search_param);
        List<ProductDto> productDtoList = new ArrayList<>();
        return convert(productList,productDtoList);
    }

    public static List<ProductDto> convert(List<Product> productList, List<ProductDto> productDtoList){
        for(Product product:productList){
            productDtoList.add(new ProductDto((product)));
        }
        return productDtoList;
    }
    private void shuffle(List<ProductDto> productDtoList){
        long seed = System.nanoTime();
        Collections.shuffle(productDtoList, new Random(seed));
    }
}
