package com.kaart;

import java.io.IOException;

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

	public KaartServlet() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String category = null;
		category = request.getParameter("category").trim().toLowerCase();
		Client client = new Client(category);
		client.startRunning();
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(client.getResult());
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}