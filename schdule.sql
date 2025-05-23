-- DB 생성
DROP DATABASE IF EXISTS schedule;
CREATE DATABASE IF NOT EXISTS schedule;
USE schedule;

-- users 테이블 생성
DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '유저 ID (PK)',
    name VARCHAR(50) NOT NULL COMMENT '유저명',
    email VARCHAR(100) NOT NULL UNIQUE COMMENT '유저 이메일',
    password VARCHAR(255) NOT NULL COMMENT '비밀번호',
    created_at DATETIME COMMENT '작성일',
    updated_at DATETIME COMMENT '수정일'
) COMMENT = '유저 Table';

-- schedules 테이블 생성
DROP TABLE IF EXISTS schedules;
CREATE TABLE IF NOT EXISTS schedules (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '할일 ID (PK)',
    title VARCHAR(100) NOT NULL COMMENT '할일 제목',
    contents TEXT NOT NULL COMMENT '할일 내용',
    user_id BIGINT NOT NULL COMMENT '작성 유저 ID (FK)',
    created_at DATETIME COMMENT '작성일',
    updated_at DATETIME COMMENT '수정일',

    FOREIGN KEY (user_id) REFERENCES users(id)
) COMMENT = '일정 Table';


-- schedule_comments 테이블 생성
DROP TABLE IF EXISTS schedule_comments;
CREATE TABLE IF NOT EXISTS schedule_comments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '댓글 ID (PK)',
    contents TEXT NOT NULL COMMENT '댓글 내용',
    user_id BIGINT NOT NULL COMMENT '작성 유저 ID (FK)',
    schedule_id BIGINT NOT NULL COMMENT '일정 ID (FK)',
    created_at DATETIME COMMENT '작성일',
    updated_at DATETIME COMMENT '수정일',

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (schedule_id) REFERENCES schedules(id)
) COMMENT = '일정 댓글 Table';