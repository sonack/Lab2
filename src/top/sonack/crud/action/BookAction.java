package top.sonack.crud.action;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import top.sonack.crud.dao.AuthorDAO;
import top.sonack.crud.dao.BookDAO;
import top.sonack.crud.model.Author;
import top.sonack.crud.model.Book;

public class BookAction extends ActionSupport
{
    private static final long serialVersionUID = 1L;
    private ArrayList<Book> books;
    private ArrayList<Author> authors;
    private BookDAO bookDAO = BookDAO.getBookDAO();
    private AuthorDAO authorDAO = AuthorDAO.getAuthorDAO();
    private String isbn;
    private String[] isbns;
    private String searchAuthorName;
    private int finalAuthorID;
    private int sameAuthorCnt;
    private int authorID;
    private String lastInputPage;
    private Book newBook;
    private Author newAuthor;

    private int year, month, day;
    private boolean modify;
    private Map<String, Object> session;

    public ArrayList<Book> getBooks()
    {
	return books;
    }

    public void setBooks(ArrayList<Book> books)
    {
	this.books = books;
    }

    public String updateBook() throws SQLException
    {
	System.out.println("要修改的图书的ISBN " + isbn);
	newBook = bookDAO.getBookByISBN(isbn);
	String[] dateStrs = newBook.getPublishDate().split("-");
	
	
	setYear(Integer.parseInt(dateStrs[0]));
	setMonth(Integer.parseInt(dateStrs[1]));
	setDay(Integer.parseInt(dateStrs[2]));
	
	
	System.out.println(Arrays.toString(dateStrs));
	System.out.println(newBook);
	return SUCCESS;
    }
    
    
    public String updateAuthor() throws SQLException
    {
	System.out.println("要修改的作者的AuthorID " + authorID);
	newAuthor = authorDAO.getAuthorByAuthorID(authorID);
	System.out.println("**********************" + newAuthor);
	return SUCCESS;
    }
    
    public String commitUpdatedBook() throws SQLException
    {
	System.out.println("修改后的book是" + newBook);
	bookDAO.updateBook(newBook);
	return SUCCESS;
    }
    
    public String commitUpdatedAuthor() throws SQLException 
    {
	System.out.println("修改后的author是" + newAuthor);
	authorDAO.updateAuthor(newAuthor);
	return SUCCESS;
    }
    
    public String deleteBook() throws SQLException
    {
	bookDAO.deleteBook(isbn);
	return SUCCESS;
    }

    public String deleteBooks() throws SQLException
    {
	if (isbns != null)
	    bookDAO.deleteBooks(isbns);
	return SUCCESS;
    }

    public String list() throws SQLException
    {
	System.out.println("list() called!");
	books = bookDAO.getAllBooks();
	return SUCCESS;
    }

    public String listAuthors() throws SQLException
    {
	System.out.println("listAuthors() called!");
	authors = authorDAO.getAllAuthors();
	return SUCCESS;
    }

    public String confirmAuthor() throws SQLException
    {
	books = bookDAO.getBooksByAuthorID(finalAuthorID);
	if (books.size() == 0)
	    return "noBook";
	return SUCCESS;
    }

    public String searchBooks() throws SQLException
    {
	authors = authorDAO.getAuthorsByName(searchAuthorName);
	sameAuthorCnt = authors.size();
	if (sameAuthorCnt == 0)
	{
	    return "noBook";
	}
	else if (sameAuthorCnt > 1)
	{
	    return "morethan1";
	}
	finalAuthorID = authors.get(0).getAuthorID();
	return confirmAuthor();
    }

    public String getIsbn()
    {
	return isbn;
    }

    public void setIsbn(String isbn)
    {
	this.isbn = isbn;
    }

    public String[] getIsbns()
    {
	return isbns;
    }

    public void setIsbns(String[] isbns)
    {
	this.isbns = isbns;
    }

    public String getSearchAuthorName()
    {
	return searchAuthorName;
    }

    public void setSearchAuthorName(String searchAuthorName)
    {
	this.searchAuthorName = searchAuthorName;
    }

    public ArrayList<Author> getAuthors()
    {
	return authors;
    }

    public void setAuthors(ArrayList<Author> authors)
    {
	this.authors = authors;
    }

    public int getFinalAuthorID()
    {
	return finalAuthorID;
    }

    public void setFinalAuthorID(int finalAuthorID)
    {
	this.finalAuthorID = finalAuthorID;
    }

    public int getSameAuthorCnt()
    {
	return sameAuthorCnt;
    }

    public void setSameAuthorCnt(int sameAuthorCnt)
    {
	this.sameAuthorCnt = sameAuthorCnt;
    }

    public Book getNewBook()
    {
	return newBook;
    }

    public void setNewBook(Book newBook)
    {
	this.newBook = newBook;
    }

    public Author getNewAuthor()
    {
	return newAuthor;
    }

    public void setNewAuthor(Author newAuthor)
    {
	this.newAuthor = newAuthor;
    }

    public String addBook() throws SQLException
    {
	System.out.println("addBook() called!");
	try
	{
	    if (newAuthor != null)
	    {

		session = ActionContext.getContext().getApplication();
		setNewBook((Book) session.get("newBook"));
		newAuthor.setAuthorID(getAuthorID());
		System.out.println("要加入的AUTHOR" + newAuthor);
		authorDAO.addAuthor(newAuthor);
	    }
	    if (newBook != null)
		bookDAO.addBook(newBook);
	}
	catch (MySQLIntegrityConstraintViolationException e)
	{
	    System.out.println("我要的错误" + e);
	    session = ActionContext.getContext().getApplication();
	    session.put("newBook", newBook);
	    return "noAuthor";
	}
	return SUCCESS;
    }

    public static boolean isValidDate(String str)
    {
	boolean convertSuccess = true;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	try
	{
	    format.setLenient(false);
	    format.parse(str);
	}
	catch (ParseException e)
	{
	    convertSuccess = false;
	}
	return convertSuccess;
    }

    @Override
    public void validate()
    {
	System.out.println("newAuthor is " + newAuthor);
	System.out.println("newBook is " + newBook);
	
	
	System.out.println("当前" + (modify?"是":"不是") + "修改中....");
	if (newAuthor != null)
	{
	    lastInputPage = "addAuthorForm";
	    if (newAuthor.getName() == null || newAuthor.getName().length() == 0)
	    {
		addFieldError("newAuthor.name", "姓名不能为空！");
	    }

	    if (newAuthor.getCountry() == null || newAuthor.getCountry().length() == 0)
	    {
		addFieldError("newAuthor.country", "国家不能为空！");
	    }

	    if (newAuthor.getAge() <= 0 || newAuthor.getAge() > 150)
	    {
		addFieldError("newAuthor.age", "请输入合法的年龄！");
	    }
	}

	if (newBook != null)
	{
	    lastInputPage = "addBookForm";
	    if (newBook.getIsbn() == null || newBook.getIsbn().length() == 0)
	    {
		addFieldError("newBook.isbn", "ISBN号不能为空！");
	    }

	    if (newBook.getTitle() == null || newBook.getTitle().length() == 0)
	    {
		addFieldError("newBook.title", "书名不能为空！");
	    }

	    if (newBook.getPublisher() == null || newBook.getPublisher().length() == 0)
	    {
		addFieldError("newBook.publisher", "出版社不能为空！");
	    }

	    try
	    {
		if (modify == false && bookDAO.isISBNExist(newBook.getIsbn()))
		{
		    addFieldError("newBook.isbn", "ISBN号已存在！");
		}

		if (newBook.getPrice() <= 0)
		{
		    addFieldError("newBook.price", "价格必须为正数！");
		}

		if (newBook.getAuthorID() <= 0)
		{
		    addFieldError("newBook.authorID", "作者编号必须为正数！");
		}

		String dateStr = getYear() + "-" + getMonth() + "-" + getDay();

		if (!isValidDate(dateStr) || getYear() > 9999 )
		{
		    addFieldError("day", "日期不合法！");
		}
		else
		{
		    newBook.setPublishDate(dateStr);
		}

		setAuthorID(newBook.getAuthorID());

	    }
	    catch (SQLException e)
	    {
		e.printStackTrace();
	    }

	}
    }

    public int getYear()
    {
	return year;
    }

    public void setYear(int year)
    {
	this.year = year;
    }

    public int getMonth()
    {
	return month;
    }

    public void setMonth(int month)
    {
	this.month = month;
    }

    public int getDay()
    {
	return day;
    }

    public void setDay(int day)
    {
	this.day = day;
    }

    public static void main(String[] args)
    {
	System.out.println(isValidDate("1900-2-29"));
    }

  

    public String getLastInputPage()
    {
	return lastInputPage;
    }

    public void setLastInputPage(String lastInputPage)
    {
	this.lastInputPage = lastInputPage;
    }

    public boolean isModify()
    {
        return modify;
    }

    public void setModify(boolean modify)
    {
        this.modify = modify;
    }

    public int getAuthorID()
    {
        return authorID;
    }

    public void setAuthorID(int authorID)
    {
        this.authorID = authorID;
    }

    
}
