package booktopia.controller.anyone.book;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import booktopia.entity.Book;
import booktopia.entity.Category;
import booktopia.dao.BookDAO;
import booktopia.dao.BookDAOImpl;
import booktopia.dao.CategoryDAO;
import booktopia.dao.CategoryDAOImpl;

@WebServlet(name = "ViewActiveBooksServlet", urlPatterns = {"/viewbooks"})
public class ViewActiveBooksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;
    private CategoryDAO categoryDAO;
    public void init() {
        bookDAO = new BookDAOImpl();
        categoryDAO = new CategoryDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy danh mục từ URL parameter
        int categoryId = -1;
        String categoryParam = request.getParameter("category");
        if (categoryParam != null && !categoryParam.isEmpty()) {
            categoryId = Integer.parseInt(categoryParam);
        }

        // Lấy danh sách sách còn kinh doanh theo danh mục hoặc tất cả sách còn kinh doanh
        List<Book> books;
        if (categoryId == -1) {
            books = bookDAO.getActiveBooks();
        } else {
            books = bookDAO.getActiveBooksByCategory(categoryId);
        }
        
        // Lấy từ khóa tìm kiếm từ URL parameter
        String searchKeyword = request.getParameter("search");
        
        // Kiểm tra xem có từ khóa tìm kiếm hay không
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            // Tìm kiếm sách theo từ khóa
            books = bookDAO.searchBooksByKeyword(books, searchKeyword);
        }

        // Lấy tổng số lượng sách
        int totalBooks = books.size();

        // Lấy trang hiện tại từ URL parameter
        int currentPage = 1;
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        // Số sách hiển thị trên mỗi trang
        int recordsPerPage = 10;

        // Tính toán vị trí bắt đầu và kết thúc của sách trên trang hiện tại
        int start = (currentPage - 1) * recordsPerPage;
        int end = Math.min(start + recordsPerPage, totalBooks);

        // Lấy danh sách sách trên trang hiện tại
        List<Book> booksOnPage = books.subList(start, end);

        // Tính toán số trang
        int totalPages = (int) Math.ceil((double) totalBooks / recordsPerPage);

        // Đặt thuộc tính vào request để sử dụng trong JSP
        request.setAttribute("books", booksOnPage);
        request.setAttribute("totalBooks", totalBooks);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        List<Category> categories = categoryDAO.getActiveCategories();
        request.setAttribute("categories", categories);

        // Forward đến trang danh sách sách theo danh mục
        request.getRequestDispatcher("jsp/viewbooks.jsp").forward(request, response);
    }
}
