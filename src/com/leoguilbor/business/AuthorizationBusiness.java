/**
 *     Class responsible for Business rules from Authorization domain  
 *     Copyright (C) 2018 Leandro Lima
 * 
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.leoguilbor.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoguilbor.DAO.ActionsDAO;
import com.leoguilbor.generic.GenericBusinessImpl;
import com.leoguilbor.generic.GenericDAO;
import com.leoguilbor.model.Actions;

@Service
public class AuthorizationBusiness extends GenericBusinessImpl<Actions> {

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
