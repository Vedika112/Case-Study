----a) com.ac.bean----

package com.ac.bean;

public class Book {
	private int bookId,bookPrice;
	private String bookTitle,bookGrade;
	
	//getters and setters
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookGrade() {
		return bookGrade;
	}
	public void setBookGrade(String bookGrade) {
		this.bookGrade = bookGrade;
	}
}

--------b) com.ac.service---------
package com.ac.service;

import com.ac.bean.Book;
import com.ac.dao.BookDao;

public class BookService {
	
	public int bookService(int bookId,String bookTitle,int bookPrice) {
		String bookGrade="";
		if(bookPrice<=200)
			bookGrade="C";
		else if (bookPrice<=500)
			bookGrade="B";
		else
			bookGrade="A";
	
	//creating object of BookDao class 
	BookDao bdao=new BookDao();
	
	//creating object of Bean
	Book book=new Book();
	
	//Wrapping up all the four field values into bean 
	book.setBookId(bookId);
	book.setBookTitle(bookTitle);
	book.setBookPrice(bookPrice);
	book.setBookGrade(bookGrade);
	
	int updateResult=0;
	try {
		updateResult=bdao.addBook(book);
		return updateResult;
	}
	catch(Exception e) {
		System.out.println(e.toString());
		return 0;
	}
	
	}
}

---------c)com.ac.Dao------
1)
  package com.ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ac.bean.Book;

public class BookDao {
	public int addBook(Book book) {
		try {
			Connection con = null;
			PreparedStatement pstmt = null;
			con = BookDb.getConnection();

			// inserting values into book table
			pstmt = con.prepareStatement("insert into book values(?,?,?,?)");
			pstmt.setInt(1, book.getBookId());
			pstmt.setString(2, book.getBookTitle());
			pstmt.setInt(3, book.getBookPrice());
			pstmt.setString(4, book.getBookGrade());
			int updateCount = pstmt.executeUpdate();

			con.close();
			return updateCount;
		} catch (Exception e) {
			System.out.println(e.toString());
			return 0;
		}
	}

	// to get book details by id
	public ArrayList getBookDetailsById(int bookId) throws SQLException, ClassNotFoundException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		con = BookDb.getConnection();
		String selectStatement = "select booktitle,bookprice,bookgrade from book where bookid=?";

		pstmt = con.prepareStatement(selectStatement);

		pstmt.setInt(1, bookId);
		result = pstmt.executeQuery();

		ArrayList array = new ArrayList();

		//to insert book details in array
		if (result.next()) {
			array.add(result.getString(1)); 
			array.add(result.getString(2));
			array.add(result.getString(3));
		} else
			array.add("Invalid id");

		return array;
	}

}

2)
  package com.ac.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BookDb {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
    //loading driver class
		Class.forName("org.postgresql.Driver");
    
		//creating connection
		Connection con=DriverManager.getConnection("jdbc:postgresql://localhost/student","postgres","password");
	    return con;
	}
}


------d)com.ac.Ui------

 package com.ac.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.ac.dao.BookDao;
import com.ac.service.BookService;

public class BookClient {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		int bookId = 0;
		String bookTitle = "";
		int bookPrice = 0;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Book Id");
			bookId = sc.nextInt();
		System.out.println("Enter Book Title");
			bookTitle = sc.next();
		System.out.println("Enter Book Price");
			bookPrice = sc.nextInt();
			
		BookService bservice=new BookService();
		int update=bservice.bookService(bookId, bookTitle, bookPrice);
		System.out.println(update+" one row updated");
		
		
		System.out.println("Enter Book Id to know book details");
		int bid = sc.nextInt();
		
		BookDao bd=new BookDao();
	    ArrayList array = new ArrayList();
		
		array=bd.getBookDetailsById(bid); 
		System.out.println("BookTitle:"+array.get(0)+" BookPrice:"+array.get(1)+" Book Grade:"+array.get(2));

	}

}


---------Output-------
Enter Book Id
105
Enter Book Title
Physics
Enter Book Price
1100
1 one row updated

Enter Book Id to know book details
104
BookTitle:Maths BookPrice:200 Book Grade:C
