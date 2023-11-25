package com.doctor.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DoctorDao;
import com.db.DBConnect;
import com.entity.Doctor;

@WebServlet("/doctorLogin")
public class DoctorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		HttpSession session = req.getSession();
		DoctorDao dao = new DoctorDao(DBConnect.getConnection());
		Doctor doctor = dao.login(email, password);

		if (doctor != null) {
			session.setAttribute("doctObj", doctor);
			resp.sendRedirect("doctor/index.jsp");

		} else {
			session.setAttribute("errorMsg", "Invalid email & password");
			resp.sendRedirect("doctor_login.jsp");
		}
	}
	

}
