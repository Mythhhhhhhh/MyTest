package cn.myth.boot.chain.impl;

import cn.myth.boot.chain.AbstractLogicChain;
import cn.myth.boot.chain.factory.DefaultChainFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component("logic_weight")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WeightLogicChain extends AbstractLogicChain {

    @Override
    public DefaultChainFactory.LogicChainResultVo logic(String userId, String bizId) {
        log.info("【责任链】-权重开始 userId:{} bizId:{} logicMode:{}", userId, bizId, logicMode());

        Map<String, Long> logicHandleValue = new HashMap<>();
        logicHandleValue.put("1", 999L);

        if (logicHandleValue.containsKey(userId)) {
            log.info("【责任链】-权重接管 userId:{} bizId:{} logicMode:{}", userId, bizId, logicMode());
            return DefaultChainFactory.LogicChainResultVo.builder()
                    .resultVo(logicHandleValue.get(userId))
                    .logicModel(logicMode())
                    .build();
        }

        // 过滤其他责任链
        log.info("【责任链】-权重放行 userId:{} bizId:{} logicMode:{}", userId, bizId, logicMode());
        return next().logic(userId, bizId);
    }

    @Override
    protected String logicMode() {
        return DefaultChainFactory.LogicModel.LOGIC_WEIGHT.getCode();
    }
}
