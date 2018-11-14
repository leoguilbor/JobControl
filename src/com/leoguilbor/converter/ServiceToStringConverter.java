package com.leoguilbor.converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.leoguilbor.generic.GenericDAO;
import com.leoguilbor.model.Servicem;
@Component
public class ServiceToStringConverter implements Converter<Servicem,String> {
	

	@Autowired	
	private GenericDAO<Servicem> servicemDAO;

    public GenericDAO<Servicem> getServiceDAO() {
        return this.servicemDAO;
    }
	public void setServiceDAO(GenericDAO<Servicem> servicemDAO) {
	        this.servicemDAO = servicemDAO;
	}
	
	
    public String convert(Servicem from) {
    	System.out.println("action");
        return from.getId().toString();
    }
}
