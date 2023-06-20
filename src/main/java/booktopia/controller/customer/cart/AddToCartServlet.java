package booktopia.controller.customer.cart;

import java.io.IOException;
import java.net.URLEncoder;

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

@WebServlet(name = "AddToCartServlet", urlPatterns = {"/addtocart"})
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CartDAO cartDAO;
    private CartItemDAO cartItemDAO;
    private BookDAO bookDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        cartDAO = new CartDAOImpl();
        cartItemDAO = new CartItemDAOImpl();
        bookDAO = new BookDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        // Kiểm tra xem người dùng đã đăng nhập hay chưa
        if (account == null) {
            // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
            response.sendRedirect("jsp/customer/loginaccount.jsp");
            return;
        }

        // Lấy thông tin sách từ request
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Kiểm tra số lượng hợp lệ
        if (quantity <= 0) {
            // Số lượng không hợp lệ, lưu thông báo lỗi và chuyển hướng về trang chi tiết sách
            String message = "Số lượng không hợp lệ";
            response.sendRedirect("detailbook?bookId=" + bookId + "&message=" + URLEncoder.encode(message, "UTF-8"));
            return;
        }

        // Kiểm tra xem giỏ hàng của người dùng đã tồn tại hay chưa
        int accountId = account.getId();
        Cart cart = cartDAO.getCartByAccountId(accountId);
        if (cart == null) {
            // Nếu giỏ hàng chưa tồn tại, thêm giỏ hàng mới
            cart = new Cart(accountId);
            cartDAO.addCart(cart);
        }

        // Tìm CartItem theo cartId và bookId
        CartItem cartItem = cartItemDAO.getCartItemByCartAndBook(cart.getId(), bookId);

        if (cartItem == null) {
            // Nếu CartItem chưa tồn tại, tạo mới và thêm vào giỏ hàng
            cartItem = new CartItem(quantity, cart.getId(), bookId);
            cartItemDAO.addCartItem(cartItem);
        } else {
            // Nếu CartItem đã tồn tại, cộng dồn số lượng
            int currentQuantity = cartItem.getQuantity();
            int newQuantity = currentQuantity + quantity;

            // Lấy thông tin sách từ cơ sở dữ liệu
            Book book = bookDAO.getBookById(bookId);

            // Kiểm tra số lượng tồn kho
            if (newQuantity > book.getQuantity()) {
                // Số lượng vượt quá số lượng tồn kho, lưu thông báo lỗi và chuyển hướng về trang chi tiết sách
                String message = "Số lượng vượt quá số lượng tồn kho";
                response.sendRedirect("detailbook?bookId=" + bookId + "&message=" + URLEncoder.encode(message, "UTF-8"));
                return;
            }

            cartItem.setQuantity(newQuantity);
            cartItemDAO.updateCartItem(cartItem);
        }

        // Chuyển hướng về trang hiển thị giỏ hàng
        // Thành công, lưu thông báo thành công
        String message = "Thêm vào giỏ hàng thành công";
        response.sendRedirect("detailbook?bookId=" + bookId + "&message=" + URLEncoder.encode(message, "UTF-8"));
    }
}
