package com.shop.service;

/**
 * Created by ostap on 2/23/17.
 */

import java.util.List;

import com.shop.dto.ProductDto;
import com.shop.dto.UserDto;
import com.shop.dto.validation.exception.EmailExistsException;
import com.shop.dto.validation.exception.InvalidEmailException;
import com.shop.entities.User;

public interface UserService {

    User findById(int id);

    void saveUser(User user);

    User updateUser(UserDto userDto,int id);

    void deleteUserByEmail(String email) throws InvalidEmailException;

    List<UserDto> findAllUsers();

    User findUserByEmail(String email);

    UserDto registerNewUserAccount(UserDto accountDto) throws EmailExistsException;

    double totalCost(UserDto userDto);

    void addProductToUserCart(UserDto userDto, ProductDto productDto);

    List<ProductDto> showUserCart(UserDto userDto);

    void deleteFromCart(String userName,String productName);



}
