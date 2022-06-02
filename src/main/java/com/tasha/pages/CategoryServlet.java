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

/**
 * @author Shahrukh
 * @since June 2022
 */
@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter();){
			HttpSession session = request.getSession();
			BookshopDao bookshopDao = (BookshopDao) session.getAttribute("bookshopDao");
			ArrayList<String> categories = bookshopDao.getCategories();
			
			//dynamic form
			pw.print("<html><body>");
			pw.print("<form action='details'>");
			pw.print("Choose categories: <select name = 'book_category'>");
			for(String category : categories)
				pw.print("<option value = '" + category + "'>" + category + "</option>");
			pw.print("<select> </br>");
			pw.print("<input type='submit' value='choose'>");
			pw.print("</form>");
			pw.print("<a href='checkout'>Check out</a>");
			pw.print("</body></html>");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
