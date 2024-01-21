DROP DATABASE IF EXISTS text_board;
CREATE DATABASE text_board;
USE text_board;

# 게시물 테이블 생성
CREATE TABLE article (
	id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	title CHAR(100) NOT NULL,
	`body` TEXT NOT NULL
);


# 회원 테이블 생성
CREATE TABLE `member` (
	id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	loginId CHAR(100) NOT NULL,
	loginPw CHAR(200) NOT NULL,
	`name` CHAR(100) NOT NULL
);

SELECT * FROM `member`

# loginId 칼럼에 unique 제약 조건
ALTER TABLE `member` MODIFY loginId CHAR(200) NOT NULL UNIQUE;

# 해당 로그인 아이디가 있으면 1(true)을 반환 없으면 0(false)을 반환
SELECT COUNT(*) > 0
FROM `member`
WHERE loginId = 'user1';