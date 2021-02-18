package com.example.shoppinglist.web;

import com.example.shoppinglist.model.entity.CategoryNameEnum;
import com.example.shoppinglist.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(HttpSession httpSession, Model model) {
        if (httpSession.getAttribute("user") == null) {
            return "index";
        }

        model.addAttribute("totalSum", productService.findTotalPrice());
        model.addAttribute("drinks", productService.findByCategoryName(CategoryNameEnum.DRINK));
        model.addAttribute("foods", productService.findByCategoryName(CategoryNameEnum.FOOD));
        model.addAttribute("households", productService.findByCategoryName(CategoryNameEnum.HOUSEHOLD));
        model.addAttribute("others", productService.findByCategoryName(CategoryNameEnum.OTHER));
        return "home";
    }
}
