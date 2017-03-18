package com.shop.controllers;

import com.shop.dto.ProductDto;
import com.shop.dto.UserDto;
import com.shop.dto.validation.exception.InvalidEmailException;
import com.shop.dto.validation.exception.InvalidProductName;
import com.shop.service.ProductService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ostap on 3/4/17.
 */
@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = {"/admin/users"}, method = RequestMethod.GET)
    public String listOfUsers(ModelMap model) {

        List<UserDto> users = userService.findAllUsers();
        users = users.stream().peek(u -> u.setPassword(null)).collect(Collectors.toList());
        model.addAttribute("users", users);
        return "allUsers";
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "updateUser";
    }

    @RequestMapping(value = "/admin/users/{email}/delete", method = RequestMethod.GET)
    public void deleteUser(@PathVariable("email") String email, HttpServletResponse response) {
        try {
            userService.deleteUserByEmail(email);
        } catch (InvalidEmailException e) {
            response.setStatus(404);
        }
    }

    @RequestMapping(value = "/admin/products/{name}/delete", method = RequestMethod.GET)
    public void deleteProduct(@PathVariable("name") String name, HttpServletResponse response) {

        try {
            productService.deleteProduct(name);
        } catch (InvalidProductName invalidProductName) {
            response.setStatus(404);
        }
    }

    @RequestMapping(value = "/admin/products", method = RequestMethod.GET)
    public String listOfProducts(ModelMap model) {
        List<ProductDto> products = productService.allProducts();
        model.addAttribute("products", products);
        return "allProducts";
    }

    @RequestMapping(value = {"/admin/users/edit/id={id}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable int id, ModelMap model) {
        UserDto userDto = new UserDto(userService.findById(id));
        model.addAttribute("user", userDto);
        model.addAttribute("edit", true);
        return "updateUser";
    }

    @RequestMapping(value = {"/admin/products/edit/id={id}"}, method = RequestMethod.GET)
    public String editProduct(@PathVariable int id, ModelMap model) {
        ProductDto productDto = new ProductDto(productService.findProduct(id));
        model.addAttribute("product", productDto);
        model.addAttribute("edit", true);
        return "updateProduct";
    }


    @RequestMapping(value = {"/admin/products/edit/id={id}"}, method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("product") ProductDto productDto,
                                @RequestParam("imageFile") CommonsMultipartFile imageFile,
                                BindingResult result, @PathVariable int id) {

        if (result.hasErrors()) {
            return "updateProduct";
        }
        productDto.setImage(imageFile.getBytes());
        productService.updateProduct(productDto, id);
        return "redirect:/admin/products";
    }

    @RequestMapping(value = {"/admin/users/edit/id={id}"}, method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") UserDto userDto, BindingResult result,
                             @PathVariable int id) {
        userDto.setMatchingPassword(userDto.getPassword());
        if (result.hasErrors()) {
            return "updateUser";
        }
        userService.updateUser(userDto, id);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = {"/admin/add"}, method = RequestMethod.GET)
    public String registerNewProduct(ModelMap model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("product", productDto);
        return "addProduct";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST, headers = "content-type=multipart/*;")
    public String addProduct(@Valid @ModelAttribute("product") ProductDto product,
                             @RequestParam("imageFile") CommonsMultipartFile imageFile,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) throws IOException {
        if (result.hasErrors()) {
            return "/admin/add";
        }
        product.setImage(imageFile.getBytes());
        productService.addNewProduct(product);
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/admin/products";
    }

}
