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
import com.tasha.dto.BookDto;
import com.tasha.dto.CustomerDto;

/**
 * @author Shahrukh
 * @since June 03, 2022
 */
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter();){
			//get session, dao, user details, cart
			HttpSession session = request.getSession();
			CustomerDto customer = (CustomerDto) session.getAttribute("validCustomer");
			BookshopDao bookshopDao = (BookshopDao) session.getAttribute("bookshopDao");
			ArrayList<Integer> cart = (ArrayList<Integer>) session.getAttribute("cart");
			
			//get cart details
			ArrayList<BookDto> bookDetailsList = bookshopDao.getCartDetails(cart);
			
			//dynamic form
			pw.print("<html><body>");
			pw.print("<b>Hello " + customer.getCustName() + "</br></b>");
			pw.print("<h3 align='center'>Cart content:</h3>");
			double total = 0;
			for(BookDto book : bookDetailsList) {
				pw.print(book.getBookTitle() + " : " + book.getBookAuthor() + " : " + book.getBookCategory() + " : " + book.getBookPrice()
							+ "</br>");
				total += book.getBookPrice();
			}
			pw.print("<h3 align='center'>Cart Value: " + total + "</h3>");
			
			//close db and invalidate session
			cleanUp(session, bookshopDao);
			
			//redirect user to login page
			pw.print("<h3>Thank you for visiting, you will be automatically redirected to login page..</h3>");
			String path = request.getContextPath();
			System.out.println("Context path: " + path);
			response.setHeader("refresh", "10;url=" + path);
			pw.print("</body></html>");
			
		} catch (Exception e) {
			throw new ServletException("Error in doGet() of " + getClass().getName(), e);
		}
		
	}
	
	private boolean cleanUp(HttpSession session, BookshopDao bookshopDao) throws Exception {
		bookshopDao.cleanUp();
		session.invalidate();
		return true;
	}
	
}
