package com.zdcf.base;
import java.io.Serializable;

import com.zdcf.base.Constants.Cache.Type;

public class InfoChangeEvent implements Serializable{
	private Type type;
	
	private Object object;
	
	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
}