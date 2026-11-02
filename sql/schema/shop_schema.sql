-- ----------------------------------------------------------------
-- members: 고객 테이블
-- products: 상품 테이블
-- gift_products: 사은품 테이블
-- orders: 주문 테이블
-- order_items: 주문 품목 테이블 (복합 키: order_id + product_id)
-- events (이벤트 정보)
-- event_registrations (이벤트 신청)
-- ----------------------------------------------------------------

CREATE database IF NOT EXISTS ssafydb;
USE ssafydb;
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS event_registrations;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS gift_products;
DROP TABLE IF EXISTS products;


CREATE TABLE members (
    member_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15),
    address VARCHAR(255),
    join_date DATE NOT NULL
);

CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    brand VARCHAR(50),
    price DECIMAL(10,2) NOT NULL,
    stock_quantity INT NOT NULL
);

CREATE TABLE gift_products(  
    gift_id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    product_id int  NOT NULL, 
    FOREIGN KEY (product_id) REFERENCES products(product_id)
) ;

CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    member_id VARCHAR(20),
    order_date DATETIME NOT NULL,
    status ENUM('Pending', 'Shipped', 'Delivered', 'Cancelled') DEFAULT 'Pending',
    FOREIGN KEY (member_id) REFERENCES members(member_id)
);

CREATE TABLE order_items (
    order_id INT,
    product_id INT,
    quantity INT NOT NULL,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

CREATE TABLE events (
    event_id INT AUTO_INCREMENT PRIMARY KEY,
    event_name VARCHAR(100) NOT NULL,
    description TEXT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
);

CREATE TABLE event_registrations (
    registration_id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT,
    member_id VARCHAR(20),
    registration_date DATETIME NOT NULL,
    FOREIGN KEY (event_id) REFERENCES events(event_id),
    FOREIGN KEY (member_id) REFERENCES members(member_id)
);

INSERT INTO members (member_id, name, email, phone, address, join_date) VALUES
('M001', 'Alice Johnson', 'alice@example.com', '123-456-7890', '123 Elm St, NY', '2022-01-10'),
('M002', 'Bob Smith', 'bob@example.com', '234-567-8901', '456 Maple St, CA', '2023-03-15'),
('M003', 'Charlie Lee', 'charlie@example.com', '345-678-9012', '789 Oak St, TX', '2021-06-21'),
('M004', 'David Kim', 'david@example.com', '456-789-0123', '101 Pine St, WA', '2022-09-10'),
('M005', 'Eva Green', 'eva@example.com', '567-890-1234', '202 Cedar St, FL', '2023-07-30'),
('M006', 'Frank White', 'frank@example.com', '678-901-2345', '303 Birch St, IL', '2023-02-05');


INSERT INTO products (name, category, brand, price, stock_quantity) VALUES
('Laptop', 'Electronics', 'Dell', 1200.00, 10),
('Smartphone', 'Electronics', 'Apple', 999.00, 15),
('Headphones', 'Accessories', 'Sony', 150.00, 30),
('Gaming Chair', 'Furniture', 'DXRacer', 350.00, 20),
('Wireless Mouse', 'Accessories', 'Logitech', 40.00, 50),
('Mechanical Keyboard', 'Accessories', 'Corsair', 120.00, 25),
('Monitor', 'Electronics', 'Samsung', 300.00, 18),
('Desk Lamp', 'Home', 'Philips', 60.00, 40),
('Coffee Maker', 'Home', 'Keurig', 100.00, 10),
('Smartwatch', 'Electronics', 'Garmin', 250.00, 35);


INSERT INTO orders (member_id, order_date, status) VALUES
('M001', '2024-01-05 10:30:00', 'Shipped'),
('M002', '2024-01-10 14:20:00', 'Delivered'),
('M003', '2024-02-15 16:50:00', 'Pending'),
('M004', '2024-06-01 09:15:00', 'Cancelled'),
('M005', '2025-02-05 13:45:00', 'Shipped'),
('M002', '2025-02-14 18:20:00', 'Delivered');

INSERT INTO order_items (order_id, product_id, quantity) VALUES
(1, 1, 1),
(1, 5, 2),
(2, 2, 1),
(2, 6, 1),
(3, 1, 1),
(3, 7, 2),
(4, 4, 1),
(4, 8, 1),
(5, 3, 2),
(6, 10, 3);

INSERT INTO events (event_name, description, start_date, end_date) VALUES
('Spring Sale', 'Exclusive spring offers on various products.', '2024-03-01', '2024-03-20'),
('Summer Discount', 'Hot summer discounts on outdoor gear.', '2024-07-01', '2024-07-10'),
('Black Friday', 'Biggest discount event of the year!', '2024-11-25', '2024-11-29'),
('Cyber Party', 'Special online deals on electronics.', '2024-12-02', '2024-12-02'),
('Holiday Special', 'Year-end holiday shopping event.', '2024-12-20', '2024-12-31');

INSERT INTO event_registrations (event_id, member_id, registration_date) VALUES
(1, 'M001', '2024-03-16 12:00:00'),
(2, 'M005', '2024-07-02 09:00:00'),
(2, 'M006', '2024-07-05 11:10:00'),
(3, 'M001', '2024-11-26 10:30:00'),
(3, 'M002', '2024-11-27 15:45:00'),
(4, 'M003', '2024-12-02 08:20:00'),
(5, 'M001', '2024-12-21 13:40:00'),
(5, 'M003', '2024-12-22 16:50:00'),
(5, 'M006', '2024-12-23 18:30:00');


COMMIT;

SELECT COUNT(*) FROM ORDERS;