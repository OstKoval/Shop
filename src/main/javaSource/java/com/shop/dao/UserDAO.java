package com.shop.dao;

/**
 * Created by ostap on 2/23/17.
 */
import java.util.List;

import com.shop.dto.validation.exception.InvalidEmailException;
import com.shop.entities.User;

public interface UserDAO {

    User findById(int id);

    User saveUser(User user);

    void deleteUserByEmail(String id) throws InvalidEmailException;

    List<User> findAllUsers();

    User findUserByEmail(String email);

    User updateUser(User user);

    void merge(User user);

}
