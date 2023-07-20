package booktopia.controller.admin.book;

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

@WebServlet(name = "ManageDetailBookServlet", urlPatterns = {"/managedetailbook"})
public class ManageDetailBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private BookDAO bookDao; 
	private CategoryDAO categoryDAO;

    public void init() {
        bookDao = new BookDAOImpl(); 
        categoryDAO = new CategoryDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
        Account admin = (Account) session.getAttribute("admin");

        // Kiểm tra xem admin đã đăng nhập hay chưa
        if (admin == null) {
            // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
            response.sendRedirect("jsp/admin/loginadmin.jsp");
            return;
        }
    	
    	// Lấy thông tin về cuốn sách từ yêu cầu (thông qua tham số truyền)
        String bookId = request.getParameter("bookId");

        // Truy xuất dữ liệu từ nguồn dữ liệu 
        Book book = bookDao.getBookById(Integer.parseInt(bookId));

        // Đặt thuộc tính vào request để sử dụng trong JSP
        request.setAttribute("book", book);
        List<Category> categories = categoryDAO.getActiveCategories();
        request.setAttribute("categories", categories);
        
        // Forward đến trang chi tiết sách
        request.getRequestDispatcher("jsp/admin/managedetailbook.jsp").forward(request, response);
    }
}
