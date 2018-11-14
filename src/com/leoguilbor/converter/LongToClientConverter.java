package com.leoguilbor.converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.leoguilbor.generic.GenericDAO;
import com.leoguilbor.model.Client;

@Component
public class LongToClientConverter implements Converter<Long, Client> {
	
	
	
	@Autowired		
	private GenericDAO<Client> clientDAO;


	public GenericDAO<Client> getClientDAO() {
        return this.clientDAO;
    }
	public void setClientDAO(GenericDAO<Client> clientDAO) {
	        this.clientDAO = clientDAO;
	}
	
    public Client convert(Long from) {
    	
    	System.out.println("action LxC");
    	
        return clientDAO.getById(from);
        
    }

}