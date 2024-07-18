package com.example.his.api.db.mapper;

import com.example.his.api.db.pojo.GoodsEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
* @author 87647
* @description 针对表【tb_goods(体检套餐表)】的数据库操作Mapper
* @createDate 2024-06-05 22:05:27
* @Entity db.pojo.GoodsEntity
*/
public interface GoodsMapper {
    /**
     * 分页查询商品
     * @param param
     * @return
     */
    public ArrayList<HashMap> searchByPage(Map param);

    /**
     * 查询商品数量
     * @param param
     * @return
     */
    public long searchCount(Map param);


    /**
     * 插入商品信息
     * @param entity
     * @return
     */
    public int insert(GoodsEntity entity);
}