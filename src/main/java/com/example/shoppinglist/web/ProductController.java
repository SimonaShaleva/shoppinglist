package com.example.shoppinglist.web;

import com.example.shoppinglist.model.binding.ProductAddBindingModel;
import com.example.shoppinglist.model.service.ProductServiceModel;
import com.example.shoppinglist.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String addProduct(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/login";
        }
        if (!model.containsAttribute("productAddBindingModel")) {
            model.addAttribute(new ProductAddBindingModel());
        }
        return "product-add";
    }

    @PostMapping("/add")
    public String addProductPost(@Valid @ModelAttribute("productAddBindingModel")
                                             ProductAddBindingModel productAddBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.productAddBindingModel",bindingResult);
            return "redirect:add";
        }

        ProductServiceModel productServiceModel = this.modelMapper
                .map(productAddBindingModel, ProductServiceModel.class);
        productService.addProduct(productServiceModel);

        return "redirect:/";
    }

    @GetMapping("/buy/{id}")
    public String buyById(@PathVariable Long id) {

        productService.buyProductById(id);
        return "redirect:/";
    }

    @GetMapping("/buy/all")
    public String buyAll() {

        productService.buyAll();
        return "redirect:/";
    }
}