package com.albenyuan.mybatis.mapper;

import com.albenyuan.core.data.SpringBaseTestCase;
import com.albenyuan.mybatis.model.Menu;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author Alben Yuan
 * @Date 2018-09-29 13:25
 */
public class MenuMapperTest extends SpringBaseTestCase {

    private static final Logger logger = LoggerFactory.getLogger(MenuMapperTest.class);

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void findByParentId() {
        List<Menu> list = menuMapper.findByParentId(-1l);
        logger.debug("list:{}", list);
        Assert.assertNotNull("menus parent must be null", list);
        Assert.assertFalse("menu's parent must be null", list.isEmpty());
    }

    @Test
    public void findParentById() {
        Menu menu = menuMapper.findParentById(-1l);
        logger.debug("menu:{}", menu);
        Assert.assertNotNull("menu's parent must be null", menu);
    }

    @Test
    public void findTreeMenuByParentId() {
        List<Menu> list = menuMapper.findTreeMenuByParentId(-1l);
        logger.info("tree:{}", list);
    }

    @Test
    public void findMenuWithParentById() {
        Menu menu = menuMapper.findMenuWithParentById(3l);
        logger.info("menu:{}", menu);
    }
}
