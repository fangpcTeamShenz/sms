package com.pj.core.gereric;

import com.pj.core.feature.orm.mybatis.Page;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * @ClassName: GenericDao
 * @Description: 所有自定义Dao的顶级接口, 封装常用的增删查改操作,
 *               可以通过Mybatis Generator Maven 插件自动生成Dao,
 *               也可以手动编码, 然后继承GenericDao 即可.
 *
 * @param <Model> 代表数据库中的表 映射的Java对象类型
 * @param <PK>    代表对象的主键类型
 */
public interface GenericDao<Model, PK> {

    /**
     * 插入对象
     *
     * @param model 对象
     */
    int insertSelective(Model model);

    /**
     * 更新对象
     *
     * @param model 对象
     */
    int updateByPrimaryKeySelective(Model model);

    /**
     * 通过主键, 删除对象
     *
     * @param id 主键
     */
    int deleteByPrimaryKey(PK id);

    /**
     * 通过主键, 查询对象
     *
     * @param id 主键
     * @return
     */
    Model selectByPrimaryKey(PK id);
    
    /**
     * 通过筛选条件查询
     * 
     * @param model
     * @return
     */
    List<Model> selectBySelective(@Param("model") Model model, 
    		@Param("startWith") Integer pageNo, @Param("pageSize") Integer pageSize);
    
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

    /**
     * 批量删除
     */
     public int batchDelete(@Param("ids") List<PK> ids) ;
     
     /**
      * 批量新增
      */
     public int batchInsert(@Param("models") List<Model> models);

     public List<Map<String, Object>> selectPageList(@Param("params") Map<String, Object> criteria,@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);
}
