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
        String jobCategory = null;
        String result = null;
        if (dumpJobCategory.equals("yes")) {
            jobCategory = insertJobCategorydataintoRedis();
            result = "jobCategory         :" + jobCategory;
            logger.info("END: dumpJobCategorydataintoRedis() :JobCategoryService");
        }
        return "Failed in the Service";
    }

    private String insertJobCategorydataintoRedis() {
        logger.info("START: insertJobCategorydataintoRedis() :JobCategoryService");
        try {
            String result = null;
            String query = "select m_id,job_maincategory from \n" +
                    "job_maincategory where deleted=false\n" +
                    " order by job_maincategory asc";
            RowMapper<JobCategory> jobMain = (rs, rowMapper) -> {
                JobCategory jc=new JobCategory();
                jc.setJob_Category(rs.getString(2));
                jc.setId(rs.getLong(1));
                System.out.println(jc);
                return jc;
            };
            List<JobCategory> jobmain = jdbcTemplate.query(query,jobMain);
            System.out.println(jobmain);
            if (jobmain != null) {
                System.out.println("go to the if condition");
                result = jobCategoryRepo.savejobMainCategorydatainredis(jobmain);
                logger.info("END: insertJobCategorydataintoRedis() :JobCategoryService");
                System.out.println(result+"Result in service");
                return result;
            } else {
                return "Failed";
            }
        } catch (Exception e) {
            System.out.println("Error in service class");
            return "Failed";
        }
    }
}

