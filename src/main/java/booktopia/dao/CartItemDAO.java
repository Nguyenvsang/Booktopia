package booktopia.dao;

import java.util.List;
import booktopia.entity.CartItem;

public interface CartItemDAO {

List<CartItem> getAllCartItems();
    
    List<CartItem> getCartItemsById(int id);
    
    void addCartItem(CartItem cartItem);
    
    void updateCartItem(CartItem cartItem);
    
    void deleteCartItem(int id);
    
	CartItem getCartItemByCartAndBook(int cartId, int bookId);
}
