package io.github.anthem37.craft.domain.llm.model.value;

import dev.langchain4j.model.chat.request.ResponseFormatType;
import io.github.anthem37.easy.ddd.domain.model.IValueObject;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 额外配置信息，包含模型的详细参数
 *
 * @author hb28301
 * @since 2025/10/10 19:08:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ExtraInfo implements IValueObject {

    /**
     * 是否返回思考过程
     * 默认值: false
     */
    private boolean returnThinking = false;
    /**
     * 温度参数，控制生成文本的随机性，值越高越随机(0.0-2.0)
     * 默认值: 0.7
     */
    @DecimalMin(value = "0.0", inclusive = true, message = "temperature必须>=0.0")
    @DecimalMax(value = "2.0", inclusive = true, message = "temperature必须<=2.0")
    private Double temperature = 0.7;
    /**
     * 核采样参数，控制生成时考虑的词汇概率质量(0.0-1.0)
     * 默认值: 1.0
     */
    @DecimalMin(value = "0.0", inclusive = true, message = "topP必须>=0.0")
    @DecimalMax(value = "1.0", inclusive = true, message = "topP必须<=1.0")
    private Double topP = 1.0;
    /**
     * 最大令牌数，限制生成文本的最大长度
     * 默认值: 1000
     */
    @Min(value = 1, message = "maxTokens必须为正整数")
    private Integer maxTokens = 1000;
    /**
     * 存在惩罚参数，减少重复主题的出现概率
     * 默认值: 0.0
     */
    @DecimalMin(value = "-2.0", inclusive = true, message = "presencePenalty必须>=-2.0")
    @DecimalMax(value = "2.0", inclusive = true, message = "presencePenalty必须<=2.0")
    private Double presencePenalty = 0.0;
    /**
     * 频率惩罚参数，减少重复词汇的出现频率
     * 默认值: 0.0
     */
    @DecimalMin(value = "-2.0", inclusive = true, message = "frequencyPenalty必须>=-2.0")
    @DecimalMax(value = "2.0", inclusive = true, message = "frequencyPenalty必须<=2.0")
    private Double frequencyPenalty = 0.0;
    /**
     * 停止序列列表，用于指定在生成文本时的停止条件
     */
    private List<String> stop;
    /**
     * 响应格式类型，指定生成文本的格式类型
     * 默认值: TEXT
     */
    @NotNull(message = "responseFormatType不能为空")
    private ResponseFormatType responseFormatType = ResponseFormatType.TEXT;
    /**
     * 请求超时时间
     * 默认值: 30秒
     */
    @Positive(message = "timeout必须为正数秒")
    private Integer timeout = 30;

    /**
     * 最大重试次数
     * 默认值: 3
     */
    @PositiveOrZero(message = "maxRetries必须为非负整数")
    private Integer maxRetries = 3;

    /**
     * 是否记录请求日志
     * 默认值: false
     */
    private boolean logRequests = false;

    /**
     * 是否记录响应日志
     * 默认值: false
     */
    private boolean logResponses = false;

    /**
     * 聊天模型监听器列表
     */
    private List<String> chatModelListenerNames;
}
