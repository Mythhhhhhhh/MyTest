package cn.myth.lombok.$11_SneakyThrows;

import lombok.SneakyThrows;

import java.io.UnsupportedEncodingException;

public class SneakyThrowsExample implements Runnable {

    /**
     *  如果我们的代码中包含方法声明中有 throws 指明会抛出一个“被检查的异常”的代码，那我们只有两种解决方式：
     *  1. 在当前方法声明中添加 throws 语句，指明当前方法也可能抛出该类型的“被检查异常”
     *  2. 用try-catch捕获该异常，并处理(通常是将其包装成一个RuntimeException并抛出)
     */


    @SneakyThrows(UnsupportedEncodingException.class)
    public String utf8ToString(byte[] bytes) {
        return new String(bytes, "UTF-8");
    }

    @SneakyThrows
    public void run() {
        throw new Throwable();
    }

    /**
     *   真正生成的代码
     *   public String utf8ToString(byte[] bytes) {
     *     try {
     *       return new String(bytes, "UTF-8");
     *     } catch (UnsupportedEncodingException e) {
     *       throw Lombok.sneakyThrow(e);
     *     }
     *   }
     *
     *   public void run() {
     *     try {
     *       throw new Throwable();
     *     } catch (Throwable t) {
     *       throw Lombok.sneakyThrow(t);
     *     }
     *   }
     *
     * 原理
     * 显然魔法 藏在Lombok.sneakyThrow(t);中。
     * 可能大家都会以为这个方法就是new RuntimeException()之类的。然而事实并非如此。阅读代码可以看出整个方法其实最核心的逻辑是throw (T)t;
     * 利用泛型将我们传入的Throwable强转为RuntimeException。虽然事实上我们不是RuntimeException。但是没关系。因为JVM并不关心这个。
     * 泛型最后存储为字节码时并没有泛型的信息。这样写只是为了骗过javac编译器。源码中注释有解释。
     */


}
