package com.leoguilbor.generic;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface GenericDAO<T> {

	public Long save(T t);
	public Boolean update(T t);
	public Boolean delete(T t);
	public Boolean delete(Long id);
	public List<T> list();
	public List<T> list(String criteria);
	public T getById(Long id);
	public String listAsJson(String criteria) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	public String listToJson(List<T> listByName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
}

