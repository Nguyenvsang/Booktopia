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
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO `Order` (date_order, total_price, name, address, phone_number, email, account_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setDate(1, new java.sql.Date(order.getDateOrder().getTime()));
            stmt.setDouble(2, order.getTotalPrice());
            stmt.setString(3, order.getName());
            stmt.setString(4, order.getAddress());
            stmt.setString(5, order.getPhoneNumber());
            stmt.setString(6, order.getEmail());
            stmt.setInt(7, order.getAccountId());
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
            PreparedStatement stmt = conn.prepareStatement("UPDATE `Order` SET date_order = ?, total_price = ?, name = ?, address = ?, phone_number = ?, email = ?, account_id = ? WHERE id = ?");
            stmt.setDate(1, new java.sql.Date(order.getDateOrder().getTime()));
            stmt.setDouble(2, order.getTotalPrice());
            stmt.setString(3, order.getName());
            stmt.setString(4, order.getAddress());
            stmt.setString(5, order.getPhoneNumber());
            stmt.setString(6, order.getEmail());
            stmt.setInt(7, order.getAccountId());
            stmt.setInt(8, order.getId());
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return order;
	}

}
