package com.albenyuan.mybatis.mapper;

import com.albenyuan.core.data.mapper.BaseMapper;
import com.albenyuan.mybatis.model.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Alben Yuan
 * @Date 2018-09-29 12:05
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    Menu findById(Long id);

    List<Menu> findByParentId(Long parentId);

    Menu findParentById(Long id);

    List<Menu> findTreeMenuByParentId(Long parentId);

    Menu findMenuWithParentById(Long id);

}
