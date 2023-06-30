package booktopia.dao;

import java.util.List;
import booktopia.entity.CartItem;

public interface CartItemDAO {

	List<CartItem> getAllCartItems();
    
    List<CartItem> getCartItemsByCartId(int cartId);
    
    void addCartItem(CartItem cartItem);
    
    void updateCartItem(CartItem cartItem);
    
    void deleteCartItem(int id);
    
	CartItem getCartItemByCartAndBook(int cartId, int bookId);

	CartItem getCartItemById(int itemId);
}
