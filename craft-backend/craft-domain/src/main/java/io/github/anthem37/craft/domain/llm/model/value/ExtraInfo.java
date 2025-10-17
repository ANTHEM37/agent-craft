package io.github.anthem37.craft.domain.llm.model.value;

import dev.langchain4j.model.chat.request.ResponseFormatType;
import dev.langchain4j.model.chat.request.ToolChoice;
import io.github.anthem37.easy.ddd.common.assertion.Assert;
import io.github.anthem37.easy.ddd.domain.model.IValueObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 额外配置信息，包含模型的详细参数
 * 该类使用了Lombok注解来简化代码，提供了getter、setter、构造器等方法
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
     * 随机种子，用于控制模型的生成结果的可重复性
     * 范围通常是0到2^32-1（即0到4294967295）
     * 如果设置为null或不设置，模型的输出将是随机的，每次调用可能产生不同的结果
     * 如果设置了具体的整数值，模型在相同的输入和参数下将产生相同的输出
     * 这对于调试和测试非常有用，因为它允许开发者重现特定的生成结果
     */
    private Integer seed;

    /**
     * 温度参数，用于控制模型的生成结果的随机性
     * 范围通常是0到1（包括0和1）
     * 默认值为0.7
     * 较高的值（如0.9或1.0）会使模型的输出更加随机，而较低的值（如0.5或0.3）会使输出更加确定
     * 温度参数在生成文本时对结果的影响是显著的，因此在使用模型时需要根据具体任务和需求调整这个参数
     */
    private Double temperature = 0.7;

    /**
     * Top-p（nucleus sampling）参数，用于控制模型的生成结果的随机性
     * 范围通常是0到1（包括0和1）
     * 默认值为0.95
     * 较高的值（如0.95或1.0）会使模型的输出更加随机，而较低的值（如0.5或0.3）会使输出更加确定
     * Top-p参数在生成文本时对结果的影响是显著的，因此在使用模型时需要根据具体任务和需求调整这个参数
     */
    private Double topP = 0.95;

    /**
     * Top-k参数，用于控制模型的生成结果的随机性
     * 范围通常是1到100（包括1和100）
     * 默认值为40
     * 较高的值（如50或100）会使模型的输出更加随机，而较低的值（如10或20）会使输出更加确定
     * Top-k参数在生成文本时对结果的影响是显著的，因此在使用模型时需要根据具体任务和需求调整这个参数
     */
    private Integer topK = 40;

    /**
     * 频率惩罚参数，用于控制模型生成结果中重复内容的程度
     * 范围通常是-2.0到2.0（包括-2.0和2.0）
     * 默认值为0.0
     * 较高的值（如1.0或2.0）会使模型在生成文本时更加关注新的内容，而较低的值（如-1.0或-2.0）会使模型在生成文本时更加关注重复内容
     * 频率惩罚参数在生成文本时对结果的影响是显著的，因此在使用模型时需要根据具体任务和需求调整这个参数
     */
    private Double frequencyPenalty = 0.0;

    /**
     * 存在惩罚参数，用于控制模型生成结果中是否包含新内容的程度
     * 范围通常是-2.0到2.0（包括-2.0和2.0）
     * 默认值为0.0
     * 较高的值（如1.0或2.0）会使模型在生成文本时更加关注新的内容，而较低的值（如-1.0或-2.0）会使模型在生成文本时更加关注重复内容
     * 存在惩罚参数在生成文本时对结果的影响是显著的，因此在使用模型时需要根据具体任务和需求调整这个参数
     */
    private Double presencePenalty = 0.0;

    /**
     * 最大输出令牌数，用于限制模型生成的文本长度
     * 范围通常是1到模型的最大上下文长度（如2048或4096）
     * 默认值为2048
     * 较高的值（如3072或4096）会使模型生成更长的文本，而较低的值（如512或1024）会使生成的文本更短
     * 最大输出令牌数在生成文本时对结果的影响是显著的，因此在使用模型时需要根据具体任务和需求调整这个参数
     */
    private Integer maxOutputTokens = 2048;

    /**
     * 停止序列列表，用于指定模型在生成文本时遇到这些序列时停止生成
     * 每个停止序列通常是一个字符串，可以是单词、短语或标点符号
     * 当模型在生成文本时遇到任何一个停止序列时，生成过程将立即停止，并返回当前生成的文本
     * 停止序列列表可以包含多个序列，以便更灵活地控制生成过程
     * 如果不设置停止序列，模型将继续生成文本直到达到最大输出令牌数或其他终止条件
     */
    private List<String> stopSequences;

    /**
     * 工具规范列表，用于指定模型可以使用的工具
     * 每个工具规范通常包含工具的名称、描述和参数
     * 模型在生成文本时可以根据这些规范调用相应的工具
     * 如果不设置工具规范，模型将无法使用任何工具
     */
    private List<String> toolNames;

    /**
     * {@link ToolChoice}
     * 工具选择策略，用于指定模型在生成文本时如何选择使用的工具
     * 可以是自动选择（AUTO）、Required（REQUIRED）或不使用工具（NONE）
     * 选择合适的工具选择策略可以帮助模型更有效地利用工具，从而生成更准确和有用的文本
     */
    private ToolChoice toolChoice = ToolChoice.AUTO;

    /**
     * 响应格式，用于指定模型生成文本的格式
     * 可以是纯文本（TEXT）、JSON（JSON）或其他自定义格式
     * 选择合适的响应格式可以确保生成的文本符合预期的结构和内容要求
     * 如果不设置响应格式，模型将默认生成纯文本
     */
    private ResponseFormatType responseFormatType;

    /**
     * 聊天模型监听器列表
     */
    private List<String> chatModelListenerNames;

    /**
     * 验证业务规则
     *
     * @throws IllegalArgumentException 当参数不符合业务规则时抛出
     */
    public void validate() {
        validateTemperature();
        validateTopP();
        validateTopK();
        validateFrequencyPenalty();
        validatePresencePenalty();
        validateMaxOutputTokens();
    }

    /**
     * 验证temperature参数
     */
    private void validateTemperature() {
        if (temperature != null) {
            Assert.isTrue(temperature >= 0.0 && temperature <= 2.0,
                    "temperature参数必须在0.0-2.0范围内，当前值: " + temperature);
        }
    }

    /**
     * 验证topP参数
     */
    private void validateTopP() {
        if (topP != null) {
            Assert.isTrue(topP >= 0.0 && topP <= 1.0,
                    "topP参数必须在0.0-1.0范围内，当前值: " + topP);
        }
    }

    /**
     * 验证topK参数
     */
    private void validateTopK() {
        if (topK != null) {
            Assert.isTrue(topK >= 1 && topK <= 100,
                    "topK参数必须在1-100范围内，当前值: " + topK);
        }
    }

    /**
     * 验证frequencyPenalty参数
     */
    private void validateFrequencyPenalty() {
        if (frequencyPenalty != null) {
            Assert.isTrue(frequencyPenalty >= -2.0 && frequencyPenalty <= 2.0,
                    "frequencyPenalty参数必须在-2.0-2.0范围内，当前值: " + frequencyPenalty);
        }
    }

    /**
     * 验证presencePenalty参数
     */
    private void validatePresencePenalty() {
        if (presencePenalty != null) {
            Assert.isTrue(presencePenalty >= -2.0 && presencePenalty <= 2.0,
                    "presencePenalty参数必须在-2.0-2.0范围内，当前值: " + presencePenalty);
        }
    }

    /**
     * 验证maxOutputTokens参数
     */
    private void validateMaxOutputTokens() {
        if (maxOutputTokens != null) {
            Assert.isTrue(maxOutputTokens >= 1 && maxOutputTokens <= 8192,
                    "maxOutputTokens参数必须在1-8192范围内，当前值: " + maxOutputTokens);
        }
    }
}
