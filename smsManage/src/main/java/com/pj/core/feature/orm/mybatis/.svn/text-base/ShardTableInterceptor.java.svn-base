package com.pj.core.feature.orm.mybatis;

import com.pj.core.feature.orm.dialect.MySql5PageHepler;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Intercepts(value = { @Signature(type = Executor.class,  method = "update",  args = {MappedStatement.class, Object.class}),
					  @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class ShardTableInterceptor implements Interceptor{
	
	private final static Logger logger = LoggerFactory.getLogger(ShardTableInterceptor.class);

    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	
	private static List<String> tableFilers = new ArrayList<String>();//分表集合
	
	static {
		// 命名 不可以有了t_user_sms 不可再创建t_user_sms_content表
//		tableFilers.add("t_user_sms_route");
//		tableFilers.add("t_user_sms_message");
//		tableFilers.add("t_user_sms_content");
//		tableFilers.add("t_user_order");
//		tableFilers.add("t_user_bill");
	}
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object object = invocation.getTarget();
		BoundSql boundSql = null;
		MetaObject metaStatementHandler = null;
		if(object instanceof StatementHandler){
			//查询语句
			StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
			//获取原sql
	        boundSql = statementHandler.getBoundSql();
			// 保存会话信息  
			metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		}if (object instanceof Executor) {
			//
			 final Object[] args = invocation.getArgs();  
            //获取原始的ms  
            MappedStatement ms = (MappedStatement) args[0];
            
            Object parameterObject = args[1];  
            boundSql = ms.getBoundSql(parameterObject);  
            
            StatementHandler statementHandler = new PreparedStatementHandler((Executor)object, ms, parameterObject, null, null, boundSql);
    		// 保存会话信息  
    		metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		}
        
        // 重写sql
        String newSql = getSql(boundSql);
        logger.debug(newSql);
        
        // 替换sql
        metaStatementHandler.setValue("boundSql.sql", newSql);
        //将执行权交给下一个拦截器  
        return invocation.proceed();
	}
	
	// 获取分表后的sql  
    private String getSql(BoundSql boundSql) throws ClassCastException{  
        // 0、获取sql  
    	String sql = MySql5PageHepler.getLineSql(boundSql.getSql()).trim();
        // 1、获取表名  
        List <String> tableName = getTableName(sql);
        // 2、获取新sql  
        return getShardSql(sql, tableName, boundSql.getParameterMappings(), boundSql.getParameterObject());  
    }
    
    // 构造新sql
	@SuppressWarnings("unchecked")
	private String getShardSql(String sql, List<String> tableName,List<ParameterMapping> parameterMappings, Object parameterObject) throws ClassCastException {
		// 判断是否需要路由策略
		String userId = null;
		for (String table : tableName) {
			if(tableFilers.contains(table)){
				try {
					Map<String, Object> params = (Map<String, Object>) parameterObject;
					Map<String, Object> param = (Map<String, Object>)params.get("params");
					userId = String.valueOf(param.get("userId"));
				} catch (Exception e) {
					Map<String, Object> params = (Map<String, Object>) parameterObject;
					userId = String.valueOf(params.get("userId"));
				}
				
				String newTable = table + "_" + userId;
				sql = sql.replaceAll(table, newTable);
				
			}
		}
		
		return sql;
	}

	private List <String> getTableName(String sql) {
		// 根据sql获取表名  
	    String[] sqls = sql.split("\\s+");
	    // 兼容多表查询
	    List <String> tableNames = new ArrayList<String>();
		for (int i = 0; i < sqls.length; i++) {
			if (sqls[i].equalsIgnoreCase("from") || sqls[i].equalsIgnoreCase("join")) {
				if(sqls[i+1].equals("(")){
					continue;
				}
				tableNames.add(sqls[i + 1]);
			} else if(sqls[i].equalsIgnoreCase("update")){
				tableNames.add(sqls[i + 1]);
			}
			
		}
		
		return tableNames;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}

}
