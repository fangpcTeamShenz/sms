package com.pj.core.entity;

import com.pj.core.enums.HttpStatusEnums;

import java.util.HashMap;
import java.util.Map;

public class ErrorResult extends Result {
    private static final long serialVersionUID = 8567221653356186674L;

    private Map<String, Object> errors = new HashMap<String, Object>();

    public Map<String, Object> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, Object> errors) {
        this.errors = errors;
    }

    public ErrorResult() {
    	super();
    }
    
    public ErrorResult(String message) {
    	super(message);
    }
    
    public ErrorResult(HttpStatusEnums status) {
    	super(status);
    }
    
}
