package cn.myth.design_pattern.creational_pattern.factory_method.story.impl;

import cn.myth.design_pattern.creational_pattern.factory_method.base.coupon.CouponResult;
import cn.myth.design_pattern.creational_pattern.factory_method.base.coupon.CouponService;
import cn.myth.design_pattern.creational_pattern.factory_method.story.ICommodity;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class CouponCommodityService implements ICommodity {

    private CouponService couponService = new CouponService();

    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception {
        CouponResult couponResult = couponService.sendCoupon(uId, commodityId, bizId);
        log.info("请求参数[优惠券] => uId：{} commodityId：{} bizId：{} extMap：{}", uId, commodityId, bizId, JSON.toJSON(extMap));
        log.info("测试结果[优惠券]：{}", JSON.toJSON(couponResult));
        if (!"0000".equals(couponResult.getCode())) throw new RuntimeException(couponResult.getInfo());
    }

}
