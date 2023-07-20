package booktopia.controller.customer.account;

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

@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/changepassword"})
public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AccountDAO accountDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        accountDAO = new AccountDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Thiết lập các thiết lập ký tự ở đây
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        // Kiểm tra xem người dùng đã đăng nhập hay chưa
        if (account == null) {
            // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
            response.sendRedirect("jsp/customer/loginaccount.jsp");
            return;
        }
        
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        
        // Kiểm tra mật khẩu hiện tại
        if (!BCrypt.checkpw(currentPassword, account.getPassword())) {
            // Hiển thị thông báo mật khẩu hiện tại không đúng
            request.setAttribute("message", "Mật khẩu hiện tại không đúng. Vui lòng thử lại.");
            request.getRequestDispatcher("jsp/customer/changepassword.jsp").forward(request, response);
            return;
        }
        
        // Kiểm tra mật khẩu mới và mật khẩu nhập lại
        if (!newPassword.equals(confirmPassword)) {
            // Hiển thị thông báo mật khẩu nhập lại không khớp
            request.setAttribute("message", "Mật khẩu nhập lại không khớp. Vui lòng thử lại.");
            request.getRequestDispatcher("jsp/customer/changepassword.jsp").forward(request, response);
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
        request.getRequestDispatcher("jsp/customer/viewaccount.jsp").forward(request, response);
    }
}
