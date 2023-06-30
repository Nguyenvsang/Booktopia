package booktopia.entity;
import java.util.Date;

public class Order {

	private int id;
	
	private Date dateOrder; 
	
	private double totalPrice;
	
	// Tuy la mot nguoi dung nhung ho co quyen dung nhieu email, sdt 
	
	private String name;
	
	private String address;
	  
    private String phoneNumber;
    
    private String email;
    
    private int accountId;

	public Order() {
		super();
	}

	public Order(Date dateOrder, double totalPrice, String name, String address, String phoneNumber, String email,
			int accountId) {
		super();
		this.dateOrder = dateOrder;
		this.totalPrice = totalPrice;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.accountId = accountId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
    
    
}
