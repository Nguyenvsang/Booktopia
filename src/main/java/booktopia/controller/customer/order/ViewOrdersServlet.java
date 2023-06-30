package booktopia.controller.customer.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import booktopia.dao.OrderDAO;
import booktopia.dao.OrderDAOImpl;
import booktopia.entity.Account;
import booktopia.entity.Order;

@WebServlet(name = "ViewOrdersServlet", urlPatterns = {"/vieworders"})
public class ViewOrdersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private OrderDAO orderDAO;

    public void init() {
        orderDAO = new OrderDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {    	
    	// Kiểm tra đăng nhập
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        // Kiểm tra xem người dùng đã đăng nhập hay chưa
        if (account == null) {
            // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
            response.sendRedirect("jsp/customer/loginaccount.jsp");
            return;
        }
    	
        // Lấy danh sách đơn hàng từ OrderDAO
        List<Order> orders = orderDAO.getOrdersByAccountId(account.getId());

        // Đặt danh sách đơn hàng vào thuộc tính request để sử dụng trong JSP
        request.setAttribute("orders", orders);

        // Forward đến trang vieworder.jsp
        request.getRequestDispatcher("jsp/customer/vieworders.jsp").forward(request, response);
    }
}
