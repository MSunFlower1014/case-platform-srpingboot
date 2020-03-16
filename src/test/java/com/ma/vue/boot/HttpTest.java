package com.ma.vue.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HttpTest {
    private static final Logger log = LoggerFactory.getLogger(HttpTest.class);

    @Autowired
    private MockMvc mvc;

    /**
     * 注入成功但是访问404
     * @throws Exception
     */
    @Test
    public void getMenu() throws Exception {
        log.info("MockMvc = {} ",mvc);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("http://127.0.0.1:8044/case-platform/api/menu/getMenus"))
                .andExpect(status().isOk())
                .andReturn();
        log.info("response = {} , content = {}",mvcResult,mvcResult.getResponse());
    }
}
