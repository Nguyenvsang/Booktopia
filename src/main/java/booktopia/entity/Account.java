package booktopia.entity;

import java.util.Date;

public class Account {

    private int id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String gender;
 
    private String img;

    private Date dateOfBirth;

    private String address;
  
    private String phoneNumber;
 
    private int accountType; //customer:1, admin:0

    private int status; //hoạt động:1, ngừng hd:0

    // Các phương thức getter, setter, constructor, toString...
    
    public Account() {
        // default constructor
    }

	public Account(String username, String password, String address, String phoneNumber, int accountType, int status) {
		super();
		this.username = username;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.accountType = accountType;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
