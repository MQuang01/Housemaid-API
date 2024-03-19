use db_housemaid;

INSERT INTO categories (id, name, url_Image) VALUES
(1, 'domestic help', 'URL_hinh_domestic_help'),
(2, 'cook/place kitchen', 'URL_hinh_cook_place_kitchen'),
(3, 'babysitter', 'URL_hinh_babysitter'),
(4, 'all-rounder', 'URL_hinh_all_rounder'),
(5, '24h-live in', 'URL_hinh_24h_live_in');

INSERT INTO jobs (id, name, url_Image, price, category_id) VALUES
(1, 'giặt giường', 'URL_hinh_giat_giuong', 12000, 1),
(2, 'nấu ăn', 'URL_hinh_nau_an', 60000, 2),
(3, 'đi chợ', 'URL_hinh_di_cho', 50000, 2),
(4, 'vệ sinh tường', 'URL_hinh_ve_sinh_tuong', 10000, 1),
(5, 'vệ sinh bếp', 'URL_hinh_ve_sinh_bep', 15000, 2),
(6, 'vệ sinh trần nhà', 'URL_hinh_ve_sinh_tran_nha', 14000, 1),
(7, 'Xịt/rửa sân thượng', 'URL_hinh_xit_rua_san_thuong', 9000, 1),
(8, 'Rửa bát', 'URL_hinh_rua_bat', 30000, 2),
(9, 'Hút bụi', 'URL_hinh_hut_bui', 15000, 1),
(10, 'vệ sinh máy lạnh', 'URL_hinh_ve_sinh_may_lanh', 250000, 1),
(11, 'giặt thảm', 'URL_hinh_giat_tham', 200000, 1),
(12, 'vệ sinh/sấy khô ghế sofa', 'URL_hinh_ve_sinh_say_kho_ghe_sofa', 350000, 1);

INSERT INTO roles (id, role) VALUES
(1, 'USER'),
(2, 'MODIFY'),
(3, 'ADMIN');


INSERT INTO users (id, address, created_at, dob, email, full_name, gender, is_active, password, phone, shift, type_user, username) VALUES
(1, 'Địa chỉ 1', '2024-03-19', '2000-01-01', 'email1@example.com', 'Nguyễn Văn A', 'MALE', 1, '123', '123456789', 'SHIFT_1', 'EMPLOYEE', 'user1'),
(2, 'Địa chỉ 2', '2024-03-19', '1995-05-10', 'email2@example.com', 'Trần Thị B', 'FEMALE', 1, '123', '987654321', 'SHIFT_2', 'EMPLOYEE', 'user2'),
(3, 'Địa chỉ 3', '2024-03-19', '1988-12-25', 'email3@example.com', 'Hoàng Văn C', 'MALE', 1, '123', '456123789', null, 'CUSTOMER', 'user3'),
(4, 'Địa chỉ 4', '2024-03-19', '1999-08-15', 'email4@example.com', 'Lê Thị D', 'FEMALE', 1, '123', '789123456', null, 'CUSTOMER', 'user4');

INSERT INTO `orders` (id, created_at, currently_code, status_order, time_start, total_price, total_time_approx, work_day, category_id, user_id) VALUES
(1, '2024-03-19', 'ABCD1234', 'WAITING', '08:00:00', 500000, '1', '2024-03-19', 1, 1),
(2, '2024-03-19', 'WXYZ5678', 'COMPLETE', '09:30:00', 350000, '1.5', '2024-03-19', 2, 2),
(3, '2024-03-19', 'EFGH9012', 'WAITING', '10:00:00', 800000, '1', '2024-03-19', 3, 3),
(4, '2024-03-19', 'IJKL3456', 'COMPLETE', '08:30:00', 600000, '1.5', '2024-03-19', 1, 4);


