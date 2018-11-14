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

import com.leoguilbor.DAO.IServicemDAO;
import com.leoguilbor.business.MenuBusiness;
import com.leoguilbor.model.Servicem;

@Controller
public class ServiceController {

	@Autowired(required = true)
	private IServicemDAO servicemDAO;
	
	@Autowired
	private MenuBusiness menuB;

	public void setClientDAO(IServicemDAO serviceDAO) {
		this.servicemDAO = serviceDAO;
	}

	@RequestMapping(value = {"/listServices" }, method = RequestMethod.GET)
	public String listServices(@ModelAttribute("servicem") Servicem servicem, BindingResult br, ModelMap model, HttpServletRequest request) {
		
		model.addAttribute("links", menuB.listActions());

		return "services/listServices";
	}
	
	@RequestMapping(value = {"/servicem/{service}" }, method = RequestMethod.GET)
	public String showService(@ModelAttribute("servicem") Servicem servicem, BindingResult br, ModelMap model, HttpServletRequest request) {
	
			System.out.println(servicem.getName());
			model.addAttribute("servicem", servicem);

		return "services/showService";
	}
	@RequestMapping(value = "/a/listServicem" , method = RequestMethod.GET)
	public void listServicesAjax(HttpServletRequest req,HttpServletResponse res) {
		
		
		String json = "";
		try {

			 json = servicemDAO.listAsJson("");

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
	@RequestMapping(value = "/a/listServicem/{field}/{value}" , method = RequestMethod.PUT)
	public void searchServicesAjax(@PathVariable("field") String field, @PathVariable("value") String value,HttpServletRequest req,HttpServletResponse res) {
		
		
		String json = "";
		try {

			 json = servicemDAO.listToJson(servicemDAO.listByName(value));

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
	
	@RequestMapping(value = "/a/deleteServicem/{id}", method = RequestMethod.DELETE)
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
		Boolean resp = servicemDAO.delete(id);
		
		try {
			response.getWriter().write("{}");
			response.setContentType("application/x-www-form-urlencoded");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setStatus(200);
	}

	@RequestMapping(value = "/a/editServicem/{id}", method = RequestMethod.PUT)
	public void loadServiceAjax(@PathVariable("id") Long id, HttpServletRequest req, HttpServletResponse res) throws JSONException, NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException {

		Servicem s = (Servicem) servicemDAO.getById(id);
		res.getWriter().write(s.toJson());
		res.setContentType("application.json");
		res.setStatus(200);

	}
	
	
	@RequestMapping(value = "/a/addServicem", method = RequestMethod.POST)
	public void addServiceAjax(@ModelAttribute("servicem") Servicem servicem, ModelMap model, HttpServletRequest req,
			
			HttpServletResponse res) {
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// List<Client> clients = new ArrayList<Client>();

		System.out.println(servicem.getCategory().getName() + " "+servicem.getCategory().getDescription());

		Long generatedId = null;


		try {
			System.out.println(servicem.getId());
			
			if (servicem.getId() == null) {
				
				generatedId = servicemDAO.save(servicem);
			}
			else {
				System.out.println("edit");
				servicemDAO.update(servicem);
				generatedId = servicem.getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println();
			
			res.setStatus(501);
		}

		try {
			Servicem newServicem = (Servicem) servicemDAO.getById(generatedId);


			res.getWriter().write(newServicem.toJson());
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
