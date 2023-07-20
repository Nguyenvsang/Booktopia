package booktopia.dao;

import java.util.ArrayList;
import java.util.List;

import booktopia.entity.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import booktopia.utility.JDBCDataSource;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `Order`");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setDateOrder(rs.getDate("date_order"));
                order.setTotalPrice(rs.getDouble("total_price"));
                order.setName(rs.getString("name"));
                order.setAddress(rs.getString("address"));
                order.setPhoneNumber(rs.getString("phone_number"));
                order.setEmail(rs.getString("email"));
                order.setAccountId(rs.getInt("account_id"));
                order.setStatus(rs.getInt("status"));
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return orders;
    }

    @Override
    public void addOrder(Order order) {
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO `Order` (date_order, total_price, name, address, phone_number, email, account_id, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setDate(1, new java.sql.Date(order.getDateOrder().getTime()));
            stmt.setDouble(2, order.getTotalPrice());
            stmt.setString(3, order.getName());
            stmt.setString(4, order.getAddress());
            stmt.setString(5, order.getPhoneNumber());
            stmt.setString(6, order.getEmail());
            stmt.setInt(7, order.getAccountId());
            stmt.setInt(8, order.getStatus());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    @Override
    public void updateOrder(Order order) {
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE `Order` SET date_order = ?, total_price = ?, name = ?, address = ?, phone_number = ?, email = ?, account_id = ?, status = ? WHERE id = ?");
            stmt.setDate(1, new java.sql.Date(order.getDateOrder().getTime()));
            stmt.setDouble(2, order.getTotalPrice());
            stmt.setString(3, order.getName());
            stmt.setString(4, order.getAddress());
            stmt.setString(5, order.getPhoneNumber());
            stmt.setString(6, order.getEmail());
            stmt.setInt(7, order.getAccountId());
            stmt.setInt(8, order.getStatus());
            stmt.setInt(9, order.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM `Order` WHERE id = ?");
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    @Override
    public List<Order> getOrdersByAccountId(int accountId) {
        List<Order> orders = new ArrayList<>();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `Order` WHERE account_id = ?");
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setDateOrder(rs.getDate("date_order"));
                order.setTotalPrice(rs.getDouble("total_price"));
                order.setName(rs.getString("name"));
                order.setAddress(rs.getString("address"));
                order.setPhoneNumber(rs.getString("phone_number"));
                order.setEmail(rs.getString("email"));
                order.setAccountId(rs.getInt("account_id"));
                order.setStatus(rs.getInt("status"));
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return orders;
    }

    @Override
    public Order getLastOrder(int accountId) {
        Order order = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `Order` WHERE account_id = ? ORDER BY id DESC LIMIT 1");
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                order = new Order();
                order.setId(rs.getInt("id"));
                order.setDateOrder(rs.getDate("date_order"));
                order.setTotalPrice(rs.getDouble("total_price"));
                order.setName(rs.getString("name"));
                order.setAddress(rs.getString("address"));
                order.setPhoneNumber(rs.getString("phone_number"));
                order.setEmail(rs.getString("email"));
                order.setAccountId(rs.getInt("account_id"));
                order.setStatus(rs.getInt("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return order;
    }

	@Override
	public Order getOrderById(int orderId) {
		Order order = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `Order` WHERE id = ?");
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                order = new Order();
                order.setId(rs.getInt("id"));
                order.setDateOrder(rs.getDate("date_order"));
                order.setTotalPrice(rs.getDouble("total_price"));
                order.setName(rs.getString("name"));
                order.setAddress(rs.getString("address"));
                order.setPhoneNumber(rs.getString("phone_number"));
                order.setEmail(rs.getString("email"));
                order.setAccountId(rs.getInt("account_id"));
                order.setStatus(rs.getInt("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return order;
	}

	@Override
	public List<Order> getOrdersByStatusID(int statusId) {
		List<Order> orders = new ArrayList<>();
	    Connection conn = null;
	    try {
	        conn = JDBCDataSource.getConnection();
	        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `Order` WHERE status = ?");
	        stmt.setInt(1, statusId);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            Order order = new Order();
	            order.setId(rs.getInt("id"));
	            order.setDateOrder(rs.getDate("date_order"));
	            order.setTotalPrice(rs.getDouble("total_price"));
	            order.setName(rs.getString("name"));
	            order.setAddress(rs.getString("address"));
	            order.setPhoneNumber(rs.getString("phone_number"));
	            order.setEmail(rs.getString("email"));
	            order.setAccountId(rs.getInt("account_id"));
	            order.setStatus(rs.getInt("status"));
	            orders.add(order);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCDataSource.closeConnection(conn);
	    }
	    return orders;
	}

	@Override
	public List<Order> searchOrdersByKeyword(List<Order> orders, String searchKeyword) {
		List<Order> result = new ArrayList<>();
	    String lowercaseKeyword = searchKeyword.toLowerCase();

	    for (Order order : orders) {
	        if (containsIgnoreCase(Integer.toString(order.getId()), lowercaseKeyword)
	                || containsIgnoreCase(order.getDateOrder().toString(), lowercaseKeyword)
	                || containsIgnoreCase(Double.toString(order.getTotalPrice()), lowercaseKeyword)
	                || containsIgnoreCase(order.getName(), lowercaseKeyword)
	                || containsIgnoreCase(order.getAddress(), lowercaseKeyword)
	                || containsIgnoreCase(order.getPhoneNumber(), lowercaseKeyword)
	                || containsIgnoreCase(order.getEmail(), lowercaseKeyword)
	                || containsIgnoreCase(Integer.toString(order.getAccountId()), lowercaseKeyword)
	                || containsIgnoreCase(Integer.toString(order.getStatus()), lowercaseKeyword)) {
	            result.add(order);
	        }
	    }

	    return result;
	}
	
	// Kiểm tra xem một chuỗi có chứa một chuỗi con cụ thể hay không,
	// mà không phân biệt chữ hoa chữ thường trong quá trình so sánh
	private boolean containsIgnoreCase(String text, String keyword) {
	    return text.toLowerCase().contains(keyword);
	}

}
