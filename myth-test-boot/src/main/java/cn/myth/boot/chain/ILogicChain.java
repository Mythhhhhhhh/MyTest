package cn.myth.boot.chain;

import cn.myth.boot.chain.factory.DefaultChainFactory;
import org.springframework.stereotype.Service;

/**
 * 责任链接口
 */
@Service
public interface ILogicChain extends ILogicChainArmory {

    /**
     * 责任链接口
     *
     * @param userId     用户ID
     * @param bizId 业务ID
     * @return 结果对象
     */
    DefaultChainFactory.LogicChainResultVo logic(String userId, String bizId);

}
