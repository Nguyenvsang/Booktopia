package booktopia.controller.customer.cart;

import java.io.IOException;
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
import booktopia.entity.Account;
import booktopia.entity.Book;
import booktopia.entity.Cart;
import booktopia.entity.CartItem;

@WebServlet(name = "ViewCartServlet", urlPatterns = {"/viewcart"})
public class ViewCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CartItemDAO cartItemDAO;
    private CartDAO cartDAO;
    private BookDAO bookDAO;

    public void init() {
        cartItemDAO = new CartItemDAOImpl();
        cartDAO = new CartDAOImpl();
        bookDAO = new BookDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        Cart cart;
        if (account != null) {
            int accountId = account.getId();
            cart = cartDAO.getCartByAccountId(accountId);

            if (cart == null) {
                cart = new Cart(accountId);
                cartDAO.addCart(cart);
            }
        } else {
            // Xử lý khi không có tài khoản (chưa đăng nhập)
            // Ví dụ: Chuyển hướng đến trang đăng nhập
            response.sendRedirect("jsp/customer/loginaccount.jsp");
            return;
        }

        
        int cartId = cart.getId();

        // Lấy danh sách các mục trong giỏ hàng
        List<CartItem> cartItems = cartItemDAO.getCartItemsByCartId(cartId);
        
        // Kiểm tra xem giỏ hàng có hàng không 
        if (cartItems.size() == 0) {
            request.setAttribute("message", "Giỏ hàng trống!");
        	// Chuyển hướng đến trang viewcart.jsp
            request.getRequestDispatcher("jsp/customer/viewcart.jsp").forward(request, response);
            return;
        }
        
        // Lấy danh sách sách từ BookDAO
        List<Book> books = bookDAO.getAllBooks();
        // Tạo bảng băm (hash map) ánh xạ từ bookId sang Book
        Map<Integer, Book> bookMap = new HashMap<>();
        for (Book book : books) {
            bookMap.put(book.getId(), book);
        }

        // Tính toán tổng số tiền trong giỏ hàng
        double totalAmount = calculateTotalAmount(cartItems, bookMap);

        // Lưu thông tin giỏ hàng, tổng số tiền, bảng băm vào request attribute
        request.setAttribute("cartItems", cartItems);
        request.setAttribute("totalAmount", totalAmount);
        request.setAttribute("bookMap", bookMap);

        // Chuyển hướng đến trang viewcart.jsp
        request.getRequestDispatcher("jsp/customer/viewcart.jsp").forward(request, response);
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
