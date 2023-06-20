package booktopia.controller.anyone.book;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import booktopia.dao.BookDAO;
import booktopia.dao.BookDAOImpl;
import booktopia.dao.CategoryDAO;
import booktopia.dao.CategoryDAOImpl;
import booktopia.entity.Book;
import booktopia.entity.Category;

@WebServlet(name = "ViewDetailBookServlet", urlPatterns = {"/detailbook"})
public class ViewDetailBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private BookDAO bookDao; 
	private CategoryDAO categoryDAO;

    public void init() {
        bookDao = new BookDAOImpl(); 
        categoryDAO = new CategoryDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin về cuốn sách từ yêu cầu (thông qua tham số truyền)
        String bookId = request.getParameter("bookId");
        // Lấy thông tin thông báo khi thêm sản phẩm vào giỏ hàng
        String message = request.getParameter("message");

        // Truy xuất dữ liệu từ nguồn dữ liệu 
        Book book = bookDao.getBookById(Integer.parseInt(bookId));

        // Đặt thuộc tính vào request để sử dụng trong JSP
        request.setAttribute("book", book);
        List<Category> categories = categoryDAO.getActiveCategories();
        request.setAttribute("categories", categories);
        request.setAttribute("message", message);
        
        // Forward đến trang chi tiết sách
        request.getRequestDispatcher("jsp/detailbook.jsp").forward(request, response);
    }
}