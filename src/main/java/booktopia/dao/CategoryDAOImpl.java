package booktopia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import booktopia.entity.Category;
import booktopia.utility.JDBCDataSource;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Category");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setStatus(rs.getInt("status"));
                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return categories;
    }

    @Override
    public void addCategory(Category category) {
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Category (name, status) VALUES (?, ?)");
            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getStatus());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    @Override
    public void disableCategory(int id) {
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE Category SET status = 0 WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

	@Override
	public Category getCategoryById(int id) {
		Category category = null;
		Connection conn = null;
	    try {
	        conn = JDBCDataSource.getConnection();
	        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Category WHERE id = ?");
	        stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            category = new Category();
	            category.setId(rs.getInt("id"));
	            category.setName(rs.getString("name"));
	            category.setStatus(rs.getInt("status"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCDataSource.closeConnection(conn);
	    }
	    return category;
	}

	@Override
	public List<Category> getActiveCategories() {
		List<Category> categories = new ArrayList<>();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Category WHERE status = 1");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setStatus(rs.getInt("status"));
                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return categories;
	}

	@Override
	public List<Category> searchCategoriesByKeyword(List<Category> categories, String searchKeyword) {
		List<Category> result = new ArrayList<>();
	    String lowercaseKeyword = searchKeyword.toLowerCase();

	    for (Category category : categories) {
	        if (containsIgnoreCase(Integer.toString(category.getId()), lowercaseKeyword)
	                || containsIgnoreCase(category.getName(), lowercaseKeyword)
	                || containsIgnoreCase(Integer.toString(category.getStatus()), lowercaseKeyword)) {
	            result.add(category);
	        }
	    }

	    return result;
	}
	
	// Kiểm tra xem một chuỗi có chứa một chuỗi con cụ thể hay không,
	// mà không phân biệt chữ hoa chữ thường trong quá trình so sánh
	private boolean containsIgnoreCase(String text, String keyword) {
	    return text.toLowerCase().contains(keyword);
	}

	@Override
	public List<Category> getCategoriesByStatusID(int statusId) {
		List<Category> categories = new ArrayList<>();
	    Connection conn = null;
	    try {
	        conn = JDBCDataSource.getConnection();
	        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Category WHERE status = ?");
	        stmt.setInt(1, statusId);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            Category category = new Category();
	            category.setId(rs.getInt("id"));
	            category.setName(rs.getString("name"));
	            category.setStatus(rs.getInt("status"));
	            categories.add(category);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCDataSource.closeConnection(conn);
	    }
	    return categories;
	}

	@Override
	public void updateCategory(Category category) {
		Connection conn = null;
	    try {
	        conn = JDBCDataSource.getConnection();
	        PreparedStatement stmt = conn.prepareStatement("UPDATE Category SET name = ?, status = ? WHERE id = ?");
	        stmt.setString(1, category.getName());
	        stmt.setInt(2, category.getStatus());
	        stmt.setInt(3, category.getId());
	        stmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCDataSource.closeConnection(conn);
	    }
	}
}
