package com.leoguilbor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Repository;

import com.leoguilbor.generic.GenericModelImpl;

@Repository
@Entity
@Table(name="client", schema="client")
public class Client extends GenericModelImpl{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Size(min=1,max=50)
	private String name;
	@NotBlank
	@Size(min=1,max=50)    
    private String address;
	@NotBlank
	@Size(min=1,max=50)        
    private String city;
    @NumberFormat(pattern="(##)#####-####")
    @NotBlank
    private String telephone;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Client))
			return false;
		if (obj == this)
			return true;
		return this.getId() == ((Servicem) obj).getId();
	}

	@Override
	public int hashCode() {
		if (id == null)
			return -1;
		return id.intValue();
	} 
     
  
}