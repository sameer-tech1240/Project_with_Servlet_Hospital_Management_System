package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.SpecialistDao;
import com.db.DBConnect;
@WebServlet("/addSpecialist")
public class AddSpecialist extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String specialist_name = req.getParameter("specialist_name");
		SpecialistDao dao = new SpecialistDao(DBConnect.getConnection());
		boolean f = dao.addSpecialist(specialist_name);
		
		HttpSession session = req.getSession();
		if (f) {
			session.setAttribute("succMsg", "Specialist Added");
			resp.sendRedirect("admin/index.jsp");
		} else {
			session.setAttribute("errorMsg", "somthing wrong on server");
			resp.sendRedirect("admin/index.jsp");

		}
	}
	

}
