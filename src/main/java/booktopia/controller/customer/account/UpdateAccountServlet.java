package booktopia.controller.customer.account;

import java.io.ByteArrayOutputStream;
import java.util.Map;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

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
import booktopia.dao.AccountDAO;
import booktopia.dao.AccountDAOImpl;

@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/updateaccount"})
public class UpdateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AccountDAO accountDao;
    private Cloudinary cloudinary;

    @Override
    public void init() throws ServletException {
        super.init();
        accountDao = new AccountDAOImpl();

        // Khởi tạo đối tượng Cloudinary với các thông tin xác thực của bạn
        cloudinary = new Cloudinary(ObjectUtils.asMap(
        		"cloud_name", "dosdzo1lg",
        		  "api_key", "471512853679338",
        		  "api_secret", "tmvGJJ8g7bRQKvhSNDXZRvD4WDI"
        ));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        // Kiểm tra xem người dùng đã đăng nhập hay chưa
        if (account == null) {
            // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
            response.sendRedirect("jsp/customer/loginaccount.jsp");
            return;
        }

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
                    if (fieldName.equals("firstName")) {
                        account.setFirstName(value);
                    } else if (fieldName.equals("lastName")) {
                        account.setLastName(value);
                    } else if (fieldName.equals("username")) {
                        account.setUsername(value);
                    } else if (fieldName.equals("gender")) {
                        account.setGender(value);
                    } else if (fieldName.equals("dob")) {
                        Date dateOfBirth = Date.valueOf(value);
                        account.setDateOfBirth(dateOfBirth);
                    } else if (fieldName.equals("address")) {
                        account.setAddress(value);
                    } else if (fieldName.equals("phoneNumber")) {
                        account.setPhoneNumber(value);
                    } else if (fieldName.equals("email")) {
                        account.setEmail(value);
                    }
                } else {
                	// Nếu là tệp được tải lên
                	String fileName = item.getName();
                	if (fileName != null && !fileName.isEmpty()) {
                	    // Tạo public ID cho hình ảnh trên Cloudinary (sử dụng tên người dùng)
                	    String publicId = "Booktopia/img_account/" + account.getUsername();

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

                	    // Cập nhật URL hình ảnh vào tài khoản
                	    account.setImg(imageUrl);
                	}
                }
            }
            // Cập nhật tài khoản trong cơ sở dữ liệu
            accountDao.updateAccount(account);

            // Lưu thông tin tài khoản mới vào session
            session.setAttribute("account", account);

            // Forward đến trang xem thông tin tài khoản
            request.setAttribute("message", "Đã cập nhật thành công!");
            request.getRequestDispatcher("jsp/customer/viewaccount.jsp").forward(request, response);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
}
