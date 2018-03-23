package com.cqut.compusEC.util;

import com.cqut.compusEC.entity.Entity;

public class EntityFilter implements ClassFilter{

	@Override
	public boolean accept(Class clazz) {
		return clazz.getSuperclass().equals(Entity.class);
	}

}
