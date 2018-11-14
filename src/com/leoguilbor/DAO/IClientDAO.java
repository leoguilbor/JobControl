package com.leoguilbor.DAO;

import java.util.List;

import com.leoguilbor.generic.GenericDAO;

public interface IClientDAO<Client> extends GenericDAO<Client>{

	public List<Client> listByName(String criteria);

	
}

