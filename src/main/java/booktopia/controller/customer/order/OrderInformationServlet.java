package booktopia.controller.customer.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import booktopia.dao.CartDAO;
import booktopia.dao.CartDAOImpl;
import booktopia.dao.CartItemDAO;
import booktopia.dao.CartItemDAOImpl;
import booktopia.entity.Account;
import booktopia.entity.Cart;
import booktopia.entity.CartItem;

@WebServlet(name = "OrderInformationServlet", urlPatterns = {"/orderinformation"})
public class OrderInformationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private CartDAO cartDAO;
    private CartItemDAO cartItemDAO;
    
    public void init() {
        cartDAO = new CartDAOImpl();
        cartItemDAO = new CartItemDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        
        // Kiểm tra xem người dùng đã đăng nhập hay chưa
        if (account == null) {
            // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
            response.sendRedirect("jsp/customer/loginaccount.jsp");
            return;
        }
        
        // Kiểm tra xem giỏ hàng có hàng không 
        Cart cart = cartDAO.getCartByAccountId(account.getId());
        List<CartItem> cartItems = cartItemDAO.getCartItemsByCartId(cart.getId());
        if (cartItems.size() == 0) {
        	response.sendRedirect("viewcart");
            return;
        }
        
        double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
    	
        request.setAttribute("account", account);
        request.setAttribute("totalAmount", totalAmount);
        request.getRequestDispatcher("jsp/customer/orderinformation.jsp").forward(request, response);
    }
}
