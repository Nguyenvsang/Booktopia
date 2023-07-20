package booktopia.controller.admin.book;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import booktopia.entity.Account;
import booktopia.entity.Book;
import booktopia.entity.Category;
import booktopia.dao.BookDAO;
import booktopia.dao.BookDAOImpl;
import booktopia.dao.CategoryDAO;
import booktopia.dao.CategoryDAOImpl;

@WebServlet(name = "UpdateBookServlet", urlPatterns = {"/updatebook"})
public class UpdateBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private BookDAO bookDAO;
    private CategoryDAO categoryDAO;
    private Cloudinary cloudinary;

    @Override
    public void init() throws ServletException {
        super.init();

        bookDAO = new BookDAOImpl();
        categoryDAO = new CategoryDAOImpl();
        // Khởi tạo đối tượng Cloudinary với các thông tin xác thực của bạn
        cloudinary = new Cloudinary(ObjectUtils.asMap(
        		"cloud_name", "dosdzo1lg",
        		  "api_key", "471512853679338",
        		  "api_secret", "tmvGJJ8g7bRQKvhSNDXZRvD4WDI"
        ));
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
    	
    	// Lấy thông tin sách từ request parameters
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        Book book = bookDAO.getBookById(bookId);

        // Set thuộc tính sách, danh mục trong request attribute để sử dụng trong JSP
        request.setAttribute("book", book);
        List<Category> categories = categoryDAO.getActiveCategories();
        request.setAttribute("categories", categories);

        // Forward request và response đến trang updatebook.jsp
        request.getRequestDispatcher("jsp/admin/updatebook.jsp").forward(request, response);
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
        
        // Tạo đối tương sách để lưu dữ liệu cần cập nhật
        Book book = new Book();

        // Tạo đối tượng ServletFileUpload để truy xuất các trường dữ liệu và tệp được tải lên từ form
        ServletFileUpload upload = new ServletFileUpload();

        try {
            // Lấy danh sách các trường dữ liệu và tệp được tải lên từ form
            FileItemIterator iter = upload.getItemIterator(request);
            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                String fieldName = item.getFieldName();
                InputStream stream = item.openStream();
                if (item.isFormField()) {
                    // Nếu là trường dữ liệu thông thường
                    String value = Streams.asString(stream, "UTF-8");
                    if (fieldName.equals("id")) {
                    	int id = Integer.parseInt(value);
                        book.setId(id);
                    } else if (fieldName.equals("name")) {
                    	book.setName(value);
                    } else if (fieldName.equals("author")) {
                    	book.setAuthor(value);
                    } else if (fieldName.equals("price")) {
                    	double price = Double.parseDouble(value);
                        book.setPrice(price);
                    } else if (fieldName.equals("img")) {
                    	book.setImg(value);
                    } else if (fieldName.equals("categoryId")) {
                    	int categoryId = Integer.parseInt(value);
                        book.setCategoryId(categoryId);
                    } else if (fieldName.equals("description")) {
                    	//Xử lý việc chữ xuống dòng
                    	book.setDescription(value);
                    } else if (fieldName.equals("status")) {
                    	int status = Integer.parseInt(value);
                        book.setStatus(status);
                    } else if (fieldName.equals("detail")) {
                    	book.setDetail(value);
                    } else if (fieldName.equals("quantity")) {
                    	int quantity = Integer.parseInt(value);
                        book.setQuantity(quantity);
                    }
                } else {
                	// Nếu là tệp được tải lên
                	String fileName = item.getName();
                	if (fileName != null && !fileName.isEmpty()) {
                	    // Tạo public ID cho hình ảnh trên Cloudinary (sử dụng id sách)
                	    String publicId = "Booktopia/img_book/book_" + book.getId();

                	    // Đọc dữ liệu từ InputStream và lưu vào một mảng byte
                	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                	    IOUtils.copy(stream, outputStream);
                	    byte[] imageData = outputStream.toByteArray();

                	    // Tải lên hình ảnh lên Cloudinary
                	    @SuppressWarnings("unchecked")
                	    Map<String, Object> uploadResult = (Map<String, Object>) cloudinary.uploader().upload(imageData, ObjectUtils.asMap(
                	        "public_id", publicId
                	    ));

                	    // Lấy URL của hình ảnh từ kết quả tải lên
                	    String imageUrl = (String) uploadResult.get("secure_url");

                	    // Cập nhật URL hình ảnh vào sách
                	    book.setImg(imageUrl);
                	}
                }
            }
            
            // Kiểm tra trường hợp số lượng bằng 0 sẽ đưa về ngừng kinh doanh
            if (book.getQuantity() == 0) {
            	book.setStatus(0);
            }
            
            // Cập nhật sách trong cơ sở dữ liệu
            bookDAO.updateBook(book);

            // Lưu thông tin sách mới vào session
            session.setAttribute("book", book);
            List<Category> categories = categoryDAO.getActiveCategories();
            request.setAttribute("categories", categories);

            // Forward đến trang xem thông tin tài khoản
            request.setAttribute("message", "Đã cập nhật thành công!");
            request.getRequestDispatcher("jsp/admin/updatebook.jsp").forward(request, response);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
}
