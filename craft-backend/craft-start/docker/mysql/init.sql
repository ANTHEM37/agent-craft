-- 初始化数据库与表结构
CREATE
DATABASE IF NOT EXISTS `craft` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE
`craft`;

-- LLM 配置表（与 LLMConfigPO / BasePO 对应）
CREATE TABLE IF NOT EXISTS `tb_chat_model_config`
(
    `id`
    BIGINT
    NOT
    NULL,
    `config_name`
    VARCHAR
(
    255
) NULL COMMENT '配置名称',
    `provider` VARCHAR
(
    50
) NULL COMMENT '模型提供商',
    `base_url` VARCHAR
(
    255
) NULL COMMENT '自定义API基础URL',
    `api_key` VARCHAR
(
    255
) NULL COMMENT 'API密钥',
    `model_name` VARCHAR
(
    255
) NULL COMMENT '模型名称',
    `extra_info` JSON NULL COMMENT '额外配置信息',
    `description` TEXT NULL COMMENT '模型描述',

    `created_at` DATETIME NULL COMMENT '创建时间',
    `updated_at` DATETIME NULL COMMENT '更新时间',
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
    `deleted` BIGINT NOT NULL DEFAULT 0 COMMENT '逻辑删除标记（0 未删除，1 删除）',
    PRIMARY KEY
(
    `id`
),
    KEY `idx_config_name`
(
    `config_name`
),
    KEY `idx_provider`
(
    `provider`
),
    KEY `idx_model_name`
(
    `model_name`
),
    KEY `idx_updated_at`
(
    `updated_at`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='LLM配置表';

-- 聊天内存配置表
CREATE TABLE IF NOT EXISTS `tb_chat_memory_config`
(
    `id`
    BIGINT
    NOT
    NULL,
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

    `created_at` DATETIME NULL COMMENT '创建时间',
    `updated_at` DATETIME NULL COMMENT '更新时间',
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
    `deleted` BIGINT NOT NULL DEFAULT 0 COMMENT '逻辑删除标记（0 未删除，1 删除）',
    PRIMARY KEY
(
    `id`
),
    KEY `idx_config_name`
(
    `config_name`
),
    KEY `idx_chat_memory_type`
(
    `chat_memory_type`
),
    KEY `idx_updated_at`
(
    `updated_at`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='聊天内存配置表';

-- 聊天内存配置关联表
CREATE TABLE IF NOT EXISTS `tb_chat_memory_config_ref`
(
    `id`
    BIGINT
    NOT
    NULL,
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
    NULL
    COMMENT
    '创建时间',
    `updated_at`
    DATETIME
    NULL
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
    `deleted` BIGINT NOT NULL DEFAULT 0 COMMENT '逻辑删除标记（0 未删除，1 删除）',
    PRIMARY KEY
(
    `id`
),
    KEY `idx_config_id`
(
    `config_id`
),
    KEY `idx_memory_id`
(
    `memory_id`
),
    KEY `idx_updated_at`
(
    `updated_at`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='聊天内存配置关联表';

-- 聊天内存表
CREATE TABLE IF NOT EXISTS `tb_chat_memory`
(
    `id`
    BIGINT
    NOT
    NULL,
    `content`
    JSON
    NULL
    COMMENT
    '消息内容',

    `created_at`
    DATETIME
    NULL
    COMMENT
    '创建时间',
    `updated_at`
    DATETIME
    NULL
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
    `deleted` BIGINT NOT NULL DEFAULT 0 COMMENT '逻辑删除标记（0 未删除，1 删除）',
    PRIMARY KEY
(
    `id`
),
    KEY `idx_updated_at`
(
    `updated_at`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='聊天内存表';