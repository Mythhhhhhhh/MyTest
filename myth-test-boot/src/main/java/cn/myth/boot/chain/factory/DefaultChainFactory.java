package cn.myth.boot.chain.factory;

import cn.myth.boot.chain.ILogicChain;
import lombok.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DefaultChainFactory {
    // 原型模式获取对象
    private final ApplicationContext applicationContext;

    // 存放策略链，策略ID -> 责任链
    private final Map<Long, ILogicChain> logicChainGroup;

    // 业务阶段处理，要构建的责任链规则id数组
    String[] logicModels = new String[]{
            LogicModel.LOGIC_BLACKLIST.getCode(),
            LogicModel.LOGIC_WEIGHT.getCode()};

    public DefaultChainFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.logicChainGroup = new ConcurrentHashMap<>();
    }

    /**
     * 通过策略ID，构建责任链
     *
     * @param strategyId 策略ID
     * @return LogicChain
     */
    public ILogicChain openLogicChain(Long strategyId) {
        ILogicChain cacheLogicChain = logicChainGroup.get(strategyId);
        if (null != cacheLogicChain) return cacheLogicChain;

        // 如果未配置策略逻辑，则只装填一个默认责任链
        if (null == logicModels || 0 == logicModels.length) {
            ILogicChain ruleDefaultLogicChain = applicationContext.getBean(LogicModel.LOGIC_DEFAULT.getCode(), ILogicChain.class);
            // 写入缓存
            logicChainGroup.put(strategyId, ruleDefaultLogicChain);
            return ruleDefaultLogicChain;
        }

        // 按照配置顺序初始化责任链；logic_blacklist、logic_weight
        ILogicChain logicChain = applicationContext.getBean(logicModels[0], ILogicChain.class);
        ILogicChain current = logicChain;
        for (int i = 1; i < logicModels.length; i++) {
            ILogicChain nextChain = applicationContext.getBean(logicModels[i], ILogicChain.class);
            current = current.appendNext(nextChain);
        }

        // 责任链的最后装填默认责任链
        current.appendNext(applicationContext.getBean(LogicModel.LOGIC_DEFAULT.getCode(), ILogicChain.class));
        // 写入缓存
        logicChainGroup.put(strategyId, logicChain);

        return logicChain;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LogicChainResultVo {

        /* 责任链逻辑模型 */
        private String logicModel;

        // ... 其它字段由具体的业务去实现
        private Object resultVo;

    }

    @Getter
    @AllArgsConstructor
    public enum LogicModel {
        LOGIC_DEFAULT("logic_default", "默认逻辑"),
        LOGIC_BLACKLIST("logic_blacklist", "黑名单逻辑"),
        LOGIC_WEIGHT("logic_weight", "权重逻辑"),
        ;

        private final String code;
        private final String info;
    }
}
