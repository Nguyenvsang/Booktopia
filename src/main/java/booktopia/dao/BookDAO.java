package booktopia.dao;

import java.util.List;

import booktopia.entity.Book;

public interface BookDAO {

	// Phương thức để lấy danh sách tất cả các quyển sách
    List<Book> getAllBooks();

    // Phương thức để thêm một quyển sách mới
    void addBook(Book book);

    // Phương thức để ngừng kinh doanh một quyển sách theo id
    void disableBook(int id);
	
    // Phương thức để lấy những cuốn sách theo danh mục 
    List<Book> getBooksByCategory(int categoryId);
    
    // Phương thức để lấy danh sách tất cả các quyển sách còn kinh doanh
    List<Book> getActiveBooks();
    
    // Phương thức để lấy danh sách tất cả các quyển sách còn kinh doanh theo danh mục
    List<Book> getActiveBooksByCategory(int categoryId);

    // Phương thức tìm kiếm sách dựa trên từ khóa
	List<Book> searchBooksByKeyword(List<Book> books, String searchKeyword);

	// Phương thức lấy sách theo Id
	Book getBookById(int id);
	
	// Phương thức lấy sách theo CartItemId
    Book getBookByCartItemId(int cartItemId);

    // Phương thức cập nhật một cuốn sách
	void updateBook(Book book);
	
}
