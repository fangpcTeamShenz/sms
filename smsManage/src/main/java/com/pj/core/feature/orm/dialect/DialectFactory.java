package com.pj.core.feature.orm.dialect;

import com.pj.core.util.Logs;

import org.apache.ibatis.session.Configuration;

public class DialectFactory {

    public static String dialectClass = null;

    public static Dialect buildDialect(Configuration configuration) {
        if (dialectClass == null) {
            synchronized (DialectFactory.class) {
                if (dialectClass == null) {
                    dialectClass = configuration.getVariables().getProperty("dialectClass");
                }
            }
        }
        Dialect dialect = null;
        try {
            dialect = (Dialect) Class.forName(dialectClass).newInstance();
        } catch (Exception e) {
        	Logs.error("请检查 mybatis-config.xml 中  dialectClass 是否配置正确?", e);
        }
        return dialect;
    }
}
