package com.pj.core.gereric;

import com.pj.core.feature.orm.mybatis.Page;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: GenericService
 * @Description: 所有自定义Service的顶级接口, 封装常用的增删查改操作
 *
 * @param <Model> 代表数据库中的表 映射的Java对象类型
 * @param <PK>    代表对象的主键类型
 */
public interface GenericService<Model, PK> {

    /**
     * 插入对象
     *
     * @param model 对象
     */
    int insert(Model model);

    /**
     * 更新对象
     *
     * @param model 对象
     */
    int update(Model model);

    /**
     * 通过主键, 删除对象
     *
     * @param id 主键
     */
    int delete(PK id);

    /**
     * 通过主键, 查询对象
     *
     * @param id 主键
     * @return model 对象
     */
    Model selectById(PK id);
    
    /**
     * 通过筛选条件查询
     * 
     * @param model
     * @return
     */
    List<Model> selectBySelective(Model model, Integer pageNo, Integer pageSize);

    /**
     * 查询多个对象
     * 
     * @param criteria 查询条件
     * @return Model对象集合
     */
    List<Model> selectList(Map<String, Object> criteria);
    
    /**
     * 分页查询
     * @param page
     * @param criteria
     * @return
     */
    List<Map<String, Object>> selectByPageList(Page<Map<String, Object>> page, Map<String, Object> criteria);
    
    List<Map<String, Object>> selectPageList(Map<String, Object> criteria);

    /**
     * 批量删除
     */
     public int batchDelete(List<PK> ids) ;
     
     /**
      * 批量新增
      */
     public int batchInsert(List<Model> models) ;
     
}
