package com.pj.web.controller;

import com.pj.core.entity.ErrorResult;
import com.pj.core.entity.JSONResult;
import com.pj.core.entity.Result;
import com.pj.core.enums.HttpStatusEnums;
import com.pj.core.feature.orm.mybatis.Page;
import com.pj.core.gereric.GenericService;
import com.pj.web.annotation.SystemLog;
import com.pj.web.model.User;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @ClassName: BaseController
 * @Description: 基础控制类，所有controller都继承自此类，可以获得基础的CRUD功能
 * @date 2015-12-15
 *
 */
public abstract class BaseController<E, PK> {
	
	protected static final Logger log = Logger.getLogger(BaseController.class);
	
	protected static final String SESSION_USER = "SESSION_USER";
	
	protected final String ADD = "admin/" + getModuleName() + "/add";
	protected final String EDIT = "admin/" + getModuleName() + "/edit";
	protected final String VIEW = "admin/" + getModuleName() + "/view";
	protected final String LIST = "admin/" + getModuleName() + "/list";

	protected User getSessionUser(HttpSession session) {
		return (User)session.getAttribute(SESSION_USER);
	}
	
	@RequestMapping(value="/admin/add")
	protected String add(Model model, E entity) {
		checkPermission(getModuleName()+"_insert");
		model.addAttribute("criteria", entity);
		return ADD;
	}
	
	@RequestMapping(value = "/admin/insert", method = RequestMethod.POST)
	@SystemLog(description = "新增")
    public @ResponseBody Result insert(@Valid E entity, BindingResult result) {
		checkPermission(getModuleName()+"_insert");
		if (result.hasErrors()) {
			return getErrorResult(result);
		}
		
		Result before = insertBefore(entity);
		if (before != null) {
			return before;
		}
		
		getService().insert(entity);
		
		Result after = insertAfter(entity);
		
		if (after != null) {
			return after;
		}
		
    	return getJSONResult(HttpStatusEnums.SUCCESS, null);
    }
	
	@RequestMapping(value = "/admin/delete/{id}")
	@SystemLog(description = "删除")
    public @ResponseBody Result delete(@PathVariable("id") PK id) {
		checkPermission(getModuleName()+"_delete");
		
		Result before = deleteBefore(id);
		if (before != null) {
			return before;
		}
		
		getService().delete(id);
    	return getJSONResult(HttpStatusEnums.SUCCESS, null);
    }
	
	@RequestMapping(value="/admin/edit/{id}")
	protected String edit(Model model, @PathVariable("id") PK id) {
		checkPermission(getModuleName()+"_edit");
		model.addAttribute("item", getService().selectById(id));
		return EDIT;
	}
	
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	@SystemLog(description = "修改")
    public @ResponseBody Result update(@Valid E entity, BindingResult result, HttpServletRequest request) {
		checkPermission(getModuleName()+"_edit");
		if (result.hasErrors()) {
			return getErrorResult(result);
		}
		
		Result before = updateBefore(entity, request);
		if (before != null) {
			return before;
		}
		
		getService().update(entity);
    	return getJSONResult(HttpStatusEnums.SUCCESS, null);
    }
	
	@RequestMapping(value="/admin/view/{id}")
	protected String view(Model model, @PathVariable("id") PK id) {
		checkPermission(getModuleName()+"_view");
		E entity = getService().selectById(id);
		viewAfter(entity);
		model.addAttribute("item", entity);
		return VIEW;
	}
	
	@RequestMapping(value="/admin/list")
	protected String list(Model model, E entity) {
		checkPermission(getModuleName()+"_view");
		model.addAttribute("criteria", entity);
		return LIST;
	}
	
	@RequestMapping(value="/admin/page/list")
	@ResponseBody
	protected Page<Map<String, Object>> pageList(HttpServletRequest request) {
		checkPermission(getModuleName()+"_view");
		
		Page<Map<String, Object>> page = getPage(request);
    	Map<String, Object> criteria = getCriteria(request, getPageListCriteria());
    	
    	List<Map<String, Object>> list = getService().selectByPageList(page, criteria);
    	page.setResult(list);
    	return page;
	}

	protected Map<String, Object> getCriteria(HttpServletRequest request, String[] paras) {
		Map<String, Object> criteria = new HashMap<String, Object>();
    	if (paras != null && paras.length > 0) {
    		for (String key : paras) {
        		String value = request.getParameter(key);
        		if (StringUtils.isNotBlank(value)) {
        			try {
        				criteria.put(key.trim(), new String(value.trim().getBytes("ISO-8859-1"), "UTF-8"));
    				} catch (UnsupportedEncodingException e) {
    					log.error("Unsupported encoding exception ==>", e);
    				}
        		}
        	}
    	}
		return criteria;
	}

	protected Page<Map<String, Object>> getPage(HttpServletRequest request) {
		Page<Map<String, Object>> page;
		String pageNo = request.getParameter("pageNo");
		int n = StringUtils.isBlank(pageNo) ? 1 : Integer.parseInt(pageNo);
    	String pageSize = request.getParameter("pageSize");
    	if (StringUtils.isBlank(pageSize)) {
    		page = new Page<Map<String, Object>>(n);
    	} else {
    		page = new Page<Map<String, Object>>(n, Integer.parseInt(pageSize));
    	}
		return page;
	}

	/**
	 * 
	 * @Title: getModuleName
	 * @Description: 获得模块名称，用于权限控制和jsp保存路径，根目录为"admin/"，默认从admin/目录下获取
	 */
	protected abstract String getModuleName();
	
	/**
	 * 
	 * @Title: getService
	 * @Description: 获得Service实体，所有的Service必须继承GenericService类
	 */
	protected abstract GenericService<E, PK> getService();
	
	/**
	 * 
	 * @Title: getPageListCriteria
	 * @Description: 子类Override此方法，可实现从request中取出key对应的参数传入DAO层作为分页查询的条件
	 */
	protected String[] getPageListCriteria() {
		return null;
	};
	
	/**
	 * 
	 * @Title: insertBefore
	 * @Description: 子类Override此方法，可实现执行插入操作之前的处理
	 * @return Result 如果需要程序继续往下执行，应返回null，否则直接返回Result结果
	 */
	protected Result insertBefore(E entity) {
		return null;
	}
	
	protected Result insertAfter(E entity) {
		return null;
	}
	
	protected void viewAfter(E entity) {}

	/**
	 * 
	 * @Title: updateBefore
	 * @Description: 子类Override此方法，可实现执行更新操作之前的处理
	 * @return Result 如果需要程序继续往下执行，应返回null，否则直接返回Result结果
	 */
	protected Result updateBefore(E entity, HttpServletRequest request) {
		return null;
	}
	
	/**
	 * 
	 * @Title: deleteBefore
	 * @Description: 子类Override此方法，可实现执行删除操作之前的处理
	 * @return Result 如果需要程序继续往下执行，应返回null，否则直接返回Result结果
	 */
	protected Result deleteBefore(PK id) {
		return null;
	}
	
	/**
	 * 
	 * @Title: checkPermission
	 * @Description: 检查操作权限，保存用户权限时必须与模块名称一致
	 * @throws 无权限时抛出AuthorizationException
	 */
	protected final void checkPermission(String permission) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			throw new AuthenticationException();
		}
		if (!subject.isPermitted(permission)) {
			throw new AuthorizationException();
		}
	}
	
	/**
	 * 
	 * @Title: getErrorResult
	 * @Description: 获得校验失败的返回结果
	 */
	protected ErrorResult getErrorResult(BindingResult result) {
		ErrorResult errorResult = new ErrorResult(HttpStatusEnums.ERROR_PARAS);
		if (result != null && result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				FieldError e = (FieldError) error;
				errorResult.getErrors().put(e.getField(), e.getDefaultMessage());
			}
		}
		return errorResult;
	}
	
	/**
	 * 
	 * @Title: getErrorResult
	 * @Description: 获得校验失败的返回结果
	 * @param @param key 页面元素的name名称
	 * @param @param value 错误描述
	 */
	protected ErrorResult getErrorResult(String key, String value) {
		ErrorResult errorResult = new ErrorResult(HttpStatusEnums.ERROR_PARAS);
		errorResult.getErrors().put(key, value);
		return errorResult;
	}
	
	/**
	 * 
	 * @Title: getJSONResult
	 * @Description: 获得用于json传输的返回结果
	 */
	protected <T> JSONResult<T> getJSONResult(HttpStatusEnums status, T data) {
		JSONResult<T> result = new JSONResult<T>(status);
		result.setData(data);
		return result;
	}
	
}
