package booktopia.controller.admin.category;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import booktopia.dao.BookDAOImpl;
import booktopia.dao.CategoryDAO;
import booktopia.dao.CategoryDAOImpl;
import booktopia.entity.Account;
import booktopia.entity.Category;

@WebServlet(name = "AddCategoryServlet", urlPatterns = {"/addcategory"})
public class AddCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryDAO categoryDAO;
    
    public void init() {
        categoryDAO = new CategoryDAOImpl();
        new BookDAOImpl();
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
      
        // Forward đến trang addcategory.jsp
        request.getRequestDispatcher("jsp/admin/addcategory.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Account admin = (Account) session.getAttribute("admin");

        // Kiểm tra xem admin đã đăng nhập hay chưa
        if (admin == null) {
            // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
            response.sendRedirect("jsp/admin/loginadmin.jsp");
            return;
        }

        // Lấy thông tin danh mục được tạo mới từ request parameter
        String categoryName = request.getParameter("name");
        int status = Integer.parseInt(request.getParameter("status"));

        // Tạo Category mới 
        Category category = new Category(categoryName, status);
        
        // Tạo và lưu vào cơ sở dữ liệu
        categoryDAO.addCategory(category);

        request.setAttribute("message", "Đã thêm danh mục mới thành công");
        // Forward đến trang addcategory.jsp sau khi thêm mới
        request.getRequestDispatcher("jsp/admin/addcategory.jsp").forward(request, response);
    }
}