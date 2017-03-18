package com.shop.controllers;

/**
 * Created by ostap on 2/23/17.
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.shop.dto.ProductDto;
import com.shop.dto.UserDto;
import com.shop.dto.validation.exception.EmailExistsException;


import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.shop.service.UserService;
import org.springframework.web.servlet.ModelAndView;


import java.io.IOException;
import java.security.Principal;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String showLoginForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDto accountDto,
            BindingResult result) {

        UserDto registered = new UserDto();
        if (!result.hasErrors()) {
            try {
                registered = userService.registerNewUserAccount(accountDto);
            } catch (EmailExistsException e) {
                e.printStackTrace();
            }
        }
        if (registered == null) {
            result.rejectValue("email", "Invalid email");
        }
        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", accountDto);
        } else {
            return new ModelAndView("index", "user", accountDto);
        }
    }

    @RequestMapping(value = "/products/imageDisplay", method = RequestMethod.GET)
    public void showImage(@RequestParam("name") String name, HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {
        ProductDto productDto = productService.findProduct(name);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(productDto.getImage());
        response.getOutputStream().close();
    }


    @RequestMapping(value = "products/{category}/{type}", method = RequestMethod.GET)
    public String filterProducts(@PathVariable("category") String category, @PathVariable("type") String type,
                                 ModelMap model) {
        List<ProductDto> products = productService.findProductsByCategoryAndType(category, type);
        model.addAttribute("products", products);
        if (products.size() == 0) {
            return "searchError";
        }
        return "productViewTemplate";
    }

    @RequestMapping(value = "search/value={value}", method = RequestMethod.GET)
    public String searchResult(@PathVariable("value") String value, ModelMap model) {
        List<ProductDto> products = productService.searchResultList(value);
        model.addAttribute("products", products);
        if (products.size() == 0) {
            return "searchError";
        }
        return "productViewTemplate";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String userCart(ModelMap model, Principal principal) {
        UserDto userDto = new UserDto();
        userDto.setEmail(principal.getName());
        List<ProductDto> products = userService.showUserCart(userDto);
        double totalCost = userService.totalCost(userDto);
        int quantity = userService.findUserByEmail(userDto.getEmail()).getProducts().size();
        model.addAttribute("products", products);
        model.addAttribute("total", totalCost);
        model.addAttribute("quantity", quantity);
        return "userCart";
    }

    @RequestMapping(value = "/view/{name}", method = RequestMethod.GET)
    public String productRewiew(@PathVariable("name") String name, ModelMap model, Principal principal) {
        ProductDto productDto = productService.findProduct(name);
        model.addAttribute("product", productDto);
        if (principal != null) {
            UserDto userDto = new UserDto();
            userDto.setEmail(principal.getName());
            double totalCost = userService.totalCost(userDto);
            int quantity = userService.findUserByEmail(userDto.getEmail()).getProducts().size();
            model.addAttribute("total", totalCost);
            model.addAttribute("quantity", quantity);
        }
        return "productReview";
    }

    @RequestMapping(value = "addToCart/{product}", method = RequestMethod.GET)
    public String addToCart(@PathVariable("product") String name, Principal principal, ModelMap model) {
        UserDto currentUser = new UserDto();
        currentUser.setEmail(principal.getName());
        userService.addProductToUserCart(currentUser, productService.findProduct(name));
        model.addAttribute("user", currentUser);
        return "index";
    }

    @RequestMapping(value = "/cart/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean deleteFormCart(@PathVariable("name") String name, Principal principal) {

        userService.deleteFromCart(principal.getName(), name);
        return true;
    }
}


