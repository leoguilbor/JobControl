/**
 *     Class responsible for Controlling view interactions from Category domain  
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

package com.leoguilbor.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leoguilbor.DAO.ICategoryDAO;
import com.leoguilbor.business.AuthorizationBusiness;
import com.leoguilbor.model.Category;

@Controller
public class CategoryController {

	@Autowired(required = true)
	private ICategoryDAO categoryDAO;
	
	@Autowired
	private AuthorizationBusiness authorizationB;

	public void setClientDAO(ICategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@RequestMapping(value = {"/listCategories" }, method = RequestMethod.GET)
	public String listServices(@ModelAttribute("category") Category category, BindingResult br, ModelMap model, HttpServletRequest request) {
		
		model.addAttribute("links", authorizationB.listActions());

		return "services/listServices";
	}
	
	@RequestMapping(value = {"/category/{category}" }, method = RequestMethod.GET)
	public String showService(@ModelAttribute("category") Category category, BindingResult br, ModelMap model, HttpServletRequest request) {
	
			System.out.println(category.getName());
			model.addAttribute("category", category);

		return "services/showService";
	}
	@RequestMapping(value = "/a/listCategory" , method = RequestMethod.GET)
	public void listServicesAjax(HttpServletRequest req,HttpServletResponse res) {
		
		
		String json = "";
		try {

			 json = categoryDAO.listAsJson("");

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			res.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.setContentType("application/json");
		res.setStatus(200);
		
	}
	@RequestMapping(value = "/a/listCategory/{field}/{value}" , method = RequestMethod.PUT)
	public void searchServicesAjax(@PathVariable("field") String field, @PathVariable("value") String value,HttpServletRequest req,HttpServletResponse res) {
		
		
		String json = "";
		try {

			 json = categoryDAO.listToJson(categoryDAO.listByName(value));

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			res.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.setContentType("application/json");
		res.setStatus(200);
		
	}
	
	@RequestMapping(value = "/a/deleteCategory/{id}", method = RequestMethod.DELETE)
	public void deleteServiceAjax(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response) {
		
		Enumeration<String> headerNames = request.getParameterNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			System.out.println("Header Name: <em>" + headerName);
			String headerValue = request.getParameter(headerName);
			System.out.println("</em>, Header Value: <em>" + headerValue.toString());
			System.out.println("</em><br/>");
		}
		System.out.println(id);
		Boolean resp = categoryDAO.delete(id);
		
		try {
			response.getWriter().write("{}");
			response.setContentType("application/x-www-form-urlencoded");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setStatus(200);
	}

	@RequestMapping(value = "/a/editCategory/{id}", method = RequestMethod.PUT)
	public void loadServiceAjax(@PathVariable("id") Long id, HttpServletRequest req, HttpServletResponse res) throws JSONException, NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException {

		Category s = (Category) categoryDAO.getById(id);
		res.getWriter().write(s.toJson());
		res.setContentType("application.json");
		res.setStatus(200);

	}
	
	
	@RequestMapping(value = "/a/addCategory", method = RequestMethod.POST)
	public void addServiceAjax(@ModelAttribute("category") Category category, ModelMap model, HttpServletRequest req,
			
			HttpServletResponse res) {
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// List<Client> clients = new ArrayList<Client>();


		Long generatedId = null;


		try {
			System.out.println(category.getId());
			
			if (category.getId() == null) {
				
				generatedId = categoryDAO.save(category);
			}
			else {
				System.out.println("edit");
				categoryDAO.update(category);
				generatedId = category.getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println();
			
			res.setStatus(501);
		}

		try {
			Category newCategory = (Category) categoryDAO.getById(generatedId);


			res.getWriter().write(newCategory.toJson());
			res.setContentType("application/json");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		res.setStatus(200);
	}

}
