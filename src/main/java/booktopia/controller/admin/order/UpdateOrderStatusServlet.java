package booktopia.controller.admin.order;

import java.io.IOException;

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

@WebServlet(name = "UpdateOrderStatusServlet", urlPatterns = {"/updateorderstatus"})
public class UpdateOrderStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderDAO orderDAO;

    public void init() {
        orderDAO = new OrderDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession();
        Account admin = (Account) session.getAttribute("admin");

        // Kiểm tra xem admin đã đăng nhập hay chưa
        if (admin == null) {
            // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
            response.sendRedirect("jsp/admin/loginadmin.jsp");
            return;
        }
    	
    	// Lấy thông tin đơn hàng từ request
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int status = Integer.parseInt(request.getParameter("status"));

        // Lấy đơn hàng từ CSDL
        Order order = orderDAO.getOrderById(orderId);

        // Cập nhật trạng thái đơn hàng
        order.setStatus(status);

        // Cập nhật đơn hàng trong CSDL
        orderDAO.updateOrder(order);

        // Chuyển hướng về trang manageorderitems.jsp
        response.sendRedirect("manageorderitems?orderId=" + orderId);
    }
}

