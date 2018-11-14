package com.leoguilbor.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.leoguilbor.generic.GenericDAOImpl;
import com.leoguilbor.model.Servicem;

@Repository
public class ServicemDAO extends GenericDAOImpl<Servicem> implements IServicemDAO<Servicem>{
	public List<Servicem> listByName(String criteria) {
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
		System.out.println("rodou!#######################################");
		System.out.println();
		System.out.println("rodou!#######################################");
		if(criteria.length()>=3) {
			criteria = '%'+criteria+'%';
			return getSession().createQuery("from Servicem c where c.name like :xname").setParameter("xname", criteria).list();
		}
		return getSession().createQuery("from Servicem c ").list();

		
	}
}
