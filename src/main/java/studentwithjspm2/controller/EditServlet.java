package studentwithjspm2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentwithjspm2.dao.StudentDao;
import studentwithjspm2.dto.Student;
@WebServlet("/edit")
public class EditServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int id=Integer.parseInt(req.getParameter("id"));
	String name=req.getParameter("name");
	String address=req.getParameter("address");
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	long phone=Long.parseLong(req.getParameter("phone"));
	double fees=Double.parseDouble(getServletContext().getInitParameter("fees"));
	
	Student student=new Student();
	student.setAddress(address);
	student.setEmail(email);
	student.setFees(fees);
	student.setId(id);
	student.setName(name);
	student.setPassword(password);
	student.setPhone(phone);
	
	StudentDao dao=new StudentDao();
	Student dbStudent=dao.updateStudent(student);
	
	req.setAttribute("list", dao.getAllStudents());
	RequestDispatcher dispatcher=req.getRequestDispatcher("display.jsp");
	dispatcher.forward(req, resp);
	
	
}
}
