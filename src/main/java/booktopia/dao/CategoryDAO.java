package booktopia.dao;

import java.util.List;

import booktopia.entity.Category;

public interface CategoryDAO {

	// Phương thức để lấy danh sách tất cả các danh mục
    List<Category> getAllCategories();

    // Phương thức để thêm một danh mục mới
    void addCategory(Category category);

    // Phương thức để vô hiệu một danh mục theo id
    void disableCategory(int id);
    
    // Phương thức lấy danh mục theo id
    Category getCategoryById(int id);

    // Phương thức lấy tất cả các danh mục còn kinh doanh 
	List<Category> getActiveCategories();

	// Lấy danh mục theo từ khóa
	List<Category> searchCategoriesByKeyword(List<Category> categories, String searchKeyword);

	// Lấy danh mục theo trạng thái
	List<Category> getCategoriesByStatusID(int statusId);

	// Cập nhật danh mục
	void updateCategory(Category category);
}
