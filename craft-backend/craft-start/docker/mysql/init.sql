-- =============================================
-- Agent Craft 数据库初始化脚本
-- 版本: 1.0
-- 创建时间: 2024-01-01
-- 描述: 初始化Agent Craft项目的数据库结构和基础数据
-- =============================================

-- 创建数据库
CREATE
DATABASE IF NOT EXISTS `craft` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE
`craft`;

-- 设置时区和SQL模式
SET
time_zone = '+00:00';
SET
sql_mode = 'STRICT_TRANS_TABLES,NO_ZERO_DATE,NO_ZERO_IN_DATE,ERROR_FOR_DIVISION_BY_ZERO';

-- =============================================
-- LLM 配置表（与 LLMConfigPO / BasePO 对应）
-- =============================================
CREATE TABLE IF NOT EXISTS `tb_chat_model_config`
(
    `id`
    BIGINT
    NOT
    NULL
    COMMENT
    '主键ID',
    `config_name`
    VARCHAR
(
    255
) NOT NULL COMMENT '配置名称',
    `provider` VARCHAR
(
    50
) NOT NULL COMMENT '模型提供商',
    `base_url` VARCHAR
(
    500
) NULL COMMENT '自定义API基础URL',
    `api_key` VARCHAR
(
    500
) NOT NULL COMMENT 'API密钥',
    `model_name` VARCHAR
(
    255
) NOT NULL COMMENT '模型名称',
    `extra_info` JSON NULL COMMENT '额外配置信息',
    `description` TEXT NULL COMMENT '模型描述',
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `created_by` BIGINT NULL COMMENT '创建人',
    `created_by_username` VARCHAR
(
    255
) NULL COMMENT '创建人用户名',
    `updated_by` BIGINT NULL COMMENT '更新人',
    `updated_by_username` VARCHAR
(
    255
) NULL COMMENT '更新人用户名',
    `deleted` BIGINT NOT NULL DEFAULT 0 COMMENT '逻辑删除标记（0 未删除，删除时设为ID）',
    PRIMARY KEY
(
    `id`
),
    UNIQUE KEY `uk_config_name_deleted`
(
    `config_name`,
    `deleted`
),
    KEY `idx_provider`
(
    `provider`
),
    KEY `idx_model_name`
(
    `model_name`
),
    KEY `idx_created_at`
(
    `created_at`
),
    KEY `idx_updated_at`
(
    `updated_at`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_0900_ai_ci COMMENT='LLM配置表';

-- =============================================
-- 聊天内存配置表
-- =============================================
CREATE TABLE IF NOT EXISTS `tb_chat_memory_config`
(
    `id`
    BIGINT
    NOT
    NULL
    COMMENT
    '主键ID',
    `config_name`
    VARCHAR
(
    255
) NOT NULL COMMENT '配置名称，用于标识不同的记忆配置',
    `chat_memory_type` VARCHAR
(
    50
) NOT NULL COMMENT '记忆类型',
    `params` JSON NULL COMMENT '记忆参数',
    `description` TEXT NOT NULL COMMENT '记忆描述',
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `created_by` BIGINT NULL COMMENT '创建人',
    `created_by_username` VARCHAR
(
    255
) NULL COMMENT '创建人用户名',
    `updated_by` BIGINT NULL COMMENT '更新人',
    `updated_by_username` VARCHAR
(
    255
) NULL COMMENT '更新人用户名',
    `deleted` BIGINT NOT NULL DEFAULT 0 COMMENT '逻辑删除标记（0 未删除，删除时设为ID）',
    PRIMARY KEY
(
    `id`
),
    UNIQUE KEY `uk_config_name_deleted`
(
    `config_name`,
    `deleted`
),
    KEY `idx_chat_memory_type`
(
    `chat_memory_type`
),
    KEY `idx_created_at`
(
    `created_at`
),
    KEY `idx_updated_at`
(
    `updated_at`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_0900_ai_ci COMMENT='聊天内存配置表';

-- =============================================
-- 聊天内存配置关联表
-- =============================================
CREATE TABLE IF NOT EXISTS `tb_chat_memory_config_ref`
(
    `id`
    BIGINT
    NOT
    NULL
    COMMENT
    '主键ID',
    `config_id`
    BIGINT
    NOT
    NULL
    COMMENT
    '配置ID',
    `memory_id`
    BIGINT
    NOT
    NULL
    COMMENT
    '记忆ID',
    `created_at`
    DATETIME
    NOT
    NULL
    DEFAULT
    CURRENT_TIMESTAMP
    COMMENT
    '创建时间',
    `updated_at`
    DATETIME
    NOT
    NULL
    DEFAULT
    CURRENT_TIMESTAMP
    ON
    UPDATE
    CURRENT_TIMESTAMP
    COMMENT
    '更新时间',
    `created_by`
    BIGINT
    NULL
    COMMENT
    '创建人',
    `created_by_username`
    VARCHAR
(
    255
) NULL COMMENT '创建人用户名',
    `updated_by` BIGINT NULL COMMENT '更新人',
    `updated_by_username` VARCHAR
(
    255
) NULL COMMENT '更新人用户名',
    `deleted` BIGINT NOT NULL DEFAULT 0 COMMENT '逻辑删除标记（0 未删除，删除时设为ID）',
    PRIMARY KEY
(
    `id`
),
    UNIQUE KEY `uk_config_memory_deleted`
(
    `config_id`,
    `memory_id`,
    `deleted`
),
    KEY `idx_config_id`
(
    `config_id`
),
    KEY `idx_memory_id`
(
    `memory_id`
),
    KEY `idx_created_at`
(
    `created_at`
),
    KEY `idx_updated_at`
(
    `updated_at`
),
    CONSTRAINT `fk_memory_config_ref_config` FOREIGN KEY
(
    `config_id`
) REFERENCES `tb_chat_memory_config`
(
    `id`
) ON DELETE CASCADE,
    CONSTRAINT `fk_memory_config_ref_memory` FOREIGN KEY
(
    `memory_id`
) REFERENCES `tb_chat_memory`
(
    `id`
)
  ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_0900_ai_ci COMMENT='聊天内存配置关联表';

-- =============================================
-- 聊天内存表
-- =============================================
CREATE TABLE IF NOT EXISTS `tb_chat_memory`
(
    `id`
    BIGINT
    NOT
    NULL
    COMMENT
    '主键ID',
    `session_id`
    VARCHAR
(
    255
) NOT NULL COMMENT '会话ID',
    `content` JSON NOT NULL COMMENT '消息内容',
    `message_type` VARCHAR
(
    50
) NOT NULL COMMENT '消息类型（USER/AI/SYSTEM）',
    `token_count` INT NULL COMMENT 'Token数量',
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `created_by` BIGINT NULL COMMENT '创建人',
    `created_by_username` VARCHAR
(
    255
) NULL COMMENT '创建人用户名',
    `updated_by` BIGINT NULL COMMENT '更新人',
    `updated_by_username` VARCHAR
(
    255
) NULL COMMENT '更新人用户名',
    `deleted` BIGINT NOT NULL DEFAULT 0 COMMENT '逻辑删除标记（0 未删除，删除时设为ID）',
    PRIMARY KEY
(
    `id`
),
    KEY `idx_session_id`
(
    `session_id`
),
    KEY `idx_message_type`
(
    `message_type`
),
    KEY `idx_created_at`
(
    `created_at`
),
    KEY `idx_updated_at`
(
    `updated_at`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_0900_ai_ci COMMENT='聊天内存表';

-- =============================================
-- 初始化基础数据
-- =============================================

-- 插入默认的LLM配置
INSERT INTO `tb_chat_model_config` (`id`, `config_name`, `provider`, `base_url`, `api_key`, `model_name`, `extra_info`,
                                    `description`, `created_by`, `created_by_username`)
VALUES (1, 'OpenAI GPT-4', 'OPENAI', 'https://api.openai.com/v1', 'your-openai-api-key', 'gpt-4',
        '{"temperature": 0.7, "maxTokens": 2048}', 'OpenAI GPT-4 模型配置', 1, 'system'),
       (2, '通义千问', 'DASHSCOPE', 'https://dashscope.aliyuncs.com/api/v1', 'your-dashscope-api-key', 'qwen-turbo',
        '{"temperature": 0.7, "maxTokens": 2048}', '阿里云通义千问模型配置', 1, 'system');

-- 插入默认的聊天内存配置
INSERT INTO `tb_chat_memory_config` (`id`, `config_name`, `chat_memory_type`, `params`, `description`, `created_by`,
                                     `created_by_username`)
VALUES (1, '默认窗口记忆', 'WINDOW', '{"maxMessages": 10}', '保留最近10条消息的窗口记忆', 1, 'system'),
       (2, '摘要记忆', 'SUMMARY', '{"maxMessages": 20, "summaryPrompt": "请总结以下对话内容"}',
        '当消息超过20条时进行摘要的记忆配置', 1, 'system');

-- =============================================
-- 创建索引优化查询性能
-- =============================================

-- 为经常查询的字段创建复合索引
CREATE INDEX `idx_memory_session_created` ON `tb_chat_memory` (`session_id`, `created_at`);
CREATE INDEX `idx_config_provider_deleted` ON `tb_chat_model_config` (`provider`, `deleted`);

-- =============================================
-- 数据库初始化完成
-- =============================================