package booktopia.controller.admin.category;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import booktopia.dao.BookDAO;
import booktopia.dao.BookDAOImpl;
import booktopia.dao.CategoryDAO;
import booktopia.dao.CategoryDAOImpl;
import booktopia.entity.Account;
import booktopia.entity.Book;
import booktopia.entity.Category;

@WebServlet(name = "EditCategoryServlet", urlPatterns = {"/editcategory"})
public class EditCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryDAO categoryDAO;
    private BookDAO bookDAO;

    public void init() {
        categoryDAO = new CategoryDAOImpl();
        bookDAO = new BookDAOImpl();
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
        
    	// Lấy thông tin categoryId từ request parameter
        String categoryIdStr = request.getParameter("categoryId");
        int categoryId = 0;

        if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
            categoryId = Integer.parseInt(categoryIdStr);
        } else {
            request.setAttribute("message", "Không tìm thấy danh mục");
        }

        // Lấy thông tin category từ categoryId
        Category category = categoryDAO.getCategoryById(categoryId);

        if (category != null) {
            request.setAttribute("category", category);
        } else {
            request.setAttribute("message", "Không tìm thấy danh mục");
        }

        // Forward đến trang editcategory.jsp để hiển thị thông tin và cho phép chỉnh sửa
        request.getRequestDispatcher("jsp/admin/editcategory.jsp").forward(request, response);
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

        // Lấy thông tin categoryId từ request parameter
        int categoryId = Integer.parseInt(request.getParameter("id"));

        // Lấy thông tin danh mục được chỉnh sửa từ request parameter
        String categoryName = request.getParameter("name");
        int status = Integer.parseInt(request.getParameter("status"));

        // Lấy category từ categoryId
        Category category = categoryDAO.getCategoryById(categoryId);

        if (category != null) {
            // Kiểm tra nếu trạng thái mới của danh mục là 0 và đã được lưu thành công vào cơ sở dữ liệu
            // Thì cập nhật trạng thái của các cuốn sách thuộc danh mục này thành 0
            if (status == 0) {
                category.setName(categoryName);
                category.setStatus(status);
                categoryDAO.updateCategory(category);

                // Cập nhật trạng thái của các cuốn sách thuộc danh mục thành 0
                List<Book> booksInCategory = bookDAO.getBooksByCategory(categoryId);
                for (Book book : booksInCategory) {
                    book.setStatus(status);
                    bookDAO.updateBook(book);
                }
            } else {
                // Nếu trạng thái mới của danh mục không phải là 0, chỉ cập nhật thông tin của danh mục
                category.setName(categoryName);
                category.setStatus(status);
                categoryDAO.updateCategory(category);
            }

            // Hiển thị thông báo cập nhật thành công
            request.setAttribute("message", "Cập nhật thành công");
            request.setAttribute("category", category);
        } else {
            request.setAttribute("message", "Không tìm thấy danh mục");
        }

        // Forward đến trang editcategory.jsp để hiển thị thông báo và thông tin sau khi cập nhật
        request.getRequestDispatcher("jsp/admin/editcategory.jsp").forward(request, response);
    }
}
