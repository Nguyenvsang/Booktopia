package booktopia.controller.admin.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LogoutAdminServlet", urlPatterns = {"/logoutadmin"})
public class LogoutAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xóa session để đăng xuất
        HttpSession session = request.getSession();
        session.invalidate();

        // Chuyển hướng về trang chủ
        response.sendRedirect("jsp/index.jsp");
    }
}

