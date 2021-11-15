package com.geekbrains.springweb.Controllers;

import com.geekbrains.springweb.model.Product;
import com.geekbrains.springweb.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//бин
@Controller
public class MainController {
    private ProductRepository productRepository;

    public MainController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //ResponseBody - то, что в return, то и возвращается пользователю
    //@RequestParam - параметры, поторые могут быть указаны в запросе
    //required = false - не обязательный параметр
    @GetMapping("/add")
    @ResponseBody
    public int add(@RequestParam(required = false) int a, @RequestParam int b){
        return a + b;
    }

    @GetMapping("/product/{id}/info")
    @ResponseBody
    public String showProductInfo(@PathVariable Long id){
        return "Product #" + id;
    }

    @GetMapping("/products")
    public String showProductsPage(Model model){
        model.addAttribute("products", productRepository.getAllProducts());
        return "products_page";
    }

    @GetMapping("/products/{id}")
    public String showProductPage(Model model, @PathVariable Long id){
        Product product = productRepository.findById(id);
        model.addAttribute("product", product);
        return "product_info_page";
    }
}
