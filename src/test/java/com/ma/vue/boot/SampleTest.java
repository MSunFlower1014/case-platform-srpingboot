package com.ma.vue.boot;

import com.ma.vue.boot.entity.CaseEntity;
import com.ma.vue.boot.entity.Coffee;
import com.ma.vue.boot.entity.UserEntity;
import com.ma.vue.boot.service.ICaseEntityService;
import com.ma.vue.boot.service.IUserService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableCaching(proxyTargetClass = true)
public class SampleTest {
    private static final Logger logger = LoggerFactory.getLogger(SampleTest.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private ICaseEntityService caseEntityService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 通过value将spring配置文件中的配置信息绑定到对应属性
     */
    @Value("${case.name}")
    private String caseName;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("123456");
        userEntity.setUserName("mayantao");
        userService.save(userEntity);
//        List<UserEntity> userList = userMapper.selectList(null);
//        Assert.assertEquals(5, userList.size());
//        userList.forEach(System.out::println);
    }

    @Test
    public void testSave() {
        System.out.println(("----- selectAll method test ------"));
        CaseEntity byId = caseEntityService.getById("0379d9c452e3344538ef9afe46c494ce");
        byId.setType("test");
        byId.setName("mayao");
        byId.setHospital("3");
        caseEntityService.saveOrUpdate(byId);
        System.out.println(byId);
    }

    @Test
    public void mongoTest() throws JSONException, InterruptedException {
        Coffee build = Coffee.builder().name("马艳涛").price(Money.of(CurrencyUnit.of("CNY"), 20.0)).build();
        Coffee save = mongoTemplate.save(build);

        logger.info(" mongoTemplate save  return {}",save);
        List<Coffee> idList = mongoTemplate.find(Query.query(Criteria.where("name").is("马艳涛")), Coffee.class);
        idList.forEach(c -> logger.info("idList foreach {}",c));

        Thread.sleep(1000);
        UpdateResult updateResult = mongoTemplate.updateFirst(Query.query(Criteria.where("name").is("马艳涛")), new Update().set("price", Money.of(CurrencyUnit.of("CNY"), 30.0)), Coffee.class);

        logger.info(" mongoTemplate update  return {}",updateResult);

        Coffee byId = mongoTemplate.findById(save.getId(), Coffee.class);

        DeleteResult remove = mongoTemplate.remove(build);
        logger.info(" mongoTemplate remove  return {}",remove);
    }

    @Test
    public void redisTest(){
        logger.info("jedisPoolConfig {}",jedisPoolConfig);
        logger.info("jedisPool {}",jedisPool);

        try(Jedis jedis = jedisPool.getResource()){
            jedis.set("name","mayantao");
            String name = jedis.get("name");
            logger.info("jedis get name {} ",name);
            Long name1 = jedis.del("name");
            logger.info("jedis del name {} ",name1);
        }
    }

    @Test
    public void cacheTest() throws InterruptedException {
        logger.info("case List {}",caseEntityService.findAllCase());
        for(int i=0;i<5;i++){
            List<CaseEntity> allCase = caseEntityService.findAllCase();
            logger.info("getFrom Cache");
        }
        Thread.sleep(5000);
        caseEntityService.reloadAllCase();
        logger.info("case List {}",caseEntityService.findAllCase());
    }

    @Test
    public void propertiesTest(){
        logger.info("caseName : = {}",caseName);
    }
}
