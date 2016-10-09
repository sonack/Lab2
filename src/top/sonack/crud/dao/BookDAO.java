package top.sonack.crud.dao;

import top.sonack.crud.model.Book;
import top.sonack.crud.dao.utils.JdbcUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import com.opensymphony.xwork2.Result;

public class BookDAO
{
    private static BookDAO bookDAO = null;
    private static final String BOOK_TABLE_NAME = "Book";
    private Statement stmt;
    
    private BookDAO()
    {
	
    }
    
    public static BookDAO getBookDAO()
    {
	if(bookDAO == null)
	{
	    bookDAO = new BookDAO();
	}
	return bookDAO;
    }
    
    
    private static Book ORM(ResultSet res) throws SQLException
    {
	return new Book(res.getString("isbn"),res.getString("title"),res.getInt("authorID"),res.getString("publisher"),res.getString("publishDate"),res.getDouble("price"));
    }
    
    public ArrayList<Book> getBooksByAuthorID(int authorID) throws SQLException
    {
	String sql = "SELECT * FROM " + BOOK_TABLE_NAME + " WHERE authorID = " + authorID ;
	System.out.println("查询书籍SQL:" + sql);
	stmt = JdbcUtils.getStatement();
	ResultSet res = stmt.executeQuery(sql);
	ArrayList<Book> books = new ArrayList<Book>();    
	while(res.next())
	{
	    Book book = ORM(res);
	    books.add(book);
	}
	return books;
    }
    
    
    
    
    public ArrayList<Book> getAllBooks() throws SQLException
    {
	String sql = "SELECT * FROM " + BOOK_TABLE_NAME;
	stmt = JdbcUtils.getStatement();
	ResultSet res = stmt.executeQuery(sql);
	ArrayList<Book> books = new ArrayList<Book>();    
	while(res.next())
	{
	    Book book = ORM(res);
	    books.add(book);
	}
	return books;
    }
    
    public Book getBookByISBN(String isbn) throws SQLException
    {
	String sql = "SELECT * FROM " + BOOK_TABLE_NAME + " WHERE isbn = '" + isbn + "'";
	stmt = JdbcUtils.getStatement();
	ResultSet res = stmt.executeQuery(sql);
	if(res.next())
	    return ORM(res);
	return null;
    }
    
    public void deleteBook(String isbn) throws SQLException
    {
	String sql = "DELETE FROM " + BOOK_TABLE_NAME + " WHERE isbn = '" + isbn + "'";
	System.out.println("删除" + sql);
	stmt = JdbcUtils.getStatement();
	stmt.executeUpdate(sql);
    }
    
    public void deleteBooks(String[] isbns) throws SQLException
    {
	for(String isbn : isbns)
	{
	    deleteBook(isbn);
	}
    }
    
    public boolean isISBNExist(String isbn) throws SQLException
    {
	String sql = "SELECT COUNT(*) FROM " + BOOK_TABLE_NAME + " WHERE isbn = '" + isbn + "'";
	System.out.println("判断是否存在" + sql);
	stmt = JdbcUtils.getStatement();
	ResultSet res = stmt.executeQuery(sql);
	int cnt = 0;
	if(res.next()){
	   cnt = res.getInt(1);
	}
	if(cnt > 0) return true;
	return false;
    }
    
    public void addBook(Book book) throws SQLException
    {
	String sql = "INSERT INTO " + BOOK_TABLE_NAME 
		+ " values('" + book.getIsbn() +"','" + book.getTitle() 
		+ "'," + book.getAuthorID() + ",'" + book.getPublisher() 
		+ "','" + book.getPublishDate() + "'," + book.getPrice() + ")";
	stmt = JdbcUtils.getStatement();
	stmt.executeUpdate(sql);
    }
    
    public void updateBook(Book newBook) throws SQLException 
    {
	String sql = "UPDATE " + BOOK_TABLE_NAME  + " SET "
		+ "title = '" + newBook.getTitle() + "',"
		+ "publisher = '" + newBook.getPublisher() + "',"
		+ "publishDate = '" + newBook.getPublishDate() + "',"
		+ "price = " + newBook.getPrice() + " WHERE isbn = '" + newBook.getIsbn() + "'";
	System.out.println("更新语句" + sql);
	stmt = JdbcUtils.getStatement();
	stmt.executeUpdate(sql);
    }
}
