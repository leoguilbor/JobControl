package com.leoguilbor.converter;
import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.leoguilbor.generic.GenericDAO;
import com.leoguilbor.model.Servicem;

@Component
public class StringToServicemFormatter implements Formatter<Servicem> {
	
	@Autowired	
	private GenericDAO<Servicem> servicemDAO;

    public GenericDAO<Servicem> getServiceDAO() {
        return this.servicemDAO;
    }
	public void setServiceDAO(GenericDAO<Servicem> servicemDAO) {
	        this.servicemDAO = servicemDAO;
	}
	
	@Override
	public String print(Servicem object, Locale locale) {
		// TODO Auto-generated method stub
		return object.getId().toString();
	}
	@Override
	public Servicem parse(String text, Locale locale) throws ParseException {
		// TODO Auto-generated method stub
		return servicemDAO.getById(Long.valueOf((java.lang.String) text));
	}
    
}
