package booktopia.controller.admin.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import booktopia.entity.Account;
import booktopia.dao.AccountDAO;
import booktopia.dao.AccountDAOImpl;

@WebServlet(name = "ManageChangePasswordServlet", urlPatterns = {"/managechangepassword"})
public class ManageChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AccountDAO accountDAO;

    @Override
    public void init() throws ServletException {
        super.init();
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
        request.getRequestDispatcher("jsp/admin/managechangepassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Thiết lập các thiết lập ký tự ở đây
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
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
        
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        
        // Kiểm tra mật khẩu hiện tại
        if (!BCrypt.checkpw(currentPassword, account.getPassword())) {
            // Hiển thị thông báo mật khẩu hiện tại không đúng
            request.setAttribute("message", "Mật khẩu hiện tại không đúng. Vui lòng thử lại.");
            request.setAttribute("account", account);
            request.getRequestDispatcher("jsp/admin/managechangepassword.jsp").forward(request, response);
            return;
        }
        
        // Kiểm tra mật khẩu mới và mật khẩu nhập lại
        if (!newPassword.equals(confirmPassword)) {
            // Hiển thị thông báo mật khẩu nhập lại không khớp
            request.setAttribute("message", "Mật khẩu nhập lại không khớp. Vui lòng thử lại.");
            request.setAttribute("account", account);
            request.getRequestDispatcher("jsp/admin/managechangepassword.jsp").forward(request, response);
            return;
        }
        
        // Băm mật khẩu mới sử dụng bcrypt
        String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        // Cập nhật mật khẩu mới cho tài khoản
        account.setPassword(hashedNewPassword);
        // Cập nhật thông tin tài khoản vào cơ sở dữ liệu
        accountDAO.updateAccount(account);
        // Hiển thị thông báo thành công
        request.setAttribute("message", "Thay đổi mật khẩu thành công.");
        // Nếu trùng với admin thì lưu thông tin tài khoản mới vào session
        if(admin.getId() == account.getId()) {
        	session.setAttribute("admin", account);
        	request.setAttribute("account", account);
        } else {
        	request.setAttribute("account", account);
        }
        // Forward đến trang quản lý chi tiết tài khoản
        request.getRequestDispatcher("jsp/admin/managedetailaccount.jsp").forward(request, response);
    }
}