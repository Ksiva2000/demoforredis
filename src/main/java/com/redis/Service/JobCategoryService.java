package com.redis.Service;

import com.redis.JobCategory.JobCategory;
import com.redis.controller.JobCategoryController;
import com.redis.repository.JobCategoryRepo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

@Repository
public class JobCategoryService {
    private static final Log logger = LogFactory.getLog(JobCategoryService.class);
    @Autowired(required = true)
    JdbcTemplate jdbcTemplate;
    @Autowired(required = true)
    JobCategoryRepo jobCategoryRepo;

    public String dumpJobCategorydataintoRedis(String dumpJobCategory) {
        logger.info("START: dumpJobCategorydataintoRedis() :JobCategoryService");
        String jobCat = null;
        String result = null;
        if (dumpJobCategory.equals("yes")) {
            jobCat = insertJobCategorydataintoRedis();
            result = "JobCategory           : "+jobCat;
        }
        logger.info("END: dumpJobCategorydataintoRedis() :JobCategoryService");
        return result;
    }

    private String insertJobCategorydataintoRedis() {
        try {
            logger.info("START: insertJobCategorydataintoRedis() :JobCategoryService");
            String result = null;
            String query = "select m_id,job_maincategory from \n" +
                    "job_maincategory where deleted=false\n" +
                    " order by job_maincategory asc";
            RowMapper<JobCategory> jobMain = (rs, rowMapper) -> {
                JobCategory jc=new JobCategory();
                jc.setJob_Category(rs.getString(2));
                jc.setId(rs.getLong(1));
                return jc;
            };
            List<JobCategory> jobmain = jdbcTemplate.query(query,jobMain);
            if (jobmain != null) {
                result = jobCategoryRepo.savejobMainCategorydatainredis(jobmain);
                logger.info("END: insertJobCategorydataintoRedis() :JobCategoryService");

                return result;
            } else {
                return "Failed in insertjob()";
            }
        } catch (Exception e) {
            System.out.println("Error in service class"+e.getMessage());
            return "Failed in insertjob()";
        }
    }
}

