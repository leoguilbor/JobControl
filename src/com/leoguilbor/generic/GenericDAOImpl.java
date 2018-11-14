package com.leoguilbor.generic;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Transactional
@Scope("session")
public class GenericDAOImpl<T> implements GenericDAO<T> {
    
	@Autowired
	private SessionFactory sessionFactory;
	
    public SessionFactory getSessionFactory() {
    	System.out.println(sessionFactory);
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Class<T> clazz;
    
    public GenericDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}

    public Session getSession() {
    	Session s = this.getSessionFactory().getCurrentSession(); 

    	return s;
    }

	@Override
	public Long save(T t) {
		// TODO Auto-generated method stub
		Long generatedId=0L;
		try {
			generatedId = (Long) this.getSession().save(t);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1L;
			
		}
		return (Long) generatedId;
	}

	@Override
	public Boolean update(T t) {
		try {
			this.getSession().update(t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean delete(T t) {
		try {
			this.getSession().delete(t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean delete(Long id) {
		try {
			this.getSession().delete(this.getById(id));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<T> list() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(clazz.getName()).list();
	}

	@Override
	public List<T> list(String criteria) {
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
		
		return getSession().createCriteria(clazz.getName()).list();
	}

	
	
	@Override
	public String listAsJson(String criteria) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
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
		
		List<T> list = this.list(criteria);
		
		
		return this.listToJson(list);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T getById(Long id) {
		// TODO Auto-generated method stub
		return (T) getSession().get(clazz.getName(), (Serializable) id);
	}

	@Override
	public String listToJson(List<T> listByName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		
		StringBuilder json = new StringBuilder();
		json.append("{ ");
		Long i = 0L;
		for (T t : listByName) {
			System.out.println("valor json:"+clazz.getMethod("toJson", null).invoke(t, null));
			
			json.append("\""+ i.toString() + "\":");
			json.append(clazz.getMethod("toJson", null).invoke(t, null));	
			json.append(',');
			i++;
		}
		json.replace(json.length()-1, json.length(), "");
		json.append("}");
		return json.toString();
	}


}
