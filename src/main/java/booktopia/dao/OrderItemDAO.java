package booktopia.dao;

import java.util.List;

import booktopia.entity.OrderItem;

public interface OrderItemDAO {
    
    // Phương thức để lấy danh sách tất cả các OrderItem
    List<OrderItem> getAllOrderItems();
    
    // Phương thức để thêm một OrderItem mới
    void addOrderItem(OrderItem orderItem);
    
    // Phương thức để cập nhật một OrderItem
    void updateOrderItem(OrderItem orderItem);
    
    // Phương thức để xóa một OrderItem
    void deleteOrderItem(int orderItemId);
    
    // Phương thức lấy OrderItems theo mã đơn hàng
    List<OrderItem> getOrderItemsByOrderId(int orderId);
}
