package com.leoguilbor.DAO;

import java.util.List;

import com.leoguilbor.generic.GenericDAO;

public interface ICategoryDAO<Category> extends GenericDAO<Category>{

	public List<Category> listByName(String criteria);

	
}

