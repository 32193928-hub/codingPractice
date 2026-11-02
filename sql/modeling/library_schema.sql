DROP DATABASE IF EXISTS LibraryDB;
CREATE DATABASE LibraryDB;
USE LibraryDB;

-- 📌 회원 테이블
CREATE TABLE member (
    member_id           INT AUTO_INCREMENT PRIMARY KEY,
    name                VARCHAR(20) NOT NULL,
    phone_number        CHAR(11),
    email               VARCHAR(30) UNIQUE,
    alert_flag          TINYINT(1) NOT NULL DEFAULT 1 COMMENT '알림 여부',   
    overdue_flag        TINYINT(1) NOT NULL DEFAULT 0 COMMENT '연체 여부',
    loan_restrict_until DATE NULL COMMENT '대출 제한 종료일',                
    CHECK (alert_flag IN (0,1)),
    CHECK (overdue_flag IN (0,1))
);

-- 📌 카테고리 테이블
CREATE TABLE category (
    category_id          INT AUTO_INCREMENT PRIMARY KEY,
    category_name        VARCHAR(30) NOT NULL UNIQUE,
    category_description VARCHAR(50) NULL
);


-- 📌 선호 카테고리 테이블
CREATE TABLE preferred_category (
    member_id   INT,
    category_id INT,
    PRIMARY KEY (member_id, category_id),
    FOREIGN KEY (member_id)     REFERENCES member(member_id)     ON DELETE CASCADE,
    FOREIGN KEY (category_id)   REFERENCES category(category_id) ON DELETE CASCADE
);


-- 📌 출판사 테이블
CREATE TABLE publisher (
    publisher_id        INT AUTO_INCREMENT PRIMARY KEY,
    publisher_name      VARCHAR(50),
    publisher_phone     CHAR(15) NOT NULL
);

-- 📌 도서 테이블
CREATE TABLE book (
    book_id             INT AUTO_INCREMENT PRIMARY KEY,
    title               VARCHAR(50) NOT NULL,
    author              VARCHAR(20),
    status              ENUM('AVAILABLE', 'BORROWED', 'RESERVED', 'LOST') DEFAULT 'AVAILABLE',
    category_id         INT,
    publisher_id        INT,
    publisher_name      VARCHAR(50),
    FOREIGN KEY (category_id)   REFERENCES category(category_id)    ON DELETE SET NULL,
    FOREIGN KEY (publisher_id)  REFERENCES publisher(publisher_id)  ON DELETE SET NULL
);

-- 📌 대출 내역 테이블
CREATE TABLE loan_record (
    loan_id       INT AUTO_INCREMENT PRIMARY KEY,
    loan_date     DATETIME DEFAULT CURRENT_TIMESTAMP,
    return_due    DATE NOT NULL,
    return_date   DATE NULL,
    member_id     INT NOT NULL,
    book_id       INT NOT NULL,
    FOREIGN KEY (member_id)     REFERENCES member(member_id)    ON DELETE CASCADE,
    FOREIGN KEY (book_id)       REFERENCES book(book_id)        ON DELETE CASCADE
);
