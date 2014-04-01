package com.kaart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.client.Client;

/**
 * Servlet implementation class ActionServlet
 */

public class KaartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Client client;

	public KaartServlet() {
		// TODO Auto-generated constructor stub
		client = new Client();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equalsIgnoreCase("findbycategory")){
			//k�sib vastavalt kategooriale
			String category = request.getParameter("category").trim().toLowerCase();
			client.setLoc_name(category);
			client.startRunning();
		}
		else if(method.equalsIgnoreCase("getpointdescription")){
			String id = request.getParameter("id").trim().toLowerCase();
			client.setId(Long.parseLong(id));
			client.startRunning();
		}
		else if(method.equalsIgnoreCase("insertnewpoint")){
			String name = request.getParameter("name").trim();
			String desc = request.getParameter("desc").trim();
			String link = request.getParameter("link").trim();
			String location = request.getParameter("location");
			String cat = request.getParameter("categories").trim();
//			System.out.println("name " + name +" location "+toPoint(location)+ " desc " + desc + " link " + link  + " cat " + toCategory(cat));
			client.setLoc_name(name);
			client.setLoc_location(toPoint(location));
			client.setLoc_description(desc);
			client.setLoc_link(link);
			client.setCategoryTags(toCategory(cat));
			client.setInsert(true);
			client.startRunning();
		}else if(method.equalsIgnoreCase("findupdates")){
			String category = request.getParameter("category").trim();
			String last_id = request.getParameter("lastid").trim();
			client.setId(Long.parseLong(last_id));
			client.setLoc_name(category);
			client.startRunning();
		}
		else{
			throw new IllegalStateException("Serveril puudub selline meetod.");
		}
		client.clean();
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(client.getResult());
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}
	
	private List<String> toCategory(String expression){
		return Arrays.asList(expression.split(","));
	}
	
	private String toPoint(String expression){
		return expression.substring(1, expression.length()-1);
	}

}