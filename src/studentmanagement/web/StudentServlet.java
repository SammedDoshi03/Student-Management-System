package studentmanagement.web;

import java.util.*;
import java.sql.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import studentmanagement.bean.*;
import studentmanagement.dao.*;
/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	StudentDao studentDao;
       
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		studentDao = new StudentDao();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertStudent(request, response);
				break;
			case "/delete":
				deleteStudent(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateStudent(request, response);
				break;
			case "/newproc":
				showNewProcForm(request, response);
				break;
			case "/proc":
				insertStudentviaProcedure(request, response);
				break;
			default:
				listStudent(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
	}

	private void listStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Student> listStudent = studentDao.selectAllstudent();
		request.setAttribute("listStudent", listStudent);
		request.setAttribute("count", studentDao.showCount());
		RequestDispatcher dispatcher = request.getRequestDispatcher("Student-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewProcForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("proc.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int roll_No = Integer.parseInt(request.getParameter("roll_No"));
		Student existingStudent = studentDao.selectStudent(roll_No);
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
		request.setAttribute("Student", existingStudent);
		dispatcher.forward(request, response);
	}

	private void insertStudent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String email_Id = request.getParameter("email_Id");
		Long mobile_No = Long.parseLong(request.getParameter("mobile_No"));
		String blood_Group = request.getParameter("blood_Group");
		Student newstudent = new Student(name, email_Id, mobile_No, blood_Group);
		studentDao.insertStudent(newstudent);
		response.sendRedirect("list");
	}
	
	private void insertStudentviaProcedure(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String email_Id = request.getParameter("email_Id");
		Long mobile_No = Long.parseLong(request.getParameter("mobile_No"));
		String blood_Group = request.getParameter("blood_Group");
		Student newstudent = new Student(name, email_Id, mobile_No, blood_Group);
		studentDao.insertStudentviaProc(newstudent);
		response.sendRedirect("list");
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int roll_No = Integer.parseInt(request.getParameter("roll_No"));
		String name = request.getParameter("name");
		String email_Id = request.getParameter("email_Id");
		Long mobile_No = Long.parseLong(request.getParameter("mobile_No"));
		String blood_Group = request.getParameter("blood_Group");
		Student book = new Student(roll_No,name, email_Id, mobile_No, blood_Group);
		studentDao.updateStudent(book);
		response.sendRedirect("list");
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int roll_No = Integer.parseInt(request.getParameter("roll_No"));
		studentDao.deleteStudent(roll_No);
		response.sendRedirect("list");

	}

}
