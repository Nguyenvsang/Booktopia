package booktopia.controller.admin.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import booktopia.entity.Account;
import booktopia.entity.Order;
import booktopia.dao.OrderDAO;
import booktopia.dao.OrderDAOImpl;

@WebServlet(name = "ManageOrderServlet", urlPatterns = {"/manageorder"})
public class ManageOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderDAO orderDAO;
    public void init() {
        orderDAO = new OrderDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession();
        Account admin = (Account) session.getAttribute("admin");

        // Kiểm tra xem admin đã đăng nhập hay chưa
        if (admin == null) {
            // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
            response.sendRedirect("jsp/admin/loginadmin.jsp");
            return;
        }
    	
    	// Lấy trạng thái đơn hàng từ URL parameter
        int statusId = -1;
        String statusParam = request.getParameter("status");
        if (statusParam != null && !statusParam.isEmpty()) {
            statusId = Integer.parseInt(statusParam);
        }

        // Lấy danh sách đơn hàng theo trạng thái hoặc tất cả đơn hàng
        List<Order> orders;
        if (statusId == -1) {
        	orders = orderDAO.getAllOrders();
        } else {
        	orders = orderDAO.getOrdersByStatusID(statusId);
        }
        
        // Lấy từ khóa tìm kiếm từ URL parameter
        String searchKeyword = request.getParameter("search");
        
        // Kiểm tra xem có từ khóa tìm kiếm hay không
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            // Tìm kiếm đơn hàng theo từ khóa
            orders = orderDAO.searchOrdersByKeyword(orders, searchKeyword);
        }

        // Lấy tổng số lượng đơn hàng
        int totalOrders = orders.size();

        // Lấy trang hiện tại từ URL parameter
        int currentPage = 1;
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        // Số đơn hàng hiển thị trên mỗi trang
        int recordsPerPage = 10;

        // Tính toán vị trí bắt đầu và kết thúc của đơn hàng trên trang hiện tại
        int start = (currentPage - 1) * recordsPerPage;
        int end = Math.min(start + recordsPerPage, totalOrders);

        // Lấy danh sách đơn hàng trên trang hiện tại
        List<Order> ordersOnPage = orders.subList(start, end);

        // Tính toán số trang
        int totalPages = (int) Math.ceil((double) totalOrders / recordsPerPage);

        // Đặt thuộc tính vào request để sử dụng trong JSP
        request.setAttribute("orders", ordersOnPage);
        request.setAttribute("totalOrders", totalOrders);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);

        // Forward đến trang danh sách đơn hàng
        request.getRequestDispatcher("jsp/admin/manageorder.jsp").forward(request, response);
    }
}
