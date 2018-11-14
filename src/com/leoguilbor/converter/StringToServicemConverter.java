package com.leoguilbor.converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.leoguilbor.generic.GenericDAO;
import com.leoguilbor.model.Servicem;

@Component
public class StringToServicemConverter implements Converter<String, Servicem> {
	
	@Autowired	
	private GenericDAO<Servicem> servicemDAO;

    public GenericDAO<Servicem> getServiceDAO() {
        return this.servicemDAO;
    }
	public void setServiceDAO(GenericDAO<Servicem> servicemDAO) {
	        this.servicemDAO = servicemDAO;
	}
	
	@Override	
    public Servicem convert(String from) {
    	System.out.println("action StrxS");
        return servicemDAO.getById(Long.valueOf((java.lang.String) from));
    }
    
}
