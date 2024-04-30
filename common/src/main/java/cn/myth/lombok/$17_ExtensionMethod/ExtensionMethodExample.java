package cn.myth.lombok.$17_ExtensionMethod;

import lombok.experimental.ExtensionMethod;

@ExtensionMethod(ExtensionMethodExample.StringUtil.class)
public class ExtensionMethodExample {

    public static void main(String[] args) {
        System.out.println("".isEmpty());
    }

    /**
     *  为已经存在的类增加方法
     *  它可以达到扩展已有类的方法
     *
     *     public static void main(String[] args) {
     *         System.out.println(ExtensionMethodExample.StringUtil.isEmpty(""));
     *     }
     *
     *  实现依据：
     *  假设被扩展类为A，扩展工具类为B，那么就需要在类B中定义个static的方法，该方法有一个参数，类型为A。
     *  然后，在A中添加@ExtensionMethod(B.class) 注解即可。
     *
     */

    public static class StringUtil {

        public static boolean isEmpty(String targetStr) {
            System.out.println("StringUtil ---");
            return targetStr == null || "".equals(targetStr);
        }

    }

}
