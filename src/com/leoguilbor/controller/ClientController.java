/**
 *     Class responsible for Controlling view interactions from Client domain  
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leoguilbor.DAO.IClientDAO;
import com.leoguilbor.business.AuthorizationBusiness;
import com.leoguilbor.model.Client;

@Controller
public class ClientController {

	@Autowired(required = true)
	private IClientDAO clientDAO;
	@Autowired
	private AuthorizationBusiness authorizationB;

	public void setClientDAO(IClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	@RequestMapping(value = { "/", "/listClients" }, method = RequestMethod.GET)
	public String listarClientes(@ModelAttribute("client") Client client, BindingResult br, ModelMap model,
			HttpServletRequest request) {

		model.addAttribute("links", authorizationB.listActions());

		return "clients/listClients";
	}

	@RequestMapping(value = "/a/listClient", method = RequestMethod.GET)
	public void listarClientesAjax(HttpServletResponse res) {

		String json = "";
		try {
			json = clientDAO.listAsJson(null);
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

	@RequestMapping(value = "/a/listClient/{field}/{value}", method = RequestMethod.PUT)
	public void searchServicesAjax(@PathVariable("field") String field, @PathVariable("value") String value,
			HttpServletRequest req, HttpServletResponse res) {

		String json = "";
		try {

			json = clientDAO.listToJson(clientDAO.listByName(value));

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

	@RequestMapping(value = "/a/deleteClient/{id}", method = RequestMethod.DELETE)
	public void deleteCliente(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {

		Boolean resp = clientDAO.delete(id);

		try {
			response.setContentType("application/json");
			response.getWriter().write("{}");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setStatus(200);
	}

	@RequestMapping(value = "/a/editClient/{id}", method = RequestMethod.PUT)
	public void cliente(@PathVariable("id") Long id, HttpServletRequest req, HttpServletResponse res)
			throws JSONException, NoSuchMethodException, SecurityException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException, IOException {

		Client c = (Client) clientDAO.getById(id);

		res.getWriter().write(c.toJson());
		res.setContentType("application/json");
		res.setStatus(200);

	}

	@RequestMapping(value = "/a/addClient", method = RequestMethod.POST)
	public void adicionarClienteAjax(@Valid @ModelAttribute("client") Client client, BindingResult result, ModelMap model,
			HttpServletRequest req, HttpServletResponse res) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// List<Client> clients = new ArrayList<Client>();

		Long generatedId = null;

		if (!result.hasErrors()) {
			try {

				if (client.getId() == null) {
					generatedId = clientDAO.save(client);
				} else {
					System.out.println("edit");
					clientDAO.update(client);
					generatedId = client.getId();
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println();

				res.setStatus(500);
			}

			try {
				Client newClient = (Client) clientDAO.getById(generatedId);

				System.out.println(client.getAddress());
				System.out.println(client.toJson());
				res.setContentType("application/json");
				res.getWriter().write(newClient.toJson());

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
		} else {
			String value ="{"; 
			
				for (FieldError error : result.getFieldErrors()) {
					value += "'"+error.getField()+"':'"+error.getDefaultMessage()+"',";
				}
				
			value = value.substring(0, value.length()-1);
			value += '}';
			
			res.getWriter().write(value);
			res.setContentType("application/json");			
			res.setStatus(201);
		}
	}
}
