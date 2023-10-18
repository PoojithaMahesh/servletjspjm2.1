package studentwithjspm2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import studentwithjspm2.dao.StudentDao;
import studentwithjspm2.dto.Student;
@WebServlet("/update")
public class UpdateServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		StudentDao dao=new StudentDao();
		Student student=dao.findStudentById(id);
		HttpSession httpSession=req.getSession();
		String name=(String)httpSession.getAttribute("studentNameWhoLoggedIn");
		if(name!=null) {
             req.setAttribute("student", student);
             RequestDispatcher dispatcher=req.getRequestDispatcher("edit.jsp");
             dispatcher.forward(req, resp);	
		}else {
//			he is not coming form login page he is directly using my application with scam url
			req.setAttribute("message", "heyscammer pls login first then come here idiot fellow");
			 RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
             dispatcher.forward(req, resp);
			
		}
		
		
		
	}
}
