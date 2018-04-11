package com.pj.core.gereric;

import com.pj.core.feature.orm.mybatis.Page;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 
 * @ClassName: GenericServiceImpl
 * @Description: GenericService的实现类, 其他的自定义ServiceImpl, 继承自它, 可以获得常用的增删查改操作,
 *
 * @param <Model> 代表数据库中的表 映射的Java对象类型
 * @param <PK>    代表对象的主键类型
 */
public abstract class GenericServiceImpl<Model, PK> implements GenericService<Model, PK> {
	
	protected static final Logger log = Logger.getLogger(GenericServiceImpl.class);

    /**
     * 定义成抽象方法,由子类实现,完成dao的注入
     *
     * @return GenericDao实现类
     */
    public abstract GenericDao<Model, PK> getDao();

    /**
     * 插入对象
     *
     * @param model 对象
     */
    public int insert(Model model) {
        return getDao().insertSelective(model);
    }

    /**
     * 更新对象
     *
     * @param model 对象
     */
    public int update(Model model) {
        return getDao().updateByPrimaryKeySelective(model);
    }

    /**
     * 通过主键, 删除对象
     *
     * @param id 主键
     */
    public int delete(PK id) {
        return getDao().deleteByPrimaryKey(id);
    }

    /**
     * 通过主键, 查询对象
     *
     * @param id 主键
     * @return
     */
    public Model selectById(PK id) {
        return getDao().selectByPrimaryKey(id);
    }
    
    /**
     * 通过筛选条件查询
     * 
     * @param model
     * @return
     */
    public List<Model> selectBySelective(Model model, Integer pageNo, Integer pageSize) {
    	return getDao().selectBySelective(model, pageNo!=null&&pageSize!=null?(pageNo-1)*pageSize:null, pageSize);
    }

    /**
     * 查询多个对象
     * 
     * @param criteria 查询条件
     * @return Model对象集合
     */
	public List<Model> selectList(Map<String, Object> criteria) {
		return getDao().selectList(criteria);
	}
	
	/**
     * 分页查询
     * @param page
     * @param criteria
     * @return
     */
    public List<Map<String, Object>> selectByPageList(Page<Map<String, Object>> page, Map<String, Object> criteria) {
    	return getDao().selectByPageList(page, criteria);
    }
    
    public List<Map<String, Object>> selectPageList(Map<String, Object> criteria) {
    	/*String pageNo = (String) criteria.get("pageNo");
    	String pageSize = (String) criteria.get("pageSize");
		if (StringUtils.isBlank(pageNo)) {
			pageNo = "0";
			pageSize  = "10";
		} else {
			pageNo = (Integer.valueOf(pageNo) - 1) +"";
		}
    	return getDao().selectPageList(criteria,Integer.valueOf(pageNo),Integer.valueOf(pageSize));*/
    	return getDao().selectPageList(criteria,null,null);
    }
    
   /**
    * 批量删除
    */
    public int batchDelete(List<PK> ids) {
    	return getDao().batchDelete(ids);
    }
    
    /**
     * 批量新增
     */
    public int batchInsert(List<Model> models) {
    	return getDao().batchInsert(models);
    }
}
