package com.tasha.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tasha.dao.BookshopDao;
import com.tasha.dto.CustomerDto;

/**
 * @author Shahrukh
 * @since June 2022
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter();) {
			
			//read email, password from the request object 
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			//dao initialization
			BookshopDao bookshopDao = new BookshopDao();
			
			//validate
			CustomerDto customer = bookshopDao.validateCustomer(email, password);
			if(customer != null) {
				//httpSession
				HttpSession session = request.getSession();
				//empty cart, add cart, dao, cust-details to session
				ArrayList<Integer> cart = new ArrayList<Integer>();
				session.setAttribute("cart", cart);
				session.setAttribute("bookshopDao", bookshopDao);
				session.setAttribute("validCustomer", customer);
				//redirect
				response.sendRedirect("category");
			} else {
				pw.print("Invalid login <br/>");
				pw.print("<a href='login.html'>Retry Login</a>");
			}
		}catch (Exception e) {
			if(e instanceof ServletException)
				throw new ServletException("error in doPost() of " + getClass().getName(), e);
			e.printStackTrace();
		}
		
	}
}
