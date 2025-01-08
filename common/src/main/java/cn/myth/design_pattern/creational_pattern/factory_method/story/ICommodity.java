package cn.myth.design_pattern.creational_pattern.factory_method.story;

import java.util.Map;

/**
 * 发奖接口
 */
public interface ICommodity {

    /**
     * 所有的奖品无论是实物、虚拟还是第三方，都需要通过我们的程序实现此接口进行处理，以保证最终入参出参的统一性。
     * 接口的入参包括；用户ID、奖品ID、业务ID以及扩展字段用于处理发放实物商品时的收获地址。
     */
    void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception;
}
