package booktopia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import booktopia.entity.OrderItem;
import booktopia.utility.JDBCDataSource;

public class OrderItemDAOImpl implements OrderItemDAO {

    @Override
    public List<OrderItem> getAllOrderItems() {
        List<OrderItem> orderItems = new ArrayList<>();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM OrderItem");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(rs.getInt("id"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setPrice(rs.getDouble("price"));
                orderItem.setBookId(rs.getInt("book_id"));
                orderItem.setOrderId(rs.getInt("order_id"));
                orderItems.add(orderItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return orderItems;
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO OrderItem (quantity, price, book_id, order_id) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, orderItem.getQuantity());
            stmt.setDouble(2, orderItem.getPrice());
            stmt.setInt(3, orderItem.getBookId());
            stmt.setInt(4, orderItem.getOrderId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE OrderItem SET quantity = ?, price = ?, book_id = ?, order_id = ? WHERE id = ?");
            stmt.setInt(1, orderItem.getQuantity());
            stmt.setDouble(2, orderItem.getPrice());
            stmt.setInt(3, orderItem.getBookId());
            stmt.setInt(4, orderItem.getOrderId());
            stmt.setInt(5, orderItem.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    @Override
    public void deleteOrderItem(int orderItemId) {
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM OrderItem WHERE id = ?");
            stmt.setInt(1, orderItemId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM OrderItem WHERE order_id = ?");
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(rs.getInt("id"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setPrice(rs.getDouble("price"));
                orderItem.setBookId(rs.getInt("book_id"));
                orderItem.setOrderId(rs.getInt("order_id"));
                orderItems.add(orderItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return orderItems;
    }
}
