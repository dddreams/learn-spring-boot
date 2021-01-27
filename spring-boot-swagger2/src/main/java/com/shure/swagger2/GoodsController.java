package com.shure.swagger2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = {"2-商品管理"})
@RestController
@RequestMapping("/goods")
public class GoodsController {

    // 模拟users信息的存储
    static Map<Long, GoodsEntity> goodsList = Collections.synchronizedMap(new HashMap<>());

    @GetMapping("/list")
    @ApiOperation(value = "获取商品列表")
    public List<GoodsEntity> list(){
        List<GoodsEntity> goodses = new ArrayList<>(goodsList.values());
        return goodses;
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "获取商品详细信息", notes = "根据商品id来获取商品详细信息")
    public GoodsEntity getGoods(@PathVariable Integer id) {
        return goodsList.get(id);
    }

    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "添加商品", notes = "根据商品对象添加商品")
    public String saveGoods(@RequestBody GoodsEntity goods) {
        goodsList.put(goods.getId(), goods);
        return "success";
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除商品", notes = "根据商品的id来指定删除商品对象")
    public String deleteGoods(@PathVariable Long id) {
        goodsList.remove(id);
        return "success";
    }

}
