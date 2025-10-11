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
    `base_url`
    VARCHAR
(
    255
) NULL,
    `api_key` VARCHAR
(
    255
) NULL,
    `model_name` VARCHAR
(
    255
) NULL,
    `extra_info` JSON NULL,

    `created_at` DATETIME NULL,
    `updated_at` DATETIME NULL,
    `created_by` BIGINT NULL,
    `created_by_username` VARCHAR
(
    255
) NULL,
    `updated_by` BIGINT NULL,
    `updated_by_username` VARCHAR
(
    255
) NULL,
    `deleted` BIGINT NOT NULL DEFAULT 0,
    PRIMARY KEY
(
    `id`
),
    KEY `idx_model_name`
(
    `model_name`
),
    KEY `idx_updated_at`
(
    `updated_at`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;