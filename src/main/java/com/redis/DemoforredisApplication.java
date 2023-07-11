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

public class DemoforredisApplication {
	public static void main(String[] args) {

		SpringApplication.run(DemoforredisApplication.class, args);
	}

}
