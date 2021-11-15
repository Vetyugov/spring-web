package com.geekbrains.springweb.repositories;

import com.geekbrains.springweb.model.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private ArrayList<Product> products;

    @PostConstruct
    public void init(){
        products = new ArrayList<>(List.of(
                new Product(1L, "Banana"),
                new Product(2L, "Orange"),
                new Product(3L, "Apple"),
                new Product(4L, "Pineapple"),
                new Product(5L, "Coconut")
        ));
    }
    public List<Product> getAllProducts(){
        return Collections.unmodifiableList(products);
    }

    public Product findById(Long id){
        return products.stream().filter(s-> s.getId().equals(id)).findFirst().orElseThrow(()-> new RuntimeException("Products not found"));
    }
}
