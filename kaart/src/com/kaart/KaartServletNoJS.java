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

public class KaartServletNoJS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Client client;

	public KaartServletNoJS() {
		// TODO Auto-generated constructor stub
		client = new Client();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String category = null;
		category = request.getParameter("category").trim().toLowerCase();
		client.setLoc_name(category);
		client.startRunning();

		String result = client.getResult();
		String[] array = result.split("\\|");


		String table = "<table><tr><th>Name</th><th>Description</th><th>Link</th><th>Location</th></tr>";

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
				Map<String, String> son = new Gson().fromJson(array2[0], mapType);
				table += "<tr><td>" + son.get("name") + "</td><td>"
						+ son.get("description")  + "</td><td>" + son.get("link")
						+ "</td><td>" + son.get("location") + "</td></tr>";


			} catch (NumberFormatException e) {
				
			}
			
		}
		table += "</table>";

		client.clean();
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		// request.setAttribute("id", arg1);

		request.setAttribute("id", table);

		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}


}