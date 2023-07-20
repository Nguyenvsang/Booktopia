package booktopia.controller.admin.category;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import booktopia.dao.CategoryDAO;
import booktopia.dao.CategoryDAOImpl;
import booktopia.entity.Account;
import booktopia.entity.Category;

@WebServlet(name = "ManageCategoryServlet", urlPatterns = {"/managecategory"})
public class ManageCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryDAO categoryDAO;

    public void init() {
        categoryDAO = new CategoryDAOImpl();
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
        
        // Lấy trạng thái danh mục từ URL parameter
        int statusId = -1;
        String statusParam = request.getParameter("status");
        if (statusParam != null && !statusParam.isEmpty()) {
            statusId = Integer.parseInt(statusParam);
        }
        
        // Lấy danh sách danh mục theo trạng thái hoặc tất cả danh mục 
        List<Category> categories;
        if (statusId == -1) {
        	categories = categoryDAO.getAllCategories();
        } else {
        	categories = categoryDAO.getCategoriesByStatusID(statusId);
        }
        
        // Lấy từ khóa tìm kiếm từ URL parameter
        String searchKeyword = request.getParameter("search");
        
        // Kiểm tra xem có từ khóa tìm kiếm hay không
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            // Tìm kiếm danh mục theo từ khóa
        	categories = categoryDAO.searchCategoriesByKeyword(categories, searchKeyword);
        }
        
        // Lấy tổng số lượng danh mục
        int totalCategories = categories.size();
        
        // Lấy trang hiện tại từ URL parameter
        int currentPage = 1;
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        
        // Số danh mục hiển thị trên mỗi trang
        int recordsPerPage = 10;
        
        // Tính toán vị trí bắt đầu và kết thúc của danh mục trên trang hiện tại
        int start = (currentPage - 1) * recordsPerPage;
        int end = Math.min(start + recordsPerPage, totalCategories);
        
        // Lấy danh sách danh mục trên trang hiện tại
        List<Category> categoriesOnPage = categories.subList(start, end);
        
        // Tính toán số trang
        int totalPages = (int) Math.ceil((double) totalCategories / recordsPerPage);

        // Đặt thuộc tính vào request để sử dụng trong JSP
        request.setAttribute("categories", categoriesOnPage);
        request.setAttribute("totalCategories", totalCategories);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);

        // Forward đến trang danh sách danh mục
        request.getRequestDispatcher("jsp/admin/managecategory.jsp").forward(request, response);
    }
}
