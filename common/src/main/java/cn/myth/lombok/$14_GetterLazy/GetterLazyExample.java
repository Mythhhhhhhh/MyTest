package cn.myth.lombok.$14_GetterLazy;

import lombok.Getter;

public class GetterLazyExample {

    @Getter(lazy=true) private final double[] cached = expensive();

    private double[] expensive() {
        double[] result = new double[1000000];
        for (int i = 0; i < result.length; i++) {
            result[i] = Math.asin(i);
        }
        return result;
    }

    /**
     *      private final AtomicReference<Object> cached = new AtomicReference();
     *
     *      public double[] getCached() {
     *         Object value = this.cached.get();
     *         if (value == null) {
     *             synchronized(this.cached) {
     *                 value = this.cached.get();
     *                 if (value == null) {
     *                     double[] actualValue = this.expensive();
     *                     value = actualValue == null ? this.cached : actualValue;
     *                     this.cached.set(value);
     *                 }
     *             }
     *         }
     *
     *         return (double[])((double[])(value == this.cached ? null : value));
     *     }
     *     可以看到，Lombok自动帮我们实现了类似的代码，且使用了原子操作相关类AtomicReference，以及synchronized语句，
     *     所以用@Getter(lazy=true)实现的类似优化(延迟初始化)是可以用于多线程的，是线程安全的。
     *
     *     潜在问题
     *     就像我们看到的，如果你用了@Getter(lazy=true)，那么在类中调用该字段时就必须用getXXX获取属性值,
     *     否则你获取到的就是一个AtomicReference<Object>类型的对象，并且该对象还没有进行过“初始化”
     */
}
