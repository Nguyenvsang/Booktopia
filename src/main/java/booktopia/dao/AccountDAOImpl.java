package booktopia.dao;

import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

import booktopia.entity.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import booktopia.utility.JDBCDataSource;

public class AccountDAOImpl implements AccountDAO {

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Account");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setFirstName(rs.getString("first_name"));
                account.setLastName(rs.getString("last_name"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setGender(rs.getString("gender"));
                account.setImg(rs.getString("img"));
                account.setDateOfBirth(rs.getDate("date_of_birth"));
                account.setAddress(rs.getString("address"));
                account.setPhoneNumber(rs.getString("phone_number"));
                account.setEmail(rs.getString("email"));
                account.setAccountType(rs.getInt("account_type"));
                account.setStatus(rs.getInt("status"));
                accounts.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return accounts;
    }

    @Override
    public void addAccount(Account account) {
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Account (first_name, last_name, username, password, gender, img, date_of_birth, address, phone_number, email, account_type, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, account.getFirstName());
            stmt.setString(2, account.getLastName());
            stmt.setString(3, account.getUsername());
            stmt.setString(4, account.getPassword());
            stmt.setString(5, account.getGender());
            stmt.setString(6, account.getImg());
            stmt.setDate(7, new java.sql.Date(account.getDateOfBirth().getTime()));
            stmt.setString(8, account.getAddress());
            stmt.setString(9, account.getPhoneNumber());
            stmt.setString(10, account.getEmail());
            stmt.setInt(11, account.getAccountType());
            stmt.setInt(12, account.getStatus());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    @Override
    public void disableAccount(int id) {
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE Account SET status = 0 WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    @Override
    public void updateAccount(Account account) {
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE Account SET first_name = ?, last_name = ?, username = ?, password = ?, gender = ?, img = ?, date_of_birth = ?, address = ?, phone_number = ?, email = ?, account_type = ?, status = ? WHERE id = ?");
            stmt.setString(1, account.getFirstName());
            stmt.setString(2, account.getLastName());
            stmt.setString(3, account.getUsername());
            stmt.setString(4, account.getPassword());
            stmt.setString(5, account.getGender());
            stmt.setString(6, account.getImg());
            stmt.setDate(7, new java.sql.Date(account.getDateOfBirth().getTime()));
            stmt.setString(8, account.getAddress());
            stmt.setString(9, account.getPhoneNumber());
            stmt.setString(10, account.getEmail());
            stmt.setInt(11, account.getAccountType());
            stmt.setInt(12, account.getStatus());
            stmt.setInt(13, account.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    @Override
    public Account findAccountByUsername(String username) {
        Account account = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Account WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                account = new Account();
                account.setId(rs.getInt("id"));
                account.setFirstName(rs.getString("first_name"));
                account.setLastName(rs.getString("last_name"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setGender(rs.getString("gender"));
                account.setImg(rs.getString("img"));
                account.setDateOfBirth(rs.getDate("date_of_birth"));
                account.setAddress(rs.getString("address"));
                account.setPhoneNumber(rs.getString("phone_number"));
                account.setEmail(rs.getString("email"));
                account.setAccountType(rs.getInt("account_type"));
                account.setStatus(rs.getInt("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return account;
    }

    @Override
    public boolean checkLogin(String username, String password) {
    	Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Account WHERE username = ? AND account_type = 1 AND status = 1");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                // Kiểm tra mật khẩu băm
                return BCrypt.checkpw(password, hashedPassword);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return false;
    }

	@Override
	public boolean checkLoginAdmin(String username, String password) {
		Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Account WHERE username = ? AND account_type = 0 AND status = 1");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                // Kiểm tra mật khẩu băm
                return BCrypt.checkpw(password, hashedPassword);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return false;
	}

	@Override
	public List<Account> getAccountsByStatusID(int statusId) {
		List<Account> accounts = new ArrayList<>();
	    Connection conn = null;
	    try {
	        conn = JDBCDataSource.getConnection();
	        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Account WHERE status = ?");
	        stmt.setInt(1, statusId);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            Account account = new Account();
	            account.setId(rs.getInt("id"));
	            account.setFirstName(rs.getString("first_name"));
	            account.setLastName(rs.getString("last_name"));
	            account.setUsername(rs.getString("username"));
	            account.setPassword(rs.getString("password"));
	            account.setGender(rs.getString("gender"));
	            account.setImg(rs.getString("img"));
	            account.setDateOfBirth(rs.getDate("date_of_birth"));
	            account.setAddress(rs.getString("address"));
	            account.setPhoneNumber(rs.getString("phone_number"));
	            account.setEmail(rs.getString("email"));
	            account.setAccountType(rs.getInt("account_type"));
	            account.setStatus(rs.getInt("status"));
	            accounts.add(account);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCDataSource.closeConnection(conn);
	    }
	    return accounts;
	}

	@Override
	public List<Account> searchAccountsByKeyword(List<Account> accounts, String searchKeyword) {
		List<Account> result = new ArrayList<>();
	    String lowercaseKeyword = searchKeyword.toLowerCase();
	    
	    for (Account account : accounts) {
	    	if (containsIgnoreCase(Integer.toString(account.getId()), lowercaseKeyword)
	    			|| containsIgnoreCase(account.getFirstName(), lowercaseKeyword)
	    			|| containsIgnoreCase(account.getLastName(), lowercaseKeyword)
	    			|| containsIgnoreCase(account.getUsername(), lowercaseKeyword)
	    			|| containsIgnoreCase(account.getPassword(), lowercaseKeyword)
	    			|| containsIgnoreCase(account.getGender(), lowercaseKeyword)
	    			|| containsIgnoreCase(account.getImg(), lowercaseKeyword)
	                || containsIgnoreCase(account.getDateOfBirth().toString(), lowercaseKeyword)
	                || containsIgnoreCase(account.getAddress(), lowercaseKeyword)
	                || containsIgnoreCase(account.getPhoneNumber(), lowercaseKeyword)
	                || containsIgnoreCase(account.getEmail(), lowercaseKeyword)
	                || containsIgnoreCase(Integer.toString(account.getAccountType()), lowercaseKeyword)
	                || containsIgnoreCase(Integer.toString(account.getStatus()), lowercaseKeyword)) {
	            result.add(account);
	        }
	    }
	    
	    return result;
	}
	
	// Kiểm tra xem một chuỗi có chứa một chuỗi con cụ thể hay không,
	// mà không phân biệt chữ hoa chữ thường trong quá trình so sánh
	private boolean containsIgnoreCase(String text, String keyword) {
	    return text.toLowerCase().contains(keyword);
	}

	@Override
	public Account findAccountById(int id) {
		Account account = null;
	    Connection conn = null;
	    try {
	        conn = JDBCDataSource.getConnection();
	        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Account WHERE id = ?");
	        stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            account = new Account();
	            account.setId(rs.getInt("id"));
	            account.setFirstName(rs.getString("first_name"));
	            account.setLastName(rs.getString("last_name"));
	            account.setUsername(rs.getString("username"));
	            account.setPassword(rs.getString("password"));
	            account.setGender(rs.getString("gender"));
	            account.setImg(rs.getString("img"));
	            account.setDateOfBirth(rs.getDate("date_of_birth"));
	            account.setAddress(rs.getString("address"));
	            account.setPhoneNumber(rs.getString("phone_number"));
	            account.setEmail(rs.getString("email"));
	            account.setAccountType(rs.getInt("account_type"));
	            account.setStatus(rs.getInt("status"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCDataSource.closeConnection(conn);
	    }
	    return account;
	}
}
