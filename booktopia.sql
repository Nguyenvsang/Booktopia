-- Tạo cơ sở dữ liệu booktopia
CREATE DATABASE booktopia;

-- Sử dụng cơ sở dữ liệu booktopia
USE booktopia;

-- Tạo bảng category
CREATE TABLE Category (
  -- Khai báo thuộc tính id kiểu int, không được null, tăng tự động và là khóa chính
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  -- Khai báo thuộc tính name kiểu varchar(255), không được null
  name varchar(255) NOT NULL,
  -- Khai báo thuộc tính status kiểu int, không được null
  status INT NOT NULL
);

-- Thêm dữ liệu vào bảng category
INSERT INTO Category (name, status) VALUES ("Chưa xác định", 1), ("Xuân", 1), ("Hạ", 1), ("Thu", 1), ("Đông", 1);

-- Tạo bảng book
CREATE TABLE Book (
  -- Khai báo thuộc tính id kiểu int, không được null, tăng tự động và là khóa chính
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  -- Khai báo thuộc tính name kiểu varchar(255), không được null
  name varchar(255) NOT NULL,
  -- Khai báo thuộc tính author kiểu varchar(255), không được null
  author varchar(255) NOT NULL,
  -- Khai báo thuộc tính price kiểu double, không được null
  price double NOT NULL,
  -- Khai báo thuộc tính category_id kiểu int và là khóa ngoại tham chiếu đến bảng Category
  category_id int,
  FOREIGN KEY (category_id) REFERENCES Category(id),
  -- Khai báo thuộc tính img kiểu char(255)
  img char(255),
  -- Khai báo thuộc tính description kiểu text, có thể null
  description text,
  -- Khai báo thuộc tính status kiểu int, không được null
  status INT NOT NULL,
  -- Khai báo thuộc tính detail kiểu text, có thể null
  detail text,
  -- Khai báo thuộc tính quantity kiểu int, không được null
  quantity INT NOT NULL
);

INSERT INTO Book(name, author, price, category_id, img, description, status, detail, quantity)
VALUES ("Hè 2023", "Nguyễn Văn Sang", 2000, 3, "https://res.cloudinary.com/dosdzo1lg/image/upload/v1685797301/Booktopia/img_book/book_1.jpg", "Và lòng mình vẫn thèm muốn thu trọn vẹn mùa hè\nNhưng không ghi lại những gì mỗi sáng", 1, "Và lòng mình vẫn thèm muốn thu trọn vẹn mùa hè\nNhưng không ghi lại những gì mỗi sáng\nHoa phượng nở đỏ từ trên cành và đỏ trên lối lát gạch\nNhớ về lần lụm hoa phượng cũng đem về ép vào tập, không nhớ là lúc nào\nĐi một đoạn tiếng ve bắt đầu vang lên\nNhớ về mùa hè năm nhất đại học vào tháng bốn và đọc lên vài câu thơ mùa xuân\nNhớ về mùa hè khi mười sáu, mùa hè của lớp 11, mùa hè học Hóa học Hoàng Gia Úc, coi Chạy đi chờ chi, đi Vũng Tàu với các bạn, đi Địa đạo Củ Chi, đi xem phim.\nCó một con ve ướt cánh không bay được, cất tiếng kêu ve ve, lấy nó vào chụp ảnh rồi thả nó bay đi.\nMùa hè năm nay thật sự giữa chúng mình có đang vỡ tan.\nNắng rất nắng\nVà có vài đợt mưa ở thành phố không quá dài không đủ để vui chăng\nNhớ lại vụ đợi mưa hồi năm nhất", 100);
INSERT INTO Book(name, author, price, category_id, img, description, status, detail, quantity)
VALUES ("Chiều trong tôi 1 2023", "Nguyễn Văn Sang", 2000, 3, "https://res.cloudinary.com/dosdzo1lg/image/upload/v1686319023/Booktopia/img_book/book_2.jpg", "Những con diều trong buổi chiều xanh\nBầu trời nắng dần dần vào hạ", 1, "Những con diều trong buổi chiều xanh\nBầu trời nắng dần dần vào hạ\nMong là chúng mình không xa lạ\nTự nhiên hoa nở một góc vàng", 90);
INSERT INTO Book(name, author, price, category_id, img, description, status, detail, quantity)
VALUES ("Yêu lại mùa xuân", "Nguyễn Văn Sang", 2000, 1, "https://res.cloudinary.com/dosdzo1lg/image/upload/v1686367048/Booktopia/img_book/book_3.jpg", "Tự dưng mình gặp lại mình, mình nhắc nhở mình rằng tháng mười hai rồi đó\nLần này mình quyết định không để lòng mình quên mất", 1, 'Cuối năm viết thơ:\nYêu lại mùa xuân:\nNhững chờ đợi vào cuối năm\n"Tình trong mười chín khép lại đêm nay…"\nVà tình hai mươi ấy chưa bao giờ khép lại nhanh đến thế!\nNhững ngày đông chớp tắt lạnh\nNhững cơn mưa đêm chớp tắt sáng\nNhững trưa nắng chang chang\nVào những ngày như thế là những ngày cuối năm?\nTrên trường hình quảng cáo xuân vừa có\nVài thứ nhạc xưa không còn cất trong xó\nĐôi ba đợt giảm giá để kết lại một năm\nTự dưng mình gặp lại mình, mình nhắc nhở mình rằng tháng mười hai rồi đó\nLần này mình quyết định không để lòng mình quên mất\nMình nhắc nhở mình nhấm nháp những gì cuối hai mươi', 80);
INSERT INTO Book(name, author, price, category_id, img, description, status, detail, quantity)
VALUES ("Ngày mới", "Nguyễn Văn Sang", 2000, 2, "https://res.cloudinary.com/dosdzo1lg/image/upload/v1685797301/Booktopia/img_book/book_4.jpg", "Em ngủ quên hay đang say trong ngày mới\nVừa mở mắt thì lại choáng ngợp trong mơ!", 1, "Em ngủ quên hay đang say trong ngày mới\nVừa mở mắt thì lại choáng ngợp trong mơ!\nTiếng chim hót từ từ\nTiếng chuẩn bị buổi sáng vội vã\nNắng lên… nhanh và nhanh hơn nữa\nNắng thấy em đang giả thức chờ đông\nKhông à?\nEm đâu có chờ đông\nVì mùa xuân sắp đến\nNhững lá cây rung rinh cố giữ lại từng đợt gió cuối mùa\nNhững hòn đá có vẻ như cũng mới, bớt cộc lốc\nMũi của ta có vẻ hơi khó chịu, hít nhiều mùi hương mà có giữ lại bao giờ\nMiệng của mình đôi lúc khô lại, rồi ngủ say\nNgày mới của cuối hai mươi thật thanh bình, nắng lên sớm, lao thật nhanh vào không gian, rồi dịu đi hẳn; nắng cứ sáng lên rồi mờ xuống tưởng như cơn gió làm phai màu nắng theo; mình hít thở sâu hơn và yêu cái không gian yên tĩnh này", 70);
INSERT INTO Book(name, author, price, category_id, img, description, status, detail, quantity)
VALUES ("Chuẩn bị ngủ", "Nguyễn Văn Sang", 2000, 5, "https://res.cloudinary.com/dosdzo1lg/image/upload/v1685797301/Booktopia/img_book/book_5.jpg", "Tớ có quyền bỏ quên trái đất này\nchỉ khi tớ chìm vào giấc ngủ thật sâu", 1, 'Những đêm đầu năm\nTa nhắc nhở mình ngủ cho thật sớm\nMà... cuối năm rồi\nGiấc ngủ ấy vẫn chuẩn bị mà thôi\nTớ có quyền bỏ quên trái đất này\nchỉ khi tớ chìm vào giấc ngủ thật sâu\nVà thật lâu\nMình vỗ về cuối năm bằng những cái "chúc ngủ ngon nhé!"', 100);
INSERT INTO Book(name, author, price, category_id, img, description, status, detail, quantity)
VALUES ("Những bất ngờ cuối năm", "Nguyễn Văn Sang", 2000, 5, "https://res.cloudinary.com/dosdzo1lg/image/upload/v1686319023/Booktopia/img_book/book_6.jpg", "Tự dưng, đôi lúc, gặp lại, làm mình chơi vơi quá", 1, "Tự dưng, đôi lúc, gặp lại, làm mình chơi vơi quá\nThấy cũng đau cũng nghĩ ngợi vài điều\nMuốn ghét lắm nhưng mà sao phải ghét\nVì đôi ba dòng tin nhắn\nVì một dòng bất ngờ\nCũng làm mình cười tươi rói luôn\nThân thương", 90);
INSERT INTO Book(name, author, price, category_id, img, description, status, detail, quantity)
VALUES ("Yêu lại mùa xuân", "Nguyễn Văn Sang", 2000, 1, "https://res.cloudinary.com/dosdzo1lg/image/upload/v1686367048/Booktopia/img_book/book_3.jpg", "Tự dưng mình gặp lại mình, mình nhắc nhở mình rằng tháng mười hai rồi đó\nLần này mình quyết định không để lòng mình quên mất", 1, 'Cuối năm viết thơ:\nYêu lại mùa xuân:\nNhững chờ đợi vào cuối năm\n"Tình trong mười chín khép lại đêm nay…"\nVà tình hai mươi ấy chưa bao giờ khép lại nhanh đến thế!\nNhững ngày đông chớp tắt lạnh\nNhững cơn mưa đêm chớp tắt sáng\nNhững trưa nắng chang chang\nVào những ngày như thế là những ngày cuối năm?\nTrên trường hình quảng cáo xuân vừa có\nVài thứ nhạc xưa không còn cất trong xó\nĐôi ba đợt giảm giá để kết lại một năm\nTự dưng mình gặp lại mình, mình nhắc nhở mình rằng tháng mười hai rồi đó\nLần này mình quyết định không để lòng mình quên mất\nMình nhắc nhở mình nhấm nháp những gì cuối hai mươi', 80);
INSERT INTO Book(name, author, price, category_id, img, description, status, detail, quantity)
VALUES ("Ngày mới", "Nguyễn Văn Sang", 2000, 2, "https://res.cloudinary.com/dosdzo1lg/image/upload/v1685797301/Booktopia/img_book/book_4.jpg", "Em ngủ quên hay đang say trong ngày mới\nVừa mở mắt thì lại choáng ngợp trong mơ!", 1, "Em ngủ quên hay đang say trong ngày mới\nVừa mở mắt thì lại choáng ngợp trong mơ!\nTiếng chim hót từ từ\nTiếng chuẩn bị buổi sáng vội vã\nNắng lên… nhanh và nhanh hơn nữa\nNắng thấy em đang giả thức chờ đông\nKhông à?\nEm đâu có chờ đông\nVì mùa xuân sắp đến\nNhững lá cây rung rinh cố giữ lại từng đợt gió cuối mùa\nNhững hòn đá có vẻ như cũng mới, bớt cộc lốc\nMũi của ta có vẻ hơi khó chịu, hít nhiều mùi hương mà có giữ lại bao giờ\nMiệng của mình đôi lúc khô lại, rồi ngủ say\nNgày mới của cuối hai mươi thật thanh bình, nắng lên sớm, lao thật nhanh vào không gian, rồi dịu đi hẳn; nắng cứ sáng lên rồi mờ xuống tưởng như cơn gió làm phai màu nắng theo; mình hít thở sâu hơn và yêu cái không gian yên tĩnh này", 70);
INSERT INTO Book(name, author, price, category_id, img, description, status, detail, quantity)
VALUES ("Hè 2023", "Nguyễn Văn Sang", 2000, 3, "https://res.cloudinary.com/dosdzo1lg/image/upload/v1685797301/Booktopia/img_book/book_1.jpg", "Và lòng mình vẫn thèm muốn thu trọn vẹn mùa hè\nNhưng không ghi lại những gì mỗi sáng", 1, "Và lòng mình vẫn thèm muốn thu trọn vẹn mùa hè\nNhưng không ghi lại những gì mỗi sáng\nHoa phượng nở đỏ từ trên cành và đỏ trên lối lát gạch\nNhớ về lần lụm hoa phượng cũng đem về ép vào tập, không nhớ là lúc nào\nĐi một đoạn tiếng ve bắt đầu vang lên\nNhớ về mùa hè năm nhất đại học vào tháng bốn và đọc lên vài câu thơ mùa xuân\nNhớ về mùa hè khi mười sáu, mùa hè của lớp 11, mùa hè học Hóa học Hoàng Gia Úc, coi Chạy đi chờ chi, đi Vũng Tàu với các bạn, đi Địa đạo Củ Chi, đi xem phim.\nCó một con ve ướt cánh không bay được, cất tiếng kêu ve ve, lấy nó vào chụp ảnh rồi thả nó bay đi.\nMùa hè năm nay thật sự giữa chúng mình có đang vỡ tan.\nNắng rất nắng\nVà có vài đợt mưa ở thành phố không quá dài không đủ để vui chăng\nNhớ lại vụ đợi mưa hồi năm nhất", 100);
INSERT INTO Book(name, author, price, category_id, img, description, status, detail, quantity)
VALUES ("Chiều trong tôi 1 2023", "Nguyễn Văn Sang", 2000, 3, "https://res.cloudinary.com/dosdzo1lg/image/upload/v1686319023/Booktopia/img_book/book_2.jpg", "Những con diều trong buổi chiều xanh\nBầu trời nắng dần dần vào hạ", 1, "Những con diều trong buổi chiều xanh\nBầu trời nắng dần dần vào hạ\nMong là chúng mình không xa lạ\nTự nhiên hoa nở một góc vàng", 90);
INSERT INTO Book(name, author, price, category_id, img, description, status, detail, quantity)
VALUES ("Yêu lại mùa xuân", "Nguyễn Văn Sang", 2000, 1, "https://res.cloudinary.com/dosdzo1lg/image/upload/v1686367048/Booktopia/img_book/book_3.jpg", "Tự dưng mình gặp lại mình, mình nhắc nhở mình rằng tháng mười hai rồi đó\nLần này mình quyết định không để lòng mình quên mất", 1, 'Cuối năm viết thơ:\nYêu lại mùa xuân:\nNhững chờ đợi vào cuối năm\n"Tình trong mười chín khép lại đêm nay…"\nVà tình hai mươi ấy chưa bao giờ khép lại nhanh đến thế!\nNhững ngày đông chớp tắt lạnh\nNhững cơn mưa đêm chớp tắt sáng\nNhững trưa nắng chang chang\nVào những ngày như thế là những ngày cuối năm?\nTrên trường hình quảng cáo xuân vừa có\nVài thứ nhạc xưa không còn cất trong xó\nĐôi ba đợt giảm giá để kết lại một năm\nTự dưng mình gặp lại mình, mình nhắc nhở mình rằng tháng mười hai rồi đó\nLần này mình quyết định không để lòng mình quên mất\nMình nhắc nhở mình nhấm nháp những gì cuối hai mươi', 80);
INSERT INTO Book(name, author, price, category_id, img, description, status, detail, quantity)
VALUES ("Ngày mới", "Nguyễn Văn Sang", 2000, 2, "https://res.cloudinary.com/dosdzo1lg/image/upload/v1685797301/Booktopia/img_book/book_4.jpg", "Em ngủ quên hay đang say trong ngày mới\nVừa mở mắt thì lại choáng ngợp trong mơ!", 1, "Em ngủ quên hay đang say trong ngày mới\nVừa mở mắt thì lại choáng ngợp trong mơ!\nTiếng chim hót từ từ\nTiếng chuẩn bị buổi sáng vội vã\nNắng lên… nhanh và nhanh hơn nữa\nNắng thấy em đang giả thức chờ đông\nKhông à?\nEm đâu có chờ đông\nVì mùa xuân sắp đến\nNhững lá cây rung rinh cố giữ lại từng đợt gió cuối mùa\nNhững hòn đá có vẻ như cũng mới, bớt cộc lốc\nMũi của ta có vẻ hơi khó chịu, hít nhiều mùi hương mà có giữ lại bao giờ\nMiệng của mình đôi lúc khô lại, rồi ngủ say\nNgày mới của cuối hai mươi thật thanh bình, nắng lên sớm, lao thật nhanh vào không gian, rồi dịu đi hẳn; nắng cứ sáng lên rồi mờ xuống tưởng như cơn gió làm phai màu nắng theo; mình hít thở sâu hơn và yêu cái không gian yên tĩnh này", 70);

-- Tạo bảng account
CREATE TABLE Account (
  -- Khai báo thuộc tính id kiểu int, không được null, tăng tự động và là khóa chính
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  -- Khai báo thuộc tính first_name kiểu varchar(255)
  first_name varchar(255),
  -- Khai báo thuộc tính last_name kiểu varchar(255)
  last_name varchar(255),
  -- Khai báo thuộc tính username kiểu varchar(255), không được null
  username varchar(255) NOT NULL,
  -- Khai báo thuộc tính password kiểu varchar(255), không được null
  password varchar(255) NOT NULL,
  -- Khai báo thuộc tính gender kiểu varchar(255)
  gender varchar(255),
  -- Khai báo thuộc tính img kiểu char(255)
  img char(255),
  -- Khai báo thuộc tính date_of_birth kiểu date
  date_of_birth DATE,
  -- Khai báo thuộc tính address kiểu varchar(255), không được null
  address VARCHAR(255) NOT NULL,
  -- Khai báo thuộc tính phone_number kiểu varchar(255), không được null
  phone_number varchar(255) NOT NULL,
  -- Khai báo thuộc tính email kiểu char(255), không được null
  email char(255) NOT NULL,
  -- Khai báo thuộc tính account_type kiểu int, không được null
  account_type INT NOT NULL,
  -- Khai báo thuộc tính status kiểu int, không được null
  status INT NOT NULL
);

-- admin 
INSERT INTO Account(first_name, last_name, username, password, gender, img, date_of_birth, address, phone_number, email, account_type, status)
VALUES ("Minh", "Phạm", "phamthiminh", "$2a$10$lUfNHigfLAxtdO5TeYKf1ezzqdTquqtIL/HkdyD4LU0wlixIfpAZG", "Nữ", "", "2002-03-03", "Vũng Tàu", "0388394909", "minh09@gmail.com", 0, 1);
INSERT INTO Account(first_name, last_name, username, password, gender, img, date_of_birth, address, phone_number, email, account_type, status)
VALUES ("Mai", "Lâm", "lamngocmai", "$2a$10$ecLWKUctvRxMJoZLWHiwyuH8tGP7LFTrNEDoTiNsvkJOsAOZJh6eG", "Nữ", "", "2002-04-13", "Trà Vinh", "057399379", "mai79@gmail.com", 0, 1);
-- customer 
INSERT INTO Account(first_name, last_name, username, password, gender, img, date_of_birth, address, phone_number, email, account_type, status)
VALUES ("A", "Nguyễn", "nguyenvana", "$2a$10$2e.U.VeBZI9AI9M4HLpHeevfHaBn5fLHBILe6TIpTAORGKV/fa9wq", "Nam", "", "2002-06-10", "TP.HCM", "0388156408", "a08@gmail.com", 1, 1);
INSERT INTO Account(first_name, last_name, username, password, gender, img, date_of_birth, address, phone_number, email, account_type, status)
VALUES ("Sang", "Nguyễn", "nguyenvansang", "$2a$10$EOAYipGx4YVsEPCzLxXgFu/rlFA5R93lp6DPwjssLj.OGFYRKENHm", "Nam", "", "2002-09-10", "An Giang", "0288199567", "sang67@gmail.com", 1, 1);
INSERT INTO Account(first_name, last_name, username, password, gender, img, date_of_birth, address, phone_number, email, account_type, status)
VALUES ("Hạ", "Trần", "tranthiha", "$2a$10$fbmKZXKvk4APdj4p6becc.RHSG1bIKpjsNyQl6ml.OEx3.zfWqGL.", "Nữ", "", "2003-10-10", "Bến Tre", "0788467389", "ha89@gmail.com", 1, 1);
INSERT INTO Account(first_name, last_name, username, password, gender, img, date_of_birth, address, phone_number, email, account_type, status)
VALUES ("Tươi", "Trần", "tranvantuoi", "$2a$10$i30yFjhiZywZbfshpgiIA.MoDHert9OvSD7ZKXcqTvDO28AxZFh2y", "Nam", "", "2000-02-07", "Quảng Ngãi", "0977767587", "tuoi87@gmail.com", 1, 1);
INSERT INTO Account(first_name, last_name, username, password, gender, img, date_of_birth, address, phone_number, email, account_type, status)
VALUES ("Hoa", "Phan", "phanthihoa", "$2a$10$4cU9CFgSw1.pbTB/GbODUuzlGQLOGB2/O58Yyi1nygMEzWXDdNyEW", "Nữ", "", "1999-09-09", "Bắc Ninh", "0578695748", "hoa48@gmail.com", 1, 1);
INSERT INTO Account(first_name, last_name, username, password, gender, img, date_of_birth, address, phone_number, email, account_type, status)
VALUES ("Anh", "Nguyễn", "nguyendieuanh", "$2a$10$V0nioZSN5WopWcXyxAta3uTHB2v/ehixJ3GQV.4c35jfiYl733lKW", "Nữ", "", "1998-02-11", "Bắc Kạn", "0478399293", "anh93@gmail.com", 1, 1);
INSERT INTO Account(first_name, last_name, username, password, gender, img, date_of_birth, address, phone_number, email, account_type, status)
VALUES ("Bích", "Lê", "lengocbich", "$2a$10$cVxqCvC9fVqEfGd8jkFwXOPj0rcCMDLrxCMfcWmf/aVMHFgWXq75u", "Nữ", "", "1999-03-12", "Lâm Đồng", "0747749829", "bich29@gmail.com",1, 1);
INSERT INTO Account(first_name, last_name, username, password, gender, img, date_of_birth, address, phone_number, email, account_type, status)
VALUES ("Dung", "Đỗ", "domydung", "$2a$10$vwwuS0KBK4qdqZkBAzp8zuFrnedxMcInQPaHTRD.bYLQ7VeQxm1dq", "Nữ", "", "2002-08-04", "Long An", "0747483974", "dung74@gmail.com", 1, 1);

-- Tạo bảng Cart
CREATE TABLE Cart (
  -- Khai báo thuộc tính id kiểu int, không được null, tăng tự động và là khóa chính
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  -- Khai báo thuộc tính account_id kiểu int, không được null và là khóa ngoại tham chiếu đến bảng Account 
  account_id INT NOT NULL,
  FOREIGN KEY (account_id) REFERENCES Account(id)
);

INSERT INTO Cart(account_id) VALUES (3), (4);

-- Tạo bảng CartItem
CREATE TABLE CartItem (
  -- Khai báo thuộc tính id kiểu int, không được null, tăng tự động và là khóa chính
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  -- Khai báo thuộc tính quantity kiểu int, không được null
  quantity INT NOT NULL,
  -- Khai báo thuộc tính cart_id kiểu int, không được null và là khóa ngoại tham chiếu đến bảng Cart
  cart_id INT NOT NULL,
  FOREIGN KEY (cart_id) REFERENCES Cart(id),
  -- Khai báo thuộc tính book_id kiểu int, không được null và là khóa ngoại tham chiếu đến bảng Book
  book_id INT NOT NULL,
  FOREIGN KEY (book_id) REFERENCES Book(id)
);

INSERT INTO CartItem(quantity, cart_id, book_id) VALUES (2, 1, 1);
INSERT INTO CartItem(quantity, cart_id, book_id) VALUES (2, 1, 2);
INSERT INTO CartItem(quantity, cart_id, book_id) VALUES (2, 1, 3);
INSERT INTO CartItem(quantity, cart_id, book_id) VALUES (2, 1, 4);
INSERT INTO CartItem(quantity, cart_id, book_id) VALUES (2, 1, 5);

-- Tạo bảng Order
CREATE TABLE `Order` (
	-- Khai báo thuộc tính id kiểu int, không được null, tăng tự động và là khóa chính
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	-- Khai báo thuộc tính date_order kiểu Date, không được null
	date_order DATE NOT NULL,
	-- Khai báo thuộc tính total_price kiểu double, không được null
	total_price DOUBLE NOT NULL,
	-- Khai báo thuộc tính name kiểu varchar(255), không được null
	name VARCHAR(255) NOT NULL,
	-- Khai báo thuộc tính address kiểu varchar(255), không được null
	address VARCHAR(255) NOT NULL,
	-- Khai báo thuộc tính phone_number kiểu varchar(20), không được null
	phone_number VARCHAR(20) NOT NULL,
	-- Khai báo thuộc tính email kiểu char(255), không được null
	email CHAR(255) NOT NULL,
	-- Khai báo thuộc tính account_id kiểu int, không được null và là khóa ngoại tham chiếu đến bảng Account
	account_id INT NOT NULL,
	FOREIGN KEY (account_id) REFERENCES Account(id),
	-- Khai báo thuộc tính status kiểu int, không được null
	status INT NOT NULL
);


-- Tạo bảng OrderItem
CREATE TABLE OrderItem (
  -- Khai báo thuộc tính id kiểu int, không được null, tăng tự động và là khóa chính
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  -- Khai báo thuộc tính quantity kiểu int, không được null
  quantity INT NOT NULL,
  -- Khai báo thuộc tính price kiểu double, không được null
  price double NOT NULL,
  -- Khai báo thuộc tính book_id kiểu int, không được null và là khóa ngoại tham chiếu đến bảng Book
  book_id INT NOT NULL,
  FOREIGN KEY (book_id) REFERENCES Book(id),
  -- Khai báo thuộc tính order_id kiểu int, không được null và là khóa ngoại tham chiếu đến bảng Order
  order_id INT NOT NULL,
  FOREIGN KEY (order_id) REFERENCES `Order`(id)
);




