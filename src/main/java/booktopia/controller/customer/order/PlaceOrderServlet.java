package booktopia.controller.customer.order;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import booktopia.dao.BookDAO;
import booktopia.dao.BookDAOImpl;
import booktopia.dao.CartDAO;
import booktopia.dao.CartDAOImpl;
import booktopia.dao.CartItemDAO;
import booktopia.dao.CartItemDAOImpl;
import booktopia.dao.OrderDAO;
import booktopia.dao.OrderDAOImpl;
import booktopia.dao.OrderItemDAO;
import booktopia.dao.OrderItemDAOImpl;
import booktopia.entity.Account;
import booktopia.entity.Book;
import booktopia.entity.Cart;
import booktopia.entity.CartItem;
import booktopia.entity.Order;
import booktopia.entity.OrderItem;

@WebServlet(name = "PlaceOrderServlet", urlPatterns = {"/placeorder"})
public class PlaceOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CartDAO cartDAO;
    private CartItemDAO cartItemDAO;
    private BookDAO bookDAO;
    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;

    public void init() {
        cartDAO = new CartDAOImpl();
        cartItemDAO = new CartItemDAOImpl();
        bookDAO = new BookDAOImpl();
        orderDAO = new OrderDAOImpl();
        orderItemDAO = new OrderItemDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	// Thiết lập các thiết lập ký tự ở đây
    	response.setContentType("text/html;charset=UTF-8");
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	
        // Kiểm tra đăng nhập
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        // Kiểm tra xem người dùng đã đăng nhập hay chưa
        if (account == null) {
            // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
            response.sendRedirect("jsp/customer/loginaccount.jsp");
            return;
        }
        
        // Lấy giỏ hàng dựa trên tài khoản người dùng
        Cart cart = cartDAO.getCartByAccountId(account.getId());
        List<CartItem> cartItems = cartItemDAO.getCartItemsByCartId(cart.getId());
        if (cartItems.size() == 0) {
        	response.sendRedirect("viewcart");
            return;
        }

        // Lấy thông tin từ trang orderinformation
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        
        // Lấy danh sách sách từ BookDAO
        List<Book> books = bookDAO.getAllBooks();
        // Tạo bảng băm (hash map) ánh xạ từ bookId sang Book
        Map<Integer, Book> bookMap = new HashMap<>();
        for (Book book : books) {
            bookMap.put(book.getId(), book);
        }

        // Tính toán tổng số tiền trong giỏ hàng
        double totalAmount = calculateTotalAmount(cartItems, bookMap);

        // Tạo đối tượng đơn hàng
        Order order = new Order();
        order.setDateOrder(new Date());
        order.setTotalPrice(totalAmount);
        order.setName(name);
        order.setAddress(address);
        order.setPhoneNumber(phoneNumber);
        order.setEmail(email);
        order.setAccountId(cart.getAccountId());
        order.setStatus(0);

        // Thêm đơn hàng vào cơ sở dữ liệu
        orderDAO.addOrder(order);

        // Lấy ID của đơn hàng vừa thêm
        Order lastorder = orderDAO.getLastOrder(cart.getAccountId());

        // Tạo danh sách các mục đơn hàng (OrderItem) từ giỏ hàng
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cartItem.getQuantity());
            Book book = bookMap.get(cartItem.getBookId());
            double price = 0.0;
            price = cartItem.getQuantity() * book.getPrice();
            orderItem.setPrice(price);
            orderItem.setBookId(cartItem.getBookId());
            orderItem.setOrderId(lastorder.getId());

            // Thêm mục đơn hàng vào cơ sở dữ liệu
            orderItemDAO.addOrderItem(orderItem);
            
            // Xóa mục giỏ hàng khỏi giỏ hàng
            cartItemDAO.deleteCartItem(cartItem.getId());
            
            // Cập nhật số lượng sách và kiểm tra trạng thái
            int remainingQuantity = book.getQuantity() - cartItem.getQuantity();
            book.setQuantity(remainingQuantity);
            if (remainingQuantity <= 0) {
                book.setStatus(0);
            }
            bookDAO.updateBook(book);
        }

        // Đặt thuộc tính vào request để sử dụng trong JSP
        request.setAttribute("order", lastorder);
        List<OrderItem> orderItems = orderItemDAO.getOrderItemsByOrderId(lastorder.getId());
        request.setAttribute("orderItems", orderItems);
        request.setAttribute("bookMap", bookMap);
        // Forward đến trang danh xác nhận đơn hàng
        request.getRequestDispatcher("jsp/customer/orderconfirmation.jsp").forward(request, response);
    }
    
    private double calculateTotalAmount(List<CartItem> cartItems, Map<Integer, Book> bookMap) {
    	double totalAmount = 0.0;
        for (CartItem cartItem : cartItems) {
            Book book = bookMap.get(cartItem.getBookId());
            totalAmount += cartItem.getQuantity() * book.getPrice();
        }
        return totalAmount;
    }
}