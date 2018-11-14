package com.leoguilbor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

import com.leoguilbor.generic.GenericModelImpl;

@Repository
@Entity
@Table(name="actions", schema="auth")
public class Actions extends GenericModelImpl{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
    private String name;
    private String description;
    private String uri;
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
    
  
}