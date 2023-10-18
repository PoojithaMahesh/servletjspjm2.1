package studentwithjspm2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import studentwithjspm2.dao.StudentDao;
import studentwithjspm2.dto.Student;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		StudentDao studentDao = new StudentDao();
		List<Student> students = studentDao.getAllStudents();
		boolean value = false;
		String studentPassword = null;
		String studentName=null;
		for (Student student : students) {
			if (email.equals(student.getEmail())) {
				value = true;
				studentPassword = student.getPassword();
//				Cookiesss Concepttt
//				ill will take a student name also for cookies
				studentName=student.getName();
				break;
			}
		}

		if (value) {
//		email is present 
//		check with the password
			if (studentPassword.equals(password)) {
//			login success
//				Create One Cookies
				Cookie cookie=new Cookie("studentWhoLoggedin", studentName);
				resp.addCookie(cookie);
				
//				HttpSession
				HttpSession httpSession=req.getSession();
				httpSession.setAttribute("studentNameWhoLoggedIn", studentName);
				
				
				
				req.setAttribute("list",students);
				RequestDispatcher dispatcher = req.getRequestDispatcher("display.jsp");
				dispatcher.forward(req, resp);
			} else {
//			invalid password
				req.setAttribute("message", "Invalid Password");
				RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
				dispatcher.include(req, resp);
			}

		} else {
//		email is not present
			req.setAttribute("message", "Invalid email");
			RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
			dispatcher.include(req, resp);
		}

	}
}
