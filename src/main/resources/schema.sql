-- PostgreSQL 数据库初始化脚本
-- 这个脚本会在应用启动时自动执行（如果配置了spring.sql.init.mode=always）

-- 创建用户表（如果不存在）
CREATE TABLE IF NOT EXISTS user_info (
    user_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 创建文章表（如果不存在）
CREATE TABLE IF NOT EXISTS articles (
    article_id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    date VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT NOT NULL,
    
    -- 外键约束
    CONSTRAINT fk_articles_user_id 
        FOREIGN KEY (user_id) 
        REFERENCES user_info (user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- 创建索引以提高查询性能
CREATE INDEX IF NOT EXISTS idx_articles_user_id ON articles(user_id);
CREATE INDEX IF NOT EXISTS idx_articles_date ON articles(date);
CREATE INDEX IF NOT EXISTS idx_articles_created_at ON articles(created_at);
CREATE INDEX IF NOT EXISTS idx_articles_user_date ON articles(user_id, date);

-- 插入一些示例数据（仅用于测试）
-- 注意：密码是 "admin123" 的BCrypt加密结果
INSERT INTO user_info (username, password_hash, email) 
VALUES ('admin', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'admin@example.com')
ON CONFLICT (username) DO NOTHING;