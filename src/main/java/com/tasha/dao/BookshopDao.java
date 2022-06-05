package com.tasha.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import com.tasha.dto.BookDto;
import com.tasha.dto.CustomerDto;

/**
 * @author Shahrukh
 * @since May 2022
 */
public class BookshopDao {
	
	private Connection connection;
	private PreparedStatement pstCustValidate, pstBookCategory, pstBookDetail, pstCartDetail;

	/**
	 * @throws Exception
	 */
	public BookshopDao() throws Exception {
		connection = DatabaseUtils.getConnection();
		//customer validation
		pstCustValidate = connection.prepareStatement("select * from customers where email=? and password=?");
		//book categories
		pstBookCategory = connection.prepareStatement("select distinct category from books");
		//book details
		pstBookDetail = connection.prepareStatement("select bookid, title from books where category=?");
		//get cart details
		pstCartDetail = connection.prepareStatement("select title, author, category, price from books where bookid=?");
	}
	
	public void cleanUp() throws Exception {
		System.out.println("Clean up menthod initiated");
		if(pstCustValidate != null)
			pstCustValidate.close();
		if(pstBookCategory != null)
			pstBookCategory.close();
		if(pstBookDetail != null)
			pstBookDetail.close();
		if(pstCartDetail != null)
			pstCartDetail.close();
		if(connection != null)
			connection.close();
	}
	
	//CRUD operations
	//Cust validation
	public CustomerDto validateCustomer(String email, String password) throws Exception{
		
		CustomerDto customer = null;
		pstCustValidate.setString(1, email);
		pstCustValidate.setString(2, password);
		try(ResultSet rst = pstCustValidate.executeQuery();){
			if(rst.next()) {
				customer = new CustomerDto(rst.getInt(1), rst.getString(2), email, password, 
							rst.getDouble("deposit_amt"), rst.getDate("reg_date"), rst.getString("role"));
			}
		}
		System.out.println("valid customer: " + customer);
		return customer;
	}
	
	//get categories
	public ArrayList<String> getCategories() throws Exception {
		
		ArrayList<String> categoryList = new ArrayList<String>();
		try(ResultSet rst = pstBookCategory.executeQuery();){
			while(rst.next()) {
				categoryList.add(rst.getString(1));
			}
		}
		System.out.println("categories: " + categoryList);
		return categoryList;
	}
	
	//get book by category
	public LinkedHashMap<Integer, String> getBooksByCategory(String category) throws SQLException{
		
		LinkedHashMap<Integer, String> books = new LinkedHashMap<Integer, String>();
		pstBookDetail.setString(1, category);
		try(ResultSet rst = pstBookDetail.executeQuery();){
			while(rst.next() == true) {
				books.put(rst.getInt(1), rst.getString(2));
			}
		}
		System.out.println("books: " + books);
		return books;
	}
	
	//get cart details
	public ArrayList<BookDto> getCartDetails(ArrayList<Integer> ids) throws Exception {
		ArrayList<BookDto> bookList = new ArrayList<BookDto>();
		for(int id : ids) {
			pstCartDetail.setInt(1, id);
			try(ResultSet rst = pstCartDetail.executeQuery()){
				if(rst.next()) {
					bookList.add(new BookDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4)));
				}
			}
		}
		System.out.println("cart values: " + bookList);
		return bookList;
	}
} 
