package cn.myth.boot.chain.impl;

import cn.myth.boot.chain.AbstractLogicChain;
import cn.myth.boot.chain.factory.DefaultChainFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component("logic_blacklist")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BlackListLogicChain extends AbstractLogicChain {

    @Override
    public DefaultChainFactory.LogicChainResultVo logic(String userId, String bizId) {
        log.info("【责任链】-黑名单开始 userId:{} bizId:{} logicMode:{}", userId, bizId, logicMode());

        List<String> logicHandleValue = Arrays.asList("1", "2");

        if (logicHandleValue.contains(userId)) {
            log.info("【责任链】-黑名单命中 userId:{} bizId:{} logicMode:{}", userId, bizId, logicMode());
            return DefaultChainFactory.LogicChainResultVo.builder()
                    .resultVo("BlackList Rule")
                    .logicModel(logicMode())
                    .build();
        }
        // 过滤其他责任链
        log.info("【责任链】-黑名单放行 userId:{} bizId:{} logicMode:{}", userId, bizId, logicMode());
        return next().logic(userId, bizId);
    }

    @Override
    protected String logicMode() {
        return DefaultChainFactory.LogicModel.LOGIC_BLACKLIST.getCode();
    }
}
