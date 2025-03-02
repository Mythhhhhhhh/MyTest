package cn.myth.boot.chain.impl;

import cn.myth.boot.chain.AbstractLogicChain;
import cn.myth.boot.chain.factory.DefaultChainFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 默认的责任链「作为最后一个链」
 */
@Slf4j
@Component("logic_default")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DefaultLogicChain extends AbstractLogicChain {

    @Override
    public DefaultChainFactory.LogicChainResultVo logic(String userId, String bizId) {
        log.info("【责任链】-默认处理 userId:{} bizId:{} logicMode:{}", userId, bizId, logicMode());
        return DefaultChainFactory.LogicChainResultVo.builder()
                .resultVo("Default Rule")
                .logicModel(logicMode())
                .build();
    }

    @Override
    protected String logicMode() {
        return DefaultChainFactory.LogicModel.LOGIC_DEFAULT.getCode();
    }

}
