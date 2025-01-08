package cn.myth.design_pattern.creational_pattern.factory_method;

import cn.myth.design_pattern.creational_pattern.factory_method.story.ICommodity;
import cn.myth.design_pattern.creational_pattern.factory_method.story.impl.CardCommodityService;
import cn.myth.design_pattern.creational_pattern.factory_method.story.impl.CouponCommodityService;
import cn.myth.design_pattern.creational_pattern.factory_method.story.impl.GoodsCommodityService;

public class StoreFactory {

    public ICommodity getCommodityService(Integer commodityType) {
        if (null == commodityType) return null;
        if (1 == commodityType) return new CouponCommodityService();
        if (2 == commodityType) return new GoodsCommodityService();
        if (3 == commodityType) return new CardCommodityService();
        throw new RuntimeException("不存在的商品服务类型");
    }
}
