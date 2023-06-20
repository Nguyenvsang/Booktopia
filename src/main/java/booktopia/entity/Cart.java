package booktopia.entity;

public class Cart {

	private int id;
	private int accountId;
	
	public Cart() {
		super();
	}

	public Cart(int accountId) {
		super();
		this.accountId = accountId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int account_id) {
		this.accountId = account_id;
	}
	
	
}
