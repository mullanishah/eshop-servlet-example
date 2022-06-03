package com.tasha.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tasha.dao.BookshopDao;

/**
 * @author Shahrukh
 * @since June 03, 2022
 */
@WebServlet("/details")
public class CategoryDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter();){
			//HttpSession, get Dao, get category from request scope
			HttpSession session = request.getSession();
			BookshopDao bookshopDao = (BookshopDao) session.getAttribute("bookshopDao");
			String selectedCategory = request.getParameter("book_category");
			ArrayList<Integer> cart = (ArrayList<Integer>) session.getAttribute("cart");
			
			//get books by category
			LinkedHashMap<Integer, String> bookMap = bookshopDao.getBooksByCategory(selectedCategory);
			
			//dynamic form
			pw.print("<html><body>");
			pw.print("<h3 align='center'>Books under " + selectedCategory+ ": </h3>");
			pw.print("<form action='add_cart' menthod='get'> ");
			Set<Entry<Integer, String>> entries = bookMap.entrySet();
			for(Entry<Integer, String> entry : entries) {
				pw.print("<input type='checkbox' name='bookTitle' value='" + entry.getKey() + "'> " + entry.getValue() + "<br/>");
			}
			pw.print("<input type='submit' value='Add to Cart' >");
			pw.print("</form>");
			
			pw.print("<h5>Cart Items: " + cart + " </h5>");
			pw.print("</body></html>"); 
			
		} catch (Exception e) {
			throw new ServletException("Error in doGet() of " + getClass().getName(), e);
		}
	}
}
