package com.leoguilbor.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoguilbor.DAO.ActionsDAO;
import com.leoguilbor.generic.GenericBusinessImpl;
import com.leoguilbor.generic.GenericDAO;
import com.leoguilbor.model.Actions;

@Service
public class MenuBusiness extends GenericBusinessImpl<Actions> {

	@Autowired(required=true)
	private GenericDAO<Actions> actionsDAO;
	
	public GenericDAO<Actions> getActionsDAO() {
		return actionsDAO;
	}

	public void setActionsDAO(ActionsDAO actionsDAO) {
		this.actionsDAO = actionsDAO;
	}
	
	public List<Actions> listActions(){
		return actionsDAO.list();
	}
}
