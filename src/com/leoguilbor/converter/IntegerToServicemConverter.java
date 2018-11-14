package com.leoguilbor.converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.leoguilbor.generic.GenericDAO;
import com.leoguilbor.model.Servicem;
@Component
public class IntegerToServicemConverter implements Converter<Integer, Servicem> {
	
	

	@Autowired	
	private GenericDAO<Servicem> servicemDAO;

    public GenericDAO<Servicem> getServiceDAO() {
        return this.servicemDAO;
    }
	public void setServiceDAO(GenericDAO<Servicem> servicemDAO) {
	        this.servicemDAO = servicemDAO;
	}
	
	
    public Servicem convert(Integer from) {
    	System.out.println("action");
        return servicemDAO.getById(new Long(from));
    }
}
