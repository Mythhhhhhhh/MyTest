package cn.myth.lombok.$12_Synchronized;

import lombok.Synchronized;

public class SynchronizedExample {

    @Synchronized
    public static void hello() {
        System.out.println("hello");
    }

    @Synchronized
    public int answerToLife() {
        return 42;
    }

    private final Object readLock = new Object();

    @Synchronized("readLock")
    public void foo() {
        System.out.println("bar");
    }


    /**
     *   private static final Object $LOCK = new Object[0];
     *
     *   public static void hello() {
     *     synchronized($LOCK) {
     *       System.out.println("world");
     *     }
     *   }
     *
     *   private final Object $lock = new Object[0];
     *
     *   public int answerToLife() {
     *     synchronized($lock) {
     *       return 42;
     *     }
     *   }
     *
     *   private final Object readLock = new Object();
     *
     *   public void foo() {
     *     synchronized(readLock) {
     *       System.out.println("bar");
     *     }
     *   }
     *
     *   值得注意的是，作为临界区的 $lock 和 $LOCK 都是new Object[0](即一个元素类型为Object且长度为0的数组)，
     *   而不是一般会使用的new Object。这样做的好处是前者可以序列化，而后者不行。而序列化的时候必须确保所有属性可以被序列化，
     *   因此前者不会阻止所在的类变成一个可序列化的类(implements Serializable)，而后者会，所以使用前者会更好一点
     */


}
