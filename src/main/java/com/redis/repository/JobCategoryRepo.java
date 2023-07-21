package com.redis.repository;
import com.redis.JobCategory.JobCategory;
import com.redis.Service.JobCategoryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class JobCategoryRepo {
    private static final Log logger = LogFactory.getLog(JobCategoryService.class);
    private static final String JOB_MAINCAT="JOB_MAINCAT";
    private static final String JOB_MAINCAT_KEY="JOB_MAINCAT_KEY";
    @Autowired
    @Qualifier("redisTemplate")
    RedisTemplate template;

    public  String savejobMainCategorydatainredis(List<JobCategory> jobCategoryList){
        logger.info("START: savejobMainCategorydatainredis() :JobCategoryService");
        String result=null;
        template.opsForHash().put(JOB_MAINCAT,JOB_MAINCAT,jobCategoryList);
        System.out.println(jobCategoryList);
        result="DONE";
        logger.info("END: savejobMainCategorydatainredis() :JobCategoryService");
        return result;
    }
}
