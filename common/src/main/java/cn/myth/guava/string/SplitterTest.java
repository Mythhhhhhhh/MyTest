package cn.myth.guava.string;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * 字符串拆分器【Splitter】
 * Splitter可以被设置为按照任何模式、字符、字符串或字符匹配器拆分。返回一个Iterable<T>
 */
public class SplitterTest {

    @Test
    public void spliter() {
        // trimResults 去掉头尾空格 |a||b|c||
        List<String> splitterList = Splitter.on(",").trimResults().splitToList(" ,a,,b,c,, ");
        System.out.println(Joiner.on("|").join(splitterList));

        // omitEmptyStrings 忽略空串  |a|b|c|
        splitterList = Splitter.on(",").omitEmptyStrings().splitToList(" ,a,,b,c,, ");
        System.out.println(Joiner.on("|").join(splitterList));

        // 两者一起使用 a|b|c
        splitterList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(" ,a,,b,c,, ");
        System.out.println(Joiner.on("|").join(splitterList));

        // limit a|b,c,,
        splitterList = Splitter.on(",").trimResults().omitEmptyStrings().limit(2).splitToList(" ,a,,b,c,, ");
        System.out.println(Joiner.on("|").join(splitterList));

        // withKeyValueSeparator  a=1|b=2
        Map<String, String> splitterMap = Splitter.on(";").withKeyValueSeparator("-").split("a-1;b-2");
        System.out.println(Joiner.on("|").withKeyValueSeparator("=").join(splitterMap));
    }
}
