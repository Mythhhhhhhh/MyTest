package cn.myth.guava.string;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * 字符串连接器【Joiner】
 */
public class JoinerTest {

    @Test
    public void joiner() {
        //Joiner的使用分为三个步骤：
        //(1)、on方法用来设置链接符
        //(2)、在on方法之后 join方法之前 ，我们可以做一些扩展操作，比如s使用useForNull是为null值设置默认值。
        //(3)、join方法用来设置被操作的集合

        //joiner实例总是不可变的。用来定义joiner目标语义的配置方法总会返回一个新的joiner实例。
        //这使得joiner实例都是线程安全的，可以将其定义为static final常量。

        List<String> list = Lists.newArrayList("Hello", null, "World", "!");
        System.out.println(list);//[Hello, null, World, !]

        // skipNulls 跳过空值
        Joiner joiner = Joiner.on(" ").skipNulls();
        System.out.println(joiner.join(list));//Hello World !

        // userForNull 使用参数替换字符串中的null值
        joiner = Joiner.on(" ").useForNull("my");
        System.out.println(joiner.join(list));//Hello my World !

        //withKeyValueSeparator 使用参数连接map结构
        Map<String, String> map = ImmutableMap.of("a", "1", "b", "2", "c", "3");
        System.out.println(Joiner.on(";").withKeyValueSeparator("-").join(map));//a-1;b-2;c-3
    }

}
