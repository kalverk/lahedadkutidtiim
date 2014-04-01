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

	public KaartServletNoJS() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Client client = null;
		String category = null;
		category = request.getParameter("category").trim().toLowerCase();
		client = new Client(category);
		client.startRunning();

		String result = client.getResult();
		System.out.println(result);
		String[] array = result.split("\\|");
		System.out.println("array info: " + array[0] + " " + array[1] + " "
				+ array.length);

		String table = "<table><tr><th>ID</th><th>Description</th><th>Category</th><th>Link</th><th>Location</th></tr>";

		for (int i = 0; i < array.length; i++) {
			String[] info = array[i].split("[\\W]");
			String id = info[info.length - 1];
			client = new Client(Long.parseLong(id));
			client.startRunning();
			result = client.getResult();
			array = result.split("\\|");
			Type mapType = new TypeToken<Map<String, String>>() {
			}.getType();
			Map<String, String> son = new Gson().fromJson(array[0], mapType);
			table += "<tr><td>" + son.get("id") + "</td><td>"
					+ son.get("description") + "</td><td>"
					+ son.get("categoryTags") + "</td><td>" + son.get("link")
					+ "</td><td>" + son.get("location") + "</td></tr>";

		}
		table += "</table>";

		System.out.println(table);

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