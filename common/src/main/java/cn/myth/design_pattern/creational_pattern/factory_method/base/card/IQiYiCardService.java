package cn.myth.design_pattern.creational_pattern.factory_method.base.card;

/**
 * 模拟爱奇艺会员卡服务
 */
public class IQiYiCardService {

    public void grantToken(String bindMobileNumber, String cardId) {
        System.out.println("模拟发放爱奇艺会员卡一张：" + bindMobileNumber + "，" + cardId);
    }

}
