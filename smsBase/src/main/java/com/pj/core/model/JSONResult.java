package com.pj.core.model;

import com.pj.core.model.Result;
import com.pj.core.enums.HttpStatusEnums;

public class JSONResult<T> extends Result {

    private static final long serialVersionUID = 7880907731807860636L;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

	public JSONResult() {
        super();
    }
    
    public JSONResult(HttpStatusEnums status) {
        super(status);
    }

}