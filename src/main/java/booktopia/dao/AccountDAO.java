package booktopia.dao;

import java.util.List;

import booktopia.entity.Account;

public interface AccountDAO {

	// Phương thức để lấy danh sách tất cả các tài khoản
	List<Account> getAllAccounts();
	// Phương thức để thêm một tài khoản mới
	void addAccount(Account account);

	// Phương thức để vô hiệu hóa một tài khoản theo id
	void disableAccount(int id);

	// Phương thức để cập nhật thông tin của một tài khoản
	void updateAccount(Account account);

	// Phương thức để tìm kiếm một tài khoản theo username
	Account findAccountByUsername(String username);

	// Phương thức để kiểm tra đăng nhập của một tài khoản
	boolean checkLogin(String username, String password);
	
	// Phương thức để kiểm tra đăng nhập của một tài khoản admin 
	boolean checkLoginAdmin(String username, String password);
	
}
