package io.github.anthem37.craft.adaptor.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 通用响应封装
 *
 * @author hb28301
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Response<T> {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 业务状态码，0 表示成功
     */
    private int code;

    /**
     * 提示消息
     */
    private String message;

    /**
     * 数据载体
     */
    private T data;

    /**
     * 响应时间戳
     */
    private LocalDateTime timestamp = LocalDateTime.now();

    public static <T> Response<T> ok(T data) {
        return new Response<T>().setSuccess(true).setCode(0).setMessage("OK").setData(data).setTimestamp(LocalDateTime.now());
    }

    public static <T> Response<T> okMsg(String message) {
        return new Response<T>().setSuccess(true).setCode(0).setMessage(message).setTimestamp(LocalDateTime.now());
    }

    public static <T> Response<T> fail(int code, String message) {
        return new Response<T>().setSuccess(false).setCode(code).setMessage(message).setTimestamp(LocalDateTime.now());
    }
}