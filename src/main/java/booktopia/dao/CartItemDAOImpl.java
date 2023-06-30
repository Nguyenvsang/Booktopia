package booktopia.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import booktopia.entity.CartItem;
import booktopia.utility.JDBCDataSource;

public class CartItemDAOImpl implements CartItemDAO {

	@Override
	public List<CartItem> getAllCartItems() {
		List<CartItem> cartItems = new ArrayList<>();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CartItem");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CartItem cartItem = new CartItem();
                cartItem.setId(rs.getInt("id"));
                cartItem.setQuantity(rs.getInt("quantity"));
                cartItem.setCartId(rs.getInt("cart_id"));
                cartItem.setBookId(rs.getInt("book_id"));
                cartItems.add(cartItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return cartItems;
	}

	@Override
	public List<CartItem> getCartItemsByCartId(int cartId) {
		List<CartItem> cartItems = new ArrayList<>();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CartItem WHERE cart_id = ?");
            stmt.setInt(1, cartId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CartItem cartItem = new CartItem();
                cartItem.setId(rs.getInt("id"));
                cartItem.setQuantity(rs.getInt("quantity"));
                cartItem.setCartId(rs.getInt("cart_id"));
                cartItem.setBookId(rs.getInt("book_id"));
                cartItems.add(cartItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return cartItems;
	}

	@Override
	public void addCartItem(CartItem cartItem) {
		Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO CartItem (quantity, cart_id, book_id) VALUES (?, ?, ?)");
            stmt.setInt(1, cartItem.getQuantity());
            stmt.setInt(2, cartItem.getCartId());
            stmt.setInt(3, cartItem.getBookId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
	}

	@Override
	public void updateCartItem(CartItem cartItem) {
		Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE CartItem SET quantity = ? WHERE id = ?");
            stmt.setInt(1, cartItem.getQuantity());
            stmt.setInt(2, cartItem.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
	}

	@Override
	public void deleteCartItem(int id) {
		Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM CartItem WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
	}

	@Override
	public CartItem getCartItemByCartAndBook(int cartId, int bookId) {
		Connection conn = null;
	    try {
	        conn = JDBCDataSource.getConnection();
	        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CartItem WHERE cart_id = ? AND book_id = ?");
	        stmt.setInt(1, cartId);
	        stmt.setInt(2, bookId);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            CartItem cartItem = new CartItem();
	            cartItem.setId(rs.getInt("id"));
	            cartItem.setQuantity(rs.getInt("quantity"));
	            cartItem.setCartId(rs.getInt("cart_id"));
	            cartItem.setBookId(rs.getInt("book_id"));
	            return cartItem;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCDataSource.closeConnection(conn);
	    }
	    return null;
	}

	@Override
	public CartItem getCartItemById(int itemId) {
		CartItem cartItem = null;
	    Connection conn = null;
	    try {
	        conn = JDBCDataSource.getConnection();
	        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CartItem WHERE id = ?");
	        stmt.setInt(1, itemId);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            cartItem = new CartItem();
	            cartItem.setId(rs.getInt("id"));
	            cartItem.setQuantity(rs.getInt("quantity"));
	            cartItem.setCartId(rs.getInt("cart_id"));
	            cartItem.setBookId(rs.getInt("book_id"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCDataSource.closeConnection(conn);
	    }
	    return cartItem;
	}

}
