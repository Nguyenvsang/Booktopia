package booktopia.entity;

public class Book {

    private int id;
    private String name;
    private String author;
    private double price;
    private int categoryId;
    private String img;
    private String description; // mô tả sơ lược
    private int status; //hoạt động:1, ngừng kd:0
    private String detail; // chi tiết nội dung 
    private int quantity;

    public Book() {
    }

    public Book(String name, String author, double price, int categoryId, String img, String description, int status, String detail, int quantity) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.categoryId = categoryId;
        this.img = img;
        this.description = description;
        this.status = status;
        this.detail = detail;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", categoryId=" + categoryId +
                ", img='" + img + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", detail='" + detail + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}

