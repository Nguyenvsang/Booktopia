package booktopia.controller.customer.order;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import booktopia.dao.BookDAO;
import booktopia.dao.BookDAOImpl;
import booktopia.dao.OrderDAO;
import booktopia.dao.OrderDAOImpl;
import booktopia.dao.OrderItemDAO;
import booktopia.dao.OrderItemDAOImpl;
import booktopia.entity.Book;
import booktopia.entity.Order;
import booktopia.entity.OrderItem;

@WebServlet(name = "ViewOrderItemsServlet", urlPatterns = {"/vieworderitems"})
public class ViewOrderItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private OrderDAO orderDAO;
    private BookDAO bookDAO;
    private OrderItemDAO orderItemDAO;

    public void init() {
        orderDAO = new OrderDAOImpl();
        bookDAO = new BookDAOImpl();
        orderItemDAO = new OrderItemDAOImpl();
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy orderId từ request parameter
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        // Lấy đối tượng Order từ OrderDAO bằng orderId
        Order order = orderDAO.getOrderById(orderId);

        // Lấy danh sách OrderItem từ Order
        List<OrderItem> orderItems = orderItemDAO.getOrderItemsByOrderId(orderId);

        // Tạo một HashMap để ánh xạ từ bookId sang Book tương ứng
        Map<Integer, Book> bookMap = new HashMap<>();
        for (OrderItem orderItem : orderItems) {
            int bookId = orderItem.getBookId();
            Book book = bookDAO.getBookById(bookId);
            bookMap.put(bookId, book);
        }

        // Đặt đối tượng Order và bookMap vào thuộc tính request để sử dụng trong JSP
        request.setAttribute("order", order);
        request.setAttribute("orderItems", orderItems);
        request.setAttribute("bookMap", bookMap);

        // Forward đến trang vieworderitem.jsp
        request.getRequestDispatcher("jsp/customer/vieworderitems.jsp").forward(request, response);
    }
}
