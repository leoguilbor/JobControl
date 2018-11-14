/**
 *     This Class is an implementation of ICategoryDAO. In this example is used decorator design pattern 
 *     permitting a restructure of DAO for a specific model         
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
