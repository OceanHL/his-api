package com.example.his.api.mis.service.impl;

import cn.hutool.core.util.IdUtil;
import com.example.his.api.common.MinioUtil;
import com.example.his.api.common.PageUtils;
import com.example.his.api.db.mapper.GoodsMapper;
import com.example.his.api.db.pojo.GoodsEntity;
import com.example.his.api.mis.service.GoodsService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:
 * Package: com.example.his.api.mis.service.impl
 * Description: 测试
 *
 * @Author Ocean_jhl
 * @Create 2024/7/1 23:06
 * @Version 1.0
 */
@Service("MisGoodsServiceImpl")
@Slf4j
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private MinioUtil minioUtil;

    @Override
    public PageUtils searchByPage(Map param) {
        ArrayList<HashMap> list = new ArrayList<>();
        final long count = goodsMapper.searchCount(param);
        if (count > 0) {
            list = goodsMapper.searchByPage(param);
        }
        final int start = (Integer) param.get("start");
        final int length = (Integer) param.get("length");
        final PageUtils pageUtils = new PageUtils(list, count, start, length);
        return pageUtils;
    }

    /**
     * 上传图片
     * @param imageFile 图片文件对象
     * @return imagePath - 文件路径
     */
    @Override
    public String uploadImage(MultipartFile imageFile) {
        // 生成新的文件名
        final String imageFileName = IdUtil.simpleUUID() + ".jpg";
        final String imagePath = "front/goods/" + imageFileName;
        minioUtil.uploadImage(imagePath, imageFile);
        return imagePath;
    }

    @Override
    public int insert(GoodsEntity entity) {
        return 0;
    }
}
