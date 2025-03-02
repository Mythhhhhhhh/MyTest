package cn.myth.boot.chain;

import cn.myth.boot.chain.factory.DefaultChainFactory;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.output.ansi.enabled=ALWAYS")
public class LogicChainTest {

    @Resource
    private DefaultChainFactory defaultChainFactory;

    @Test
    public void test_chain() {
        ILogicChain logicChain = defaultChainFactory.openLogicChain(1L);
        DefaultChainFactory.LogicChainResultVo resultVo = logicChain.logic("1", "biz1");
        log.info("测试结果：{}", JSON.toJSONString(resultVo));

    }


}
