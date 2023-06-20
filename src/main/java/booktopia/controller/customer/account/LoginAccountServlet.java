package booktopia.controller.customer.account;

import java.io.IOException; 
import booktopia.entity.Account; 
import booktopia.dao.AccountDAO; 
import booktopia.dao.AccountDAOImpl;
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginAccountServlet", urlPatterns = {"/loginaccount"})
public class LoginAccountServlet extends HttpServlet {

	// Khai báo trường serialVersionUID kiểu long 
	private static final long serialVersionUID = 1L;
	
	private AccountDAO accountDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        accountDAO = new AccountDAOImpl();
    }
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Lấy thông tin username và password từ form gửi lên
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Kiểm tra đăng nhập bằng phương thức checkLogin
		boolean isValid = accountDAO.checkLogin(username, password);

		if (isValid) {
			// Nếu đăng nhập thành công, tạo một session và lưu thông tin tài khoản vào session
			HttpSession session = request.getSession();
			Account account = accountDAO.findAccountByUsername(username);
			session.setAttribute("account", account);
			// Chuyển hướng về trang chủ
			response.sendRedirect("jsp/index.jsp");
		} else {
			// Nếu đăng nhập thất bại, hiển thị thông báo lỗi và quay lại trang đăng nhập
			request.setAttribute("message", "Sai tên đăng nhập hoặc mật khẩu");
			request.getRequestDispatcher("jsp/customer/loginaccount.jsp").forward(request, response);
		}
		
	}

}