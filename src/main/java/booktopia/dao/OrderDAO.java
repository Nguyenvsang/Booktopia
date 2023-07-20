package booktopia.dao;

import java.util.List;

import booktopia.entity.Order;

public interface OrderDAO {

	// Phương thức để lấy danh sách tất cả các đơn hàng
	List<Order> getAllOrders();
	
	// Phương thức để thêm một đơn hàng
	void addOrder(Order order);
	
	// Phương thức để cập nhật một đơn hàng
	void updateOrder(Order order);
	
	// Phương thức để xóa một đơn hàng
	void deleteOrder(int orderId);
	
	// Phương thức lấy những đơn hàng theo mã người dùng
	List<Order> getOrdersByAccountId(int accountId);

	// Lấy đơn hàng cuối cùng theo accountId 
	Order getLastOrder(int accountId);

	// Lấy đơn hàng theo Id
	Order getOrderById(int orderId);

	// Lấy đơn hàng theo mã trạng thái
	List<Order> getOrdersByStatusID(int statusId);
	
	// Lấy đơn hàng theo từ khóa
	List<Order> searchOrdersByKeyword(List<Order> orders, String searchKeyword);
}
