package com.leoguilbor.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.leoguilbor.generic.GenericDAOImpl;
import com.leoguilbor.model.Category;

@Repository
public class CategoryDAO extends GenericDAOImpl<Category> implements ICategoryDAO<Category>{
	
	public List<Category> listByName(String criteria) {
		if(criteria.length()>=3) {
			criteria = '%'+criteria+'%';
			return getSession().createQuery("from Category c where c.name like :xname").setParameter("xname", criteria).list();
		}
		return getSession().createQuery("from Category c ").list();

		
	}
}
