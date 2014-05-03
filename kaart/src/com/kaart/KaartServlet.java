package com.kaart;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.client.Client;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
		if (method.equalsIgnoreCase("findbycategory")) {
			// küsib vastavalt kategooriale
			String category = request.getParameter("category").trim()
					.toLowerCase();
			client.setLoc_name(category);
			client.startRunning();
		} else if (method.equalsIgnoreCase("getpointdescription")) {
			String id = request.getParameter("id").trim().toLowerCase();
			client.setId(Long.parseLong(id));
			client.startRunning();
		} else if (method.equalsIgnoreCase("insertnewpoint")) {
			String name = request.getParameter("name").trim();
			String desc = request.getParameter("desc").trim();
			String link = request.getParameter("link").trim();
			String location = request.getParameter("location");
			String cat = request.getParameter("categories").trim();
			// System.out.println("name " + name
			// +" location "+toPoint(location)+ " desc " + desc + " link " +
			// link + " cat " + toCategory(cat));
			if(link.equalsIgnoreCase("")){
				link = "puudub";
			}
			client.setLoc_name(name);
			client.setLoc_location(toPoint(location));
			client.setLoc_description(desc);
			client.setLoc_link(link);
			client.setCategoryTags(toCategory(cat));
			client.setInsert(true);
			client.startRunning();
		} else if (method.equalsIgnoreCase("findupdates")) {
			String category = request.getParameter("category").trim();
			String last_id = request.getParameter("lastid").trim();
			client.setId(Long.parseLong(last_id));
			client.setLoc_name(category);
			client.startRunning();
		} else if (method.equalsIgnoreCase("getCategoryCount")) {
			// leiab kategooria count-i
			String category = request.getParameter("category").trim()
					.toLowerCase();
			client.setLoc_name(category);
			client.setId(-11);
			client.startRunning();
		}else if(method.equalsIgnoreCase("getPointComments")){
			String pointID = request.getParameter("pointID").trim();
			client.setLoc_name(pointID);
			client.setgetComments(true);
			client.startRunning();
		}else if (method.equalsIgnoreCase("infoNOJS")) {
			String category = null;
			category = request.getParameter("category").trim().toLowerCase();
			client.setLoc_name(category);
			client.startRunning();
			String result = client.getResult();
			String[] array = result.split("\\|");
			String table = "";

			for (int i = 0; i < array.length; i++) {
				client.clean();
				String[] info = array[i].split("[\\W]");
				String id = info[info.length - 1];
				try {
					client.setId(Long.parseLong(id));
					client.startRunning();
					result = client.getResult();
					String[] array2 = result.split("\\|");
					Type mapType = new TypeToken<Map<String, String>>() {
					}.getType();
					Map<String, String> son = new Gson().fromJson(array2[0],
							mapType);
					table += "<tr><td>" + son.get("name") + "</td><td>"
							+ son.get("description") + "</td><td>"
							+ son.get("link") + "</td><td>"
							+ son.get("location") + "</td></tr>";

				} catch (NumberFormatException e) {
				}
			}
			client.clean();
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			request.setAttribute("id", table);

			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
			return;
		} else {
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
		String method = request.getParameter("method");
		if (method.equalsIgnoreCase("insertnewpoint")) {
			String name = request.getParameter("name").trim();
			String desc = request.getParameter("desc").trim();
			String link = request.getParameter("link").trim();
			String location = request.getParameter("location");
			String cat = request.getParameter("categories").trim();
			// System.out.println("name " + name
			// +" location "+toPoint(location)+ " desc " + desc + " link " +
			// link + " cat " + toCategory(cat));
			if(link.equalsIgnoreCase("")){
				link = "puudub";
			}
			client.setLoc_name(name);
			client.setLoc_location(toPoint(location));
			client.setLoc_description(desc);
			client.setLoc_link(link);
			client.setCategoryTags(toCategory(cat));
			client.setInsert(true);
			client.startRunning();
		}else if(method.equalsIgnoreCase("adduserrating")){
			String rating = request.getParameter("rating").trim();
			String pointID = request.getParameter("pointID").trim();
			String userID = request.getParameter("userID").trim();
			String userName = request.getParameter("userName").trim();
			System.out.println("User " + userID + " rated point " + pointID + " with " + rating);
			client.setRating(true);
			client.setLoc_name(pointID);
			client.setLoc_description(userID);
			client.setLoc_link(rating);
			client.setLoc_location(userName);
			client.startRunning();
		}else if(method.equalsIgnoreCase("addusercomment")){
			String comment = request.getParameter("comment").trim();
			String pointID = request.getParameter("pointID").trim();
			String userID = request.getParameter("userID").trim();
			String userName = request.getParameter("userName").trim();
			System.out.println("User " + userID + " rated point " + pointID + " with " + comment);
			client.setComment(true);
			client.setLoc_name(pointID);
			client.setLoc_description(userID);
			client.setLoc_link(comment);
			client.setLoc_location(userName);
			client.startRunning();
		}
		else {
			throw new IllegalStateException("Serveril puudub selline meetod.");
		}
		client.clean();
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(client.getResult());
	}

	private List<String> toCategory(String expression) {
		return Arrays.asList(expression.split(","));
	}

	private String toPoint(String expression) {
		return expression.substring(1, expression.length() - 1);
	}

}