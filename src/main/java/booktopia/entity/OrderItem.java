package booktopia.entity;

public class OrderItem {

	private int id;
	
	private int quantity;
	
	private double price;
	
	private int bookId;
	
	private int orderId;
	
	public OrderItem() {
		super();
	}

	public OrderItem(int quantity, double price, int bookId, int orderId) {
		super();
		this.quantity = quantity;
		this.price = price;
		this.bookId = bookId;
		this.orderId = orderId;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	
}
