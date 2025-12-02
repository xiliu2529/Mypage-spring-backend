-- 文章表初始化脚本（可选）
-- 这个文件会在应用启动时执行（如果 spring.sql.init.mode=always）

-- 插入一些示例文章（仅用于测试）
-- 注意：这些示例文章需要对应已存在的用户ID

-- 假设用户ID为1的用户存在，插入示例文章
-- INSERT INTO articles (title, content, date, user_id, created_at, updated_at) VALUES
-- ('React 18 新特性详解', 'React 18 引入了许多令人兴奋的新特性...', '2024-01-15', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
-- ('TypeScript 高级技巧', '学习 TypeScript 的高级类型操作...', '2024-01-12', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
-- ('前端性能优化指南', '从代码分割、懒加载到缓存策略...', '2024-01-10', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);