package io.github.anthem37.craft.application.common.dto;

import lombok.Data;

import java.util.List;

/**
 * 分页查询结果
 *
 * @author hb28301
 * @since 2025/10/11 21:15:37
 */
@Data
public class PageDTO<T> {

    /**
     * 当前页码
     */
    private long current;
    /**
     * 每页数量
     */
    private long size;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 分页数据
     */
    private List<T> records;

    /**
     * 分页查询结果工厂方法
     *
     * @param current 当前页码
     * @param size    每页数量
     * @param total   总记录数
     * @param records 分页数据
     * @param <T>     分页数据类型
     * @return 分页查询结果
     */
    public static <T> PageDTO<T> of(long current, long size, long total, List<T> records) {
        PageDTO<T> pageDTO = new PageDTO<>();
        pageDTO.setCurrent(current);
        pageDTO.setSize(size);
        pageDTO.setTotal(total);
        pageDTO.setRecords(records);
        return pageDTO;
    }

}
