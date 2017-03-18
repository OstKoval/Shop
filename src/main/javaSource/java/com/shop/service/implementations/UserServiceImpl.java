package com.shop.service.implementations;

/**
 * Created by ostap on 2/23/17.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.shop.dao.ProductDAO;
import com.shop.dao.RoleDAO;
import com.shop.dto.ProductDto;
import com.shop.dto.UserDto;
import com.shop.dto.validation.exception.EmailExistsException;
import com.shop.dto.validation.exception.InvalidEmailException;
import com.shop.entities.Product;
import com.shop.entities.Role;
import com.shop.entities.User;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.UserDAO;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;

import static com.shop.service.implementations.ProductServiceImpl.convert;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private RoleDAO roleDAO;

    public User findById(int id) {
        return userDAO.findById(id);
    }

    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    public User updateUser(UserDto  userDto,int id) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setId(id);
        userDAO.updateUser(user);
        return user;
    }
    @Override
    public User findUserByEmail(String email){
        return userDAO.findUserByEmail(email);
    }

    public void deleteUserByEmail(String email) throws InvalidEmailException {
        userDAO.deleteUserByEmail(email);
    }

    public List<UserDto> findAllUsers() {
        List<User> users = userDAO.findAllUsers();
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user:users){
            userDtoList.add(new UserDto(user));
        }
        return userDtoList;
    }

    @Transactional
    @Override
    public UserDto registerNewUserAccount(UserDto accountDto)
            throws EmailExistsException {

        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress: "
                            +  accountDto.getEmail());
        }

        User user = new User();
        List<Role> roleList = roleDAO.findRolesByName("ROLE_USER");
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(accountDto.getPassword());
        user.setEmail(accountDto.getEmail());
        user.setRoles(roleList);
        userDAO.saveUser(user);
        UserDto userDto = new UserDto(user);
        return userDto;
    }

    @Override
    public double totalCost(UserDto userDto) {
        List<Product> productList = findUserByEmail(userDto.getEmail()).getProducts();
        double total = 0;
        for(Product product : productList){
            total += product.getPrice();
        }
        return total;
    }

    private boolean emailExist(String email) {
        User user = userDAO.findUserByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    @PostConstruct
    private void init(){
        if(CollectionUtils.isEmpty(userDAO.findAllUsers())) {

            User adminUser = new User();
            adminUser.setEmail("admin@admin.com");
            adminUser.setPassword("123123");

            List<Role> adminRoles = new ArrayList<>();

            Role roleUser = null;
            if ((roleUser = roleDAO.findRoleByName("ROLE_USER")) == null) {
                roleUser = new Role("ROLE_USER");
                roleDAO.saveRole(roleUser);
            }
            adminRoles.add(roleUser);

            Role roleAdmin = null;
            if ((roleAdmin = roleDAO.findRoleByName("ROLE_ADMIN")) == null) {
                roleAdmin = new Role("ROLE_ADMIN");
                roleDAO.saveRole(roleAdmin);
            }
            adminRoles.add(roleAdmin);

            adminUser.setRoles(adminRoles);
            saveUser(adminUser);
        }
    }

    @Override
    public void addProductToUserCart(UserDto userDto, ProductDto productDto){
        User user = userDAO.findUserByEmail(userDto.getEmail());
        user.getProducts().add(productDAO.findProduct(productDto.getName()));
        userDAO.merge(user);
    }

    @Override
    public List<ProductDto> showUserCart(UserDto userDto){
        List<Product> productList = userDAO.findUserByEmail(userDto.getEmail()).getProducts();
        List<ProductDto> productDtoList = new ArrayList<>();
        return convert(productList,productDtoList);
    }

    @Override
    public void deleteFromCart(String userName, String productName) {
        User user = findUserByEmail(userName);
        List<Product> productList = user.getProducts();
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getName().equals(productName)) {
                iterator.remove();
                return;
            }

        }
    }

}

