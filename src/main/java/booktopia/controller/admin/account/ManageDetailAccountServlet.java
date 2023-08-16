package booktopia.controller.admin.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import booktopia.dao.AccountDAO;
import booktopia.dao.AccountDAOImpl;
import booktopia.entity.Account;

@WebServlet(name = "ManageDetailAccountServlet", urlPatterns = {"/managedetailaccount"})
public class ManageDetailAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private AccountDAO accountDAO;

    public void init() {
        accountDAO = new AccountDAOImpl(); 
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
        
        // Lấy thông tin về tài khoản từ yêu cầu (thông qua tham số truyền)
        String accountId = request.getParameter("accountId");
        
        // Truy xuất dữ liệu từ nguồn dữ liệu 
        Account account = accountDAO.findAccountById(Integer.parseInt(accountId));

        // Lưu thông tin tài khoản vào request
        request.setAttribute("account", account);

        // Forward đến trang quản lý chi tiết tài khoản
        request.getRequestDispatcher("jsp/admin/managedetailaccount.jsp").forward(request, response);
    }
}