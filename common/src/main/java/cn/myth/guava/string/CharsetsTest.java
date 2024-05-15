package cn.myth.guava.string;

import com.google.common.base.Charsets;
import org.junit.Test;

/**
 * 字符集【Charsets】
 */
public class CharsetsTest {

    /**
     * Charsets针对所有Java平台都要保证支持的六种字符集提供了常量引用。尝试使用这些常量，而不是通过名称获取字符集实例。
     */
    @Test
    public void charsets() {
        byte[] bytes = "hello world".getBytes(Charsets.UTF_8);
    }
}
