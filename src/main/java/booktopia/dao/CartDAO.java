package booktopia.dao;

import java.util.List;

import booktopia.entity.Cart;

public interface CartDAO {

	// Phương thức để lấy danh sách tất cả các giỏ hàng
    List<Cart> getAllCarts();
    
    // Phương thức để thêm một giỏ hàng mới
    void addCart(Cart cart);
    
    // Lấy giỏ hàng theo id người dùng
    Cart getCartByAccountId(int accountId);
    
}
