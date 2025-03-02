package cn.myth.boot.chain;

/**
 * 责任链装配
 */
public interface ILogicChainArmory {

    ILogicChain next();

    ILogicChain appendNext(ILogicChain chain);

}
