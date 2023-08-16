package booktopia.controller.admin.account;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import booktopia.entity.Account;
import booktopia.dao.AccountDAO;
import booktopia.dao.AccountDAOImpl;

@WebServlet(name = "ManageAccountServlet", urlPatterns = {"/manageaccount"})
public class ManageAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AccountDAO accountDao;
    public void init() {
        accountDao = new AccountDAOImpl();
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
    	
    	// Lấy trạng thái tài khoản từ URL parameter
        int statusId = -1;
        String statusParam = request.getParameter("status");
        if (statusParam != null && !statusParam.isEmpty()) {
            statusId = Integer.parseInt(statusParam);
        }

        // Lấy danh sách tài khoản theo trạng thái hoặc tất cả tài khoản
        List<Account> accounts;
        if (statusId == -1) {
        	accounts = accountDao.getAllAccounts();
        } else {
        	accounts = accountDao.getAccountsByStatusID(statusId);
        }
        
        // Lấy từ khóa tìm kiếm từ URL parameter
        String searchKeyword = request.getParameter("search");
        
        // Kiểm tra xem có từ khóa tìm kiếm hay không
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            // Tìm kiếm tài khoản theo từ khóa
            accounts = accountDao.searchAccountsByKeyword(accounts, searchKeyword);
        }

        // Lấy tổng số lượng tài khoản
        int totalAccounts = accounts.size();

        // Lấy trang hiện tại từ URL parameter
        int currentPage = 1;
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        // Số tài khoản hiển thị trên mỗi trang
        int recordsPerPage = 10;

        // Tính toán vị trí bắt đầu và kết thúc của tài khoản trên trang hiện tại
        int start = (currentPage - 1) * recordsPerPage;
        int end = Math.min(start + recordsPerPage, totalAccounts);

        // Lấy danh sách đơn hàng trên trang hiện tại
        List<Account> accountsOnPage = accounts.subList(start, end);

        // Tính toán số trang
        int totalPages = (int) Math.ceil((double) totalAccounts / recordsPerPage);

        // Đặt thuộc tính vào request để sử dụng trong JSP
        request.setAttribute("accounts", accountsOnPage);
        request.setAttribute("totalAccounts", totalAccounts);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);

        // Forward đến trang danh sách đơn hàng
        request.getRequestDispatcher("jsp/admin/manageaccount.jsp").forward(request, response);
    }
}

