package com.tasha.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tasha.dto.CustomerDto;

/**
 * @author Shahrukh
 *
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
	//Cust validationd
	public CustomerDto validateCustomer(String email, String password) throws Exception{
		
		CustomerDto customer = null;
		pstCustValidate.setString(1, email);
		pstCustValidate.setString(2, password);
		try(ResultSet rst = pstCustValidate.executeQuery();){
			if(rst.next()) {
				customer = new CustomerDto(rst.getInt(1), rst.getString(2), email, password, 
							rst.getDouble("depsit_amt"), rst.getDate("reg_date"), rst.getString("role"));
			}
		}
		System.out.println("valid customer: " + customer);
		return customer;
	}
	
}
