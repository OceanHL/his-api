package com.example.his.api.mis.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.his.api.common.PageUtils;
import com.example.his.api.common.R;
import com.example.his.api.mis.controller.form.SearchGoodsByPageForm;
import com.example.his.api.mis.service.GoodsService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
