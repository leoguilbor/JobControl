/**
 *     Class responsible for Controlling view interactions from Job domain  
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
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leoguilbor.business.AuthorizationBusiness;
import com.leoguilbor.generic.GenericDAO;
import com.leoguilbor.model.Job;
 
@Controller
public class JobController {

	@Autowired(required = true)
	private GenericDAO<Job> jobDAO;
	@Autowired
	private AuthorizationBusiness authorizationB;
	@Autowired
	private ConversionService conversionService;
	
	
	@RequestMapping(value = "/listJobs" , method = RequestMethod.GET)
	public String listJobs(ModelMap model) {

		model.addAttribute("links", authorizationB.listActions());
		model.addAttribute("job", new Job());

		return "jobs/listJobs";
	}
	
	@RequestMapping(value = "/job" , method = RequestMethod.GET)
	public String showJob(@ModelAttribute("job") Job job ,BindingResult br, ModelMap model, HttpServletRequest request) {

		model.addAttribute("links", authorizationB.listActions());

		return "jobs/job";
	}
	
	@RequestMapping(value = "/a/listJob" , method = RequestMethod.GET)
	public void listarJobAjax(HttpServletResponse res) {

		String json = "";
		try {
			 json = jobDAO.listAsJson(null);
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
			res.setContentType("application/json");
			res.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		res.setStatus(200);
		
	}
	
	@RequestMapping(value = "/a/deleteJob/{id}", method = RequestMethod.DELETE)
	public void deleteJobAjax(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response) {
		
		Boolean resp = jobDAO.delete(id);
		
		try {
			response.setContentType("application/json");
			response.getWriter().write("{}");

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setStatus(200);
	}
	
	@RequestMapping(value = "/a/editJob/{id}", method = RequestMethod.PUT)
	public void loadJobAjax(@PathVariable("id") Long id, HttpServletRequest req, HttpServletResponse res) throws JSONException, NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException {

		res.setContentType("application/json");
		res.getWriter().write(jobDAO.getById(id).toJson());
		res.setStatus(200);

	}
	@RequestMapping(value = "/a/addJob", method = RequestMethod.POST)
	public void addJobAjax(@ModelAttribute Job job, BindingResult br, ModelMap model, HttpServletRequest req,
			HttpServletResponse res) {
		
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// List<Client> clients = new ArrayList<Client>();

		Enumeration<String> headerNames = req.getParameterNames();
		br.getAllErrors();
		

		
		for(ObjectError obje: br.getAllErrors()) {
			System.out.println(obje.toString());
		}
		
		
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			System.out.println("Header Name: <em>" + headerName);
			String headerValue = req.getParameter(headerName);
			System.out.println("</em>, Header Value: <em>" + headerValue.toString());
			System.out.println("</em><br/>");
		}
		
			


			
		Long generatedId = null;

		try {
			
			if (job.getId() == null) {
				
				System.out.println(job.getName());
				System.out.println(job.getClient().getId());
				System.out.println(job.getServicem().getName());
				
				generatedId = jobDAO.save(job);
				
				
			}
			else {
				System.out.println("edit");
				jobDAO.update(job);
				generatedId = job.getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println();

			res.setStatus(500);
		}

		try {
			Job newJob = jobDAO.getById(generatedId);

			res.setContentType("application/json");
			res.getWriter().write(newJob.toJson());

			
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