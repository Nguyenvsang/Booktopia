package booktopia.entity;

public class CartItem {

	private int id;
	private int quantity;
	private int cartId;
	private int bookId;
	
	public CartItem() {
		super();
	}

	public CartItem(int quantity, int cartId, int bookId) {
		super();
		this.quantity = quantity;
		this.cartId = cartId;
		this.bookId = bookId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	
}
