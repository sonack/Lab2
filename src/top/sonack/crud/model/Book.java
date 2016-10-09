package top.sonack.crud.model;

public class Book
{
    private String isbn;
    private String title;
    private int authorID;
    private String publisher;
    private String publishDate;
    private double price;
    
    public Book()
    {
	
    }
    
    /**
     * @param iSBN
     * @param title
     * @param authorID
     * @param publisher
     * @param publishDate
     * @param price
     */
    public Book(String isbn, String title, int authorID, String publisher, String publishDate, double price)
    {
	this.isbn = isbn;
	this.title = title;
	this.authorID = authorID;
	this.publisher = publisher;
	this.publishDate = publishDate;
	this.price = price;
    }

    public String getIsbn()
    {
	return isbn;
    }

    public void setIsbn(String isbn)
    {
	this.isbn = isbn;
    }

    public String getTitle()
    {
	return title;
    }

    public void setTitle(String title)
    {
	this.title = title;
    }

    public int getAuthorID()
    {
	return authorID;
    }

    public void setAuthorID(int authorID)
    {
	this.authorID = authorID;
    }

    public String getPublisher()
    {
	return publisher;
    }

    public void setPublisher(String publisher)
    {
	this.publisher = publisher;
    }

    public String getPublishDate()
    {
	return publishDate;
    }

    public void setPublishDate(String publishDate)
    {
	this.publishDate = publishDate;
    }

    public double getPrice()
    {
	return price;
    }

    public void setPrice(double price)
    {
	this.price = price;
    }

    @Override
    public String toString()
    {
	return "Book [isbn=" + isbn + ", title=" + title + ", authorID=" + authorID + ", publisher=" + publisher
		+ ", publishDate=" + publishDate + ", price=" + price + "]";
    }
    
}
