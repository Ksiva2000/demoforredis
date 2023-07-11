package com.redis;

import com.redis.entity.Product;
import com.redis.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/product")
public class DemoforredisApplication {

	@Autowired
	private ProductDao dao;
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

	public static void main(String[] args) {

		SpringApplication.run(DemoforredisApplication.class, args);
	}

}
