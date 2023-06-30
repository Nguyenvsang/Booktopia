package booktopia.controller.customer.account;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import booktopia.entity.Account;
import booktopia.dao.AccountDAO;
import booktopia.dao.AccountDAOImpl;

@WebServlet(name = "RegisterAccountServlet", urlPatterns = {"/registeraccount"})
public class RegisterAccountServlet extends HttpServlet {
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
    	
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");

        Date dateOfBirth = Date.valueOf(dob);
        
        // Băm mật khẩu sử dụng bcrypt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        Account account = new Account(username, hashedPassword, address, phoneNumber, email, 1, 1);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setGender(gender);
        account.setDateOfBirth(dateOfBirth);
        account.setImg("");

        try {
            if (accountDAO.findAccountByUsername(username)!=null) {
                // Username already exists
                request.setAttribute("message", "Tên tài khoản đã tồn tại. Vui lòng chọn tên khác.");
                request.getRequestDispatcher("jsp/customer/registeraccount.jsp").forward(request, response);
            } else {
                accountDAO.addAccount(account);
                request.setAttribute("message", "Đăng ký tài khoản thành công.");
                request.getRequestDispatcher("jsp/customer/registeraccount.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Đã xảy ra lỗi. Vui lòng thử lại sau.");
            request.getRequestDispatcher("jsp/customer/registeraccount.jsp").forward(request, response);
        }
    }
}