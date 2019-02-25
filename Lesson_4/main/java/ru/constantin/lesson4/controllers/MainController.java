package ru.constantin.lesson4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.constantin.lesson4.services.ProductService;

/*
Не понял только как сделать переходы по страницам, если продукты отфильтрованы по цене.
*/

@Controller
public class MainController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("products", productService.getPage("first"));
        return "index";
    }

    @GetMapping("/minprice")
    public String showMinPricePage(Model model) {
        model.addAttribute("products", productService.getAllProductsByMinCost());
        return "index";
    }

    @GetMapping("/maxprice")
    public String showMaxPricePage(Model model) {
        model.addAttribute("products", productService.getAllProductsByMaxCost());
        return "index";
    }

    @GetMapping("/previous")
    public String showPreviousPage(Model model) {
        model.addAttribute("products", productService.getPage("previous"));
        return "index";
    }

    @GetMapping("/next")
    public String showNextPage(Model model) {
        model.addAttribute("products", productService.getPage("next"));
        return "index";
    }
}
