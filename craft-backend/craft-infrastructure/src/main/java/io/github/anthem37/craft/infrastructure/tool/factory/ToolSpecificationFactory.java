package io.github.anthem37.craft.infrastructure.tool.factory;

import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.model.chat.request.json.*;
import io.github.anthem37.craft.domain.tool.model.entity.ToolConfig;
import io.github.anthem37.craft.domain.tool.model.value.ToolParamSpec;
import io.github.anthem37.craft.domain.tool.model.value.ToolParamType;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * 将 ToolConfig 转换为 LangChain4j ToolSpecification
 *
 * @author hb28301
 */
@Component
public class ToolSpecificationFactory {

    public ToolSpecification build(ToolConfig toolConfig) {
        toolConfig.validateInvariants();

        Map<String, JsonSchemaElement> properties = new LinkedHashMap<>();
        var schemaBuilder = JsonObjectSchema.builder().description(toolConfig.getParamsSchema() != null ? toolConfig.getParamsSchema().getDescription() : null);

        if (toolConfig.getParamsSchema() != null && toolConfig.getParamsSchema().getProperties() != null) {
            for (ToolParamSpec param : toolConfig.getParamsSchema().getProperties()) {
                properties.put(param.getName(), toSchema(param));
                if (Boolean.TRUE.equals(param.getRequired())) {
                    schemaBuilder.required(param.getName());
                }
            }
        }

        JsonObjectSchema schema = schemaBuilder.addProperties(properties).build();

        return ToolSpecification.builder().name(toolConfig.getBeanName()).description(appendResultInfo(toolConfig.getDescription(), toolConfig.getResultSchema())).parameters(schema).build();
    }

    private JsonSchemaElement toSchema(ToolParamSpec spec) {
        String description = spec.getDescription();
        ToolParamType type = spec.getType();
        return switch (type) {
            case STRING -> JsonStringSchema.builder().description(description).build();
            case INTEGER -> JsonIntegerSchema.builder().description(description).build();
            case NUMBER -> JsonNumberSchema.builder().description(description).build();
            case BOOLEAN -> JsonBooleanSchema.builder().description(description).build();
            case ENUM -> JsonEnumSchema.builder().description(description).enumValues(spec.getEnumValues()).build();
            case OBJECT -> buildObjectSchema(spec);
            case ARRAY -> buildArraySchema(spec);
        };
    }

    private JsonObjectSchema buildObjectSchema(ToolParamSpec spec) {
        var builder = JsonObjectSchema.builder().description(spec.getDescription());
        Map<String, JsonSchemaElement> props = new LinkedHashMap<>();
        List<ToolParamSpec> children = spec.getProperties();
        if (children != null) {
            for (ToolParamSpec child : children) {
                props.put(child.getName(), toSchema(child));
                if (Boolean.TRUE.equals(child.getRequired())) {
                    builder.required(child.getName());
                }
            }
        }
        return builder.addProperties(props).build();
    }

    private JsonArraySchema buildArraySchema(ToolParamSpec spec) {
        JsonSchemaElement itemSchema = spec.getItems() != null ? toSchema(spec.getItems()) : JsonStringSchema.builder().build();
        return JsonArraySchema.builder().description(spec.getDescription()).items(itemSchema).build();
    }

    private String appendResultInfo(String description, io.github.anthem37.craft.domain.tool.model.value.ToolParamsSchema resultSchema) {
        if (resultSchema == null || resultSchema.getProperties() == null || resultSchema.getProperties().isEmpty()) {
            return description;
        }
        StringJoiner joiner = new StringJoiner("\n");
        if (description != null && !description.isBlank()) {
            joiner.add(description.trim());
        }
        joiner.add("返回数据说明:");
        for (ToolParamSpec spec : resultSchema.getProperties()) {
            joiner.add("- " + formatSpec(spec));
        }
        return joiner.toString();
    }

    private String formatSpec(ToolParamSpec spec) {
        StringBuilder sb = new StringBuilder();
        sb.append(spec.getName()).append(" (").append(spec.getType()).append(")");
        if (spec.getDescription() != null && !spec.getDescription().isBlank()) {
            sb.append(": ").append(spec.getDescription());
        }
        if (spec.getType() == ToolParamType.ENUM && spec.getEnumValues() != null && !spec.getEnumValues().isEmpty()) {
            sb.append("，枚举值：").append(spec.getEnumValues());
        }
        if (spec.getType() == ToolParamType.OBJECT && spec.getProperties() != null && !spec.getProperties().isEmpty()) {
            sb.append("，子字段：");
            for (ToolParamSpec child : spec.getProperties()) {
                sb.append("[").append(formatSpec(child)).append("]");
            }
        }
        if (spec.getType() == ToolParamType.ARRAY && spec.getItems() != null) {
            sb.append("，数组元素：[").append(formatSpec(spec.getItems())).append("]");
        }
        return sb.toString();
    }
}