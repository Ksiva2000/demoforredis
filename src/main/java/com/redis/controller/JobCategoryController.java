package com.redis.controller;

import com.redis.Service.JobCategoryService;
import com.redis.repository.JobCategoryRepo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JobCategoryController {
    private static final Log logger = LogFactory.getLog(JobCategoryController.class);
    @Autowired
    JobCategoryService jobCategoryService;
    @PostMapping("/dumpJobCategoriesintoredis")
    public  String dumpJobCategorydataintoredis(
            @RequestParam(required = true) String dumpJobcategory){
        logger.info("START: dumpJobCategorydataintoredis() :JobCategoryController");
        String result =null;
        result=jobCategoryService.dumpJobCategorydataintoRedis(dumpJobcategory);
        logger.info("END: dumpJobCategorydataintoredis() :JobCategoryController");
        return result;
    }
}
