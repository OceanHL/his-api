package com.example.his.api.mis.service;

import com.example.his.api.common.PageUtils;
import com.example.his.api.db.pojo.GoodsEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * ClassName:
 * Package: com.example.his.api.mis.service
 * Description: 测试
 *
 * @Author Ocean_jhl
 * @Create 2024/7/1 23:05
 * @Version 1.0
 */
public interface GoodsService {
    /**
     * 分页查询商品
     * @param param
     * @return
     */
    public PageUtils searchByPage(Map param);

    /**
     * 上传图片
     * @param file 图片文件对象
     * @return
     */
    public String uploadImage(MultipartFile file);

    /**
     * 插入商品信息
     * @param entity
     * @return
     */
    public int insert(GoodsEntity entity);
}
