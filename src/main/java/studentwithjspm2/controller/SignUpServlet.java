package studentwithjspm2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentwithjspm2.dao.StudentDao;
import studentwithjspm2.dto.Student;

public class SignUpServlet extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	String name=req.getParameter("name");
	String address=req.getParameter("address");
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	long phone=Long.parseLong(req.getParameter("phone"));
	ServletContext context=getServletContext();
	double fees=Double.parseDouble(context.getInitParameter("fees"));
	
	Student student=new Student();
	student.setAddress(address);
	student.setEmail(email);
	student.setFees(fees);
	student.setName(name);
	student.setPassword(password);
	student.setPhone(phone);
	
	StudentDao studentDao=new StudentDao();
	List<Student> students=studentDao.getAllStudents();
	
	boolean value=false;
	
	for(Student st:students) {
		if(email.equals(st.getEmail())) {
			value=true;
			break;
		}	
	}
	
	if(value) {
//		email is already present in the database
		
		req.setAttribute("message", "Email Already Exist ");
		RequestDispatcher dispatcher=req.getRequestDispatcher("signup.jsp");
		dispatcher.include(req, resp);
	
		
	}else {
//		email is not present in the database
//		add this student
		studentDao.signUpStudent(student);
		
		req.setAttribute("message", "Signup Successfully Please Login");
		RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
		dispatcher.forward(req, resp);
	}
	
	
	
	
	
	
}
}
