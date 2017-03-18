package com.shop.controllers;

import com.shop.dto.ProductDto;
import com.shop.dto.UserDto;
import com.shop.service.ProductService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

/**
 * Created by ostap on 3/4/17.
 */
@Controller
public class IndexController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(ModelMap model, Principal principal) {
        if (principal != null) {
            UserDto currentUser = new UserDto();
            currentUser.setEmail(principal.getName());
            double totalCost = userService.totalCost(currentUser);
            int quantity = userService.findUserByEmail(currentUser.getEmail()).getProducts().size();
            model.addAttribute("total", totalCost);
            model.addAttribute("quantity", quantity);
        }
        List<ProductDto> products = productService.allProducts();
        model.addAttribute("products", products);
        return "index";
    }
}
