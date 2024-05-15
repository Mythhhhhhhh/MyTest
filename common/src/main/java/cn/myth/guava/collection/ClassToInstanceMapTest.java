package cn.myth.guava.collection;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;
import org.junit.Test;

public class ClassToInstanceMapTest {

    /**
     * ClassToInstanceMap是一种特殊的Map：它的键是类型，而值是符合键所指类型的对象。
     * 为了扩展Map接口，ClassToInstanceMap额外声明了两个方法：T getInstance(Class T) 和T putInstance(Class , T)，从而避免强制类型转换，同时保证了类型安全。
     */

    @Test
    public void classToInstanceMapTest(){
        ClassToInstanceMap<Number> instanceMap = MutableClassToInstanceMap.create();
        instanceMap.putInstance(Integer.class,123);
        instanceMap.putInstance(Long.class,456L);
        instanceMap.putInstance(Double.class,789.09);

        System.out.println("Integer:"+instanceMap.getInstance(Integer.class));//Integer:123
        System.out.println("Long:"+instanceMap.getInstance(Long.class));//Long:456
        System.out.println("Double:"+instanceMap.getInstance(Double.class));//Double:789.09
    }

}
