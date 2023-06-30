package booktopia.controller.customer.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import booktopia.entity.Account;

@WebServlet(name = "ViewAccountServlet", urlPatterns = {"/viewaccount"})
public class ViewAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        // Kiểm tra xem người dùng đã đăng nhập hay chưa
        if (account == null) {
            // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
            response.sendRedirect("login.jsp");
            return;
        }

        // Lưu thông tin tài khoản vào session
        session.setAttribute("account", account);

        // Forward đến trang xem thông tin tài khoản
        request.getRequestDispatcher("jsp/customer/viewaccount.jsp").forward(request, response);
    }
}