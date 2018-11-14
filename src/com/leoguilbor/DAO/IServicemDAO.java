package com.leoguilbor.DAO;

import java.util.List;

import com.leoguilbor.generic.GenericDAO;

public interface IServicemDAO<Servicem> extends GenericDAO<Servicem>{

	public List<Servicem> listByName(String criteria);

	
}

