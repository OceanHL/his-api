package com.example.his.api.common;

import lombok.Data;

import java.util.List;

/**
 * ClassName:
 * Package: com.example.his.api.common
 * Description: 测试
 *
 * @Author Ocean_jhl
 * @Create 2024/6/17 22:26
 * @Version 1.0
 */
@Data
public class PageUtils {
    /**
     * 总记录数
     */
    private long totalCount;

    /**
     * 每页显示几条记录
     */
    private int pageSize;

    /**
     * 总页数，可通过 totalCount / pageSize 计算
     */
    private int totalPage;

    /**
     * 当前页数
     */
    private int pageIndex;

    /**
     * 分页数据
     */
    private List list;

    /**
     *
     * @param list 分页数据
     * @param totalCount 总记录数
     * @param pageIndex 当前页数
     * @param pageSize 每页显示几条记录
     */
    public PageUtils(List list, long totalCount, int pageIndex, int pageSize) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.list = list;
        // 将 (double) totalCount 转化为浮点数，否则 long / int 依旧是 long
        // Math.ceil() 返回类型为 double 类型
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }
}
