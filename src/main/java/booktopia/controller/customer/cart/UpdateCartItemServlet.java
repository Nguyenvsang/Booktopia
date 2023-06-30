package booktopia.controller.customer.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import booktopia.dao.CartItemDAO;
import booktopia.dao.CartItemDAOImpl;
import booktopia.entity.Account;
import booktopia.entity.CartItem;

@WebServlet(name = "UpdateCartItemServlet", urlPatterns = {"/updatecartitem"})
public class UpdateCartItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartItemDAO cartItemDAO;
	@Override
    public void init() throws ServletException {
        super.init();
        cartItemDAO = new CartItemDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        
        // Kiểm tra xem người dùng đã đăng nhập hay chưa
        if (account == null) {
            // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
            response.sendRedirect("jsp/customer/loginaccount.jsp");
            return;
        }
    	
    	int itemId = Integer.parseInt(request.getParameter("itemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        
        CartItem cartItem = cartItemDAO.getCartItemById(itemId);
        cartItem.setQuantity(quantity);
        cartItemDAO.updateCartItem(cartItem);
        
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
