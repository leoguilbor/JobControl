package com.leoguilbor.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.leoguilbor.generic.GenericDAOImpl;
import com.leoguilbor.model.Client;

@Repository
public class ClientDAO extends GenericDAOImpl<Client> implements IClientDAO<Client>{

	
	
	
	public List<Client> listByName(String criteria) {
		// TODO Auto-generated method stub
/*		StringBuilder sql = new StringBuilder();
		sql.append("from ");
//		sql.append(this.clazz);
		try { 
			if (!criteria.isEmpty()) {
				sql.append(" where ");
				sql.append(criteria);
			}
		}catch (NullPointerException e) {
			return new ArrayList<T>();
		}*/
		
		if(criteria.length()>=3) {
			criteria = '%'+criteria+'%';
			return getSession().createQuery("from Client c where c.name like :xname").setParameter("xname", criteria).list();
		}
		return getSession().createQuery("from Client c ").list();
	}
	
}
