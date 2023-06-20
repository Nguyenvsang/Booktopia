package booktopia.dao;

import java.util.ArrayList;
import java.util.List;

import booktopia.entity.Cart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import booktopia.utility.JDBCDataSource;

public class CartDAOImpl implements CartDAO {

	@Override
	public List<Cart> getAllCarts() {
		List<Cart> carts = new ArrayList<>();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Cart");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setId(rs.getInt("id"));
                cart.setAccountId(rs.getInt("account_id"));
                carts.add(cart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return carts;
	}

	@Override
	public void addCart(Cart cart) {
		Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Cart (account_id) VALUES (?)");
            stmt.setInt(1, cart.getAccountId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
	}

	@Override
	public Cart getCartByAccountId(int accountId) {
	    Cart cart = null;
	    Connection conn = null;
	    try {
	        conn = JDBCDataSource.getConnection();
	        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Cart WHERE account_id = ?");
	        stmt.setInt(1, accountId);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            cart = new Cart();
	            cart.setId(rs.getInt("id"));
	            cart.setAccountId(rs.getInt("account_id"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCDataSource.closeConnection(conn);
	    }
	    return cart;
	}

}
