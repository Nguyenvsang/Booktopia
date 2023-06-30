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

@WebServlet(name = "RemoveFromCartServlet", urlPatterns = {"/removefromcart"})
public class RemoveFromCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CartItemDAO cartItemDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
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
    	
    	int itemId = Integer.parseInt(request.getParameter("itemId"));
        
        // Xóa CartItem khỏi giỏ hàng
        cartItemDAO.deleteCartItem(itemId);
        
        // Chuyển hướng về trang hiển thị giỏ hàng
        response.sendRedirect("viewcart");
    }
}
