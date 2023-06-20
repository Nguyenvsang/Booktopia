package booktopia.dao;

import java.util.List;
import java.util.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;

import booktopia.utility.JDBCDataSource;
import booktopia.entity.Book;
import booktopia.entity.Category;

public class BookDAOImpl implements BookDAO {

	@Override
	public List<Book> getAllBooks() {
	    List<Book> books = new ArrayList<>();
	    Connection conn = null;
	    try {
	        conn = JDBCDataSource.getConnection();
	        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM book");
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	        	Book book = new Book();
	            book.setId(rs.getInt("id"));
	            book.setName(rs.getString("name"));
	            book.setAuthor(rs.getString("author"));
	            book.setPrice(rs.getDouble("price"));
	            book.setCategoryId(rs.getInt("category_id"));
	            book.setImg(rs.getString("img"));
	            book.setDescription(rs.getString("description"));
	            book.setStatus(rs.getInt("status"));
	            book.setDetail(rs.getString("detail"));
	            book.setQuantity(rs.getInt("quantity"));
	            
	            books.add(book);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCDataSource.closeConnection(conn);
	    }
	    return books;
	}


	@Override
	public void addBook(Book book) {
		Connection conn = null;
	    try {
	        conn = JDBCDataSource.getConnection();
		    PreparedStatement stmt = conn.prepareStatement("INSERT INTO book (name, author, price, category_id, img, description, status, detail, quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		    stmt.setString(1, book.getName());
	        stmt.setString(2, book.getAuthor());
	        stmt.setDouble(3, book.getPrice());
	        stmt.setInt(4, book.getCategoryId());
	        stmt.setString(5, book.getImg());
	        stmt.setString(6, book.getDescription());
	        stmt.setInt(7, book.getStatus());
	        stmt.setString(8, book.getDetail());
	        stmt.setInt(9, book.getQuantity());
	        stmt.executeUpdate();
	    } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
	}

	//Ngừng kinh doanh chứ không xóa 
	@Override
	public void disableBook(int id) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement("UPDATE book SET status = 0 WHERE id = ?");
	        stmt.setInt(1, id);
	        stmt.executeUpdate();
		} catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

	}

	@Override
	public List<Book> getBooksByCategory(int categoryId) {
		List<Book> books = new ArrayList<>();
	    Connection conn = null;
	    try {
	        conn = JDBCDataSource.getConnection();
	        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM book WHERE category_id = ?");
	        stmt.setInt(1, categoryId);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            Book book = new Book();
	            book.setId(rs.getInt("id"));
	            book.setName(rs.getString("name"));
	            book.setAuthor(rs.getString("author"));
	            book.setPrice(rs.getDouble("price"));
	            book.setCategoryId(rs.getInt("category_id"));
	            book.setImg(rs.getString("img"));
	            book.setDescription(rs.getString("description"));
	            book.setStatus(rs.getInt("status"));
	            book.setDetail(rs.getString("detail"));
	            book.setQuantity(rs.getInt("quantity"));

	            books.add(book);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCDataSource.closeConnection(conn);
	    }
	    return books;
	}
	
	@Override
	public List<Book> getActiveBooks() {
		List<Book> books = new ArrayList<>();
	    Connection conn = null;
	    try {
	        conn = JDBCDataSource.getConnection();
	        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM book WHERE status = 1");
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	        	Book book = new Book();
	            book.setId(rs.getInt("id"));
	            book.setName(rs.getString("name"));
	            book.setAuthor(rs.getString("author"));
	            book.setPrice(rs.getDouble("price"));
	            book.setCategoryId(rs.getInt("category_id"));
	            book.setImg(rs.getString("img"));
	            book.setDescription(rs.getString("description"));
	            book.setStatus(rs.getInt("status"));
	            book.setDetail(rs.getString("detail"));
	            book.setQuantity(rs.getInt("quantity"));
	            
	            books.add(book);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCDataSource.closeConnection(conn);
	    }
	    return books;
	}

	@Override
	public List<Book> getActiveBooksByCategory(int categoryId) {
		List<Book> books = new ArrayList<>();
	    Connection conn = null;
	    try {
	        conn = JDBCDataSource.getConnection();
	        PreparedStatement stmt = conn.prepareStatement("SELECT b.id, b.name, b.author, b.price, b.category_id, b.img, b.description, b.status, b.detail, b.quantity "
	        		+ "FROM Book b "
	        		+ "JOIN Category c ON b.category_id = c.id "
	        		+ "WHERE b.category_id = ? AND b.status = 1 AND c.status = 1");
	        stmt.setInt(1, categoryId);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            Book book = new Book();
	            book.setId(rs.getInt("id"));
	            book.setName(rs.getString("name"));
	            book.setAuthor(rs.getString("author"));
	            book.setPrice(rs.getDouble("price"));
	            book.setCategoryId(rs.getInt("category_id"));
	            book.setImg(rs.getString("img"));
	            book.setDescription(rs.getString("description"));
	            book.setStatus(rs.getInt("status"));
	            book.setDetail(rs.getString("detail"));
	            book.setQuantity(rs.getInt("quantity"));
	            
	            books.add(book);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCDataSource.closeConnection(conn);
	    }
	    return books;
	}

	@Override
	public List<Book> searchBooksByKeyword(List<Book> books, String searchKeyword) {
	    List<Book> result = new ArrayList<>();
	    Set<Integer> addedBookIds = new HashSet<>();
	    String lowercaseKeyword = searchKeyword.toLowerCase();
	    CategoryDAO categoryDAO = new CategoryDAOImpl();
	    
	    for (Book book : books) {
	    	Category category = categoryDAO.getCategoryById(book.getCategoryId());
	        if (containsIgnoreCase(book.getAuthor(), lowercaseKeyword)
	                || containsIgnoreCase(book.getName(), lowercaseKeyword)
	                || containsIgnoreCase(book.getDescription(), lowercaseKeyword)
	                || containsIgnoreCase(category.getName(), lowercaseKeyword)) {
	            if (!addedBookIds.contains(book.getId())) {
	                result.add(book);
	                addedBookIds.add(book.getId());
	            }
	        }
	    }
	    
	    return result;
	}

	// kiểm tra xem một chuỗi có chứa một chuỗi con cụ thể hay không, 
	// mà không phân biệt chữ hoa chữ thường trong quá trình so sánh
	private boolean containsIgnoreCase(String text, String keyword) {
	    return text.toLowerCase().contains(keyword);
	}

	@Override
	public Book getBookById(int id) {
		Connection conn = null;
	    try {
	        conn = JDBCDataSource.getConnection();
	        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM book WHERE id = ? AND status = 1");
	        stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            Book book = new Book();
	            book.setId(rs.getInt("id"));
	            book.setName(rs.getString("name"));
	            book.setAuthor(rs.getString("author"));
	            book.setPrice(rs.getDouble("price"));
	            book.setCategoryId(rs.getInt("category_id"));
	            book.setImg(rs.getString("img"));
	            book.setDescription(rs.getString("description"));
	            book.setStatus(rs.getInt("status"));
	            book.setDetail(rs.getString("detail"));
	            book.setQuantity(rs.getInt("quantity"));
	            return book;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCDataSource.closeConnection(conn);
	    }
	    return null; // Trả về null nếu không tìm thấy cuốn sách
	}
}
