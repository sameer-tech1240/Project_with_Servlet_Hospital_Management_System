package com.admin.servlet;

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

@WebServlet("/updateDoctor")
public class UpdateDoctor extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String fullname = req.getParameter("fullname");
			String dob = req.getParameter("dob");
			String qualification = req.getParameter("qualification");
			String specialist_name = req.getParameter("specialist_name");
			String email = req.getParameter("email");
			String mobno = req.getParameter("mobno");
			String password = req.getParameter("password");
			int id = Integer.parseInt(req.getParameter("id"));
			

			Doctor d = new Doctor(id , fullname, dob, qualification, specialist_name, email, mobno, password);

			DoctorDao dao = new DoctorDao(DBConnect.getConnection());

			HttpSession session = req.getSession();

			if (dao.updateDoctor(d))

			{
				session.setAttribute("succMsg", "Doctor Update Sucessfully..");
				resp.sendRedirect("admin/view_doctor.jsp");

			} else {
				session.setAttribute("errorMsg", "somthing wrong on server");
				resp.sendRedirect("admin/view_doctor.jsp");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

}
