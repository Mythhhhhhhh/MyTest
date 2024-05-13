package cn.myth.apache;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.Test;

/**
 * A <code>Map</code> implementation that is a general purpose alternative
 * to <code>HashMap</code>.
 * <p>
 * This implementation improves on the JDK1.4 HashMap by adding the
 * {@link org.apache.commons.collections4.MapIterator MapIterator}
 * functionality and many methods for subclassing.
 */
public class HashedMapTest {

    @Test
    public void test() {
        // HashMap的通用替代品。让也能使用IterableMap的迭代器那样去使用和迭代Map了，没什么多余的可以说明的。
        HashedMap<Object, Object> map = new HashedMap<>();
    }
}
