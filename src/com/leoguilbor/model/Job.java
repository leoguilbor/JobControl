/**
 *     This is a class from model Tier for Job entity.
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

package com.leoguilbor.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

import com.leoguilbor.generic.GenericModelImpl;

@Repository
@Entity
@Table(name="job", schema="job")
public class Job extends GenericModelImpl{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
	private Servicem servicem;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
	private Client client;
	
	private String date;
	
	
	public Job() {
		this.servicem = new Servicem();
		this.client = new Client();
	}
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Servicem getServicem() {
		return servicem;
	}
	public void setServicem(Servicem servicem) {
		this.servicem = servicem;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Job))
			return false;
		if (obj == this)
			return true;
		return this.getId() == ((Job) obj).getId();
	}

	@Override
	public int hashCode() {
        try {
            if (id != null) {
                    return new Long(id).hashCode();
            }
            return 1;
    } catch (Exception e) {
            return -1;
    }
	}
}
