package com.example.his.api.mis.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.json.JSONUtil;
import com.example.his.api.common.PageUtils;
import com.example.his.api.common.R;
import com.example.his.api.db.pojo.GoodsEntity;
import com.example.his.api.mis.controller.form.InsertGoodsForm;
import com.example.his.api.mis.controller.form.SearchGoodsByPageForm;
import com.example.his.api.mis.service.GoodsService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * ClassName:
 * Package: com.example.his.api.mis.controller
 * Description: 测试
 *
 * @Author Ocean_jhl
 * @Create 2024/7/1 23:17
 * @Version 1.0
 */
@RestController("MisGoodsController")
@RequestMapping("/mis/goods")
@Slf4j
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    @PostMapping("/searchByPage")
    @SaCheckPermission(value = {"ROOT", "GOODS:SELECT"}, mode = SaMode.OR)
    public R searchByPage(@RequestBody @Valid SearchGoodsByPageForm form) {
        log.info("goods-searchByPage入参: {}", form);
        // 需要自己计算起始坐标 start
        final int page = form.getPage();
        final int length = form.getLength();
        final int start = (page - 1) * length;
        final Map param = BeanUtil.beanToMap(form);
        param.put("start", start);
        final PageUtils pageUtils = goodsService.searchByPage(param);
        final R result = R.ok().put("page", pageUtils);
        log.info("goods-searchByPage响应: {}", result);
        return result;
    }

    @PostMapping("/uploadImage")
    @SaCheckPermission(value = {"ROOT", "GOODS:INSERT", "GOODS:UPDATE"}, mode = SaMode.OR)
    public R uploadImage(@Param("file") MultipartFile file) {
        log.info("goods-uploadImage入参: {}", file);
        final String imagePath = goodsService.uploadImage(file);
        final R result = R.ok().put("result", imagePath);
        log.info("goods-uploadImage响应: {}", result);
        return result;
    }

    @PostMapping("/insert")
    @SaCheckPermission(value = {"ROOT", "GOODS:INSERT"}, mode = SaMode.OR)
    public R insert(@RequestBody @Valid InsertGoodsForm form) {
        /**
         * 因为P0J0对象中的check_1、check_2、check_3和check_4是String类型，
         * 而FORM类中的这些东西是ArrayList类型的，为了避免FORM对象转换成P0J0对象出现异常，所以要排除这四个变量。
         */
        final GoodsEntity entity = BeanUtil.toBean(form, GoodsEntity.class, CopyOptions.create()
                // 设置忽略的参数
                .setIgnoreProperties("checkup_1", "checkup_2", "checkup_3", "checkup_4", "tag"));

        String temp = null;
        if (form.getCheckup_1() != null) {
            // 手动将ArrayList转换成JS0N数组字符串，给P0J0对象的checkup_1变量赋值
            temp = JSONUtil.parseArray(form.getCheckup_1()).toString();
            entity.setCheckup_1(temp);
        }

        if (form.getCheckup_2() != null) {
            // 手动将ArrayList转换成JS0N数组字符串，给P0J0对象的checkup_2变量赋值
            temp = JSONUtil.parseArray(form.getCheckup_2()).toString();
            entity.setCheckup_2(temp);
        }

        if (form.getCheckup_3() != null) {
            // 手动将ArrayList转换成JS0N数组字符串，给P0J0对象的checkup_3变量赋值
            temp = JSONUtil.parseArray(form.getCheckup_3()).toString();
            entity.setCheckup_3(temp);
        }

        if (form.getCheckup_4() != null) {
            // 手动将ArrayList转换成JS0N数组字符串，给P0J0对象的checkup_1变量赋值
            temp = JSONUtil.parseArray(form.getCheckup_4()).toString();
            entity.setCheckup_4(temp);
        }

        if (form.getTag() != null) {
            // 手动将ArrayList转换成JS0N数组字符串，给P0J0对象的checkup_1变量赋值
            temp = JSONUtil.parseArray(form.getTag()).toString();
            entity.setTag(temp);
        }

        // 插入数据库
        final int rows = goodsService.insert(entity);

        final R result = R.ok().put("rows", rows);
        return result;
    }
}
