package com.redis.controller;

import com.redis.entity.Product;
import com.redis.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductDao dao;

    @PostMapping
    public Product savealldata(@RequestBody Product product){

        return dao.save(product);
    }
    @GetMapping("/{all}")
    public List<Product> getall(){

        return dao.findAll();
    }
    @GetMapping("/{id}")
    public Product findproduct(@PathVariable int id){

        return dao.findProductById(id);
    }
    @DeleteMapping("/{id}")
    public  String deletedid(@PathVariable int id){

        return dao.deleteproduct(id);
    }

}
