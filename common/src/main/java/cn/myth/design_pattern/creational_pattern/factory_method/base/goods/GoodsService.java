package cn.myth.design_pattern.creational_pattern.factory_method.base.goods;

import com.alibaba.fastjson2.JSON;

/**
 * 模拟实物商品服务
 */
public class GoodsService {

    public Boolean deliverGoods(DeliverReq req) {
        System.out.println("模拟发货实物商品一个：" + JSON.toJSONString(req));
        return true;
    }

}
