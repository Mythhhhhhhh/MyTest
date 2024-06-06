package cn.myth.jdk8;

import cn.myth.jdk8.demo.Author;
import org.junit.Test;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class FunctionTest extends AbstractBaseTest {

    // 只有一个抽象方法的接口我们称之为函数接口
    // JDK的函数式接口都加上了@FunctionalInterface注解，但是无论是否加上该注解只要接口中只有一个抽象方法，都是函数式接口

    // 常见的函数式接口
    // 1.Consumer<T> 消费接口：根据其中抽象方法的参数列表和返回值类型，可以在方法中传入参数进行消费
    // 2.Function<T, R> 计算转换接口：根据其中抽象方法的参数列表和返回值类型，可以在方法中对传入的参数计算或转换，把结果返回(R)
    // 3.Predicate<T> 断言接口：根据其中抽象方法的参数列表和返回值类型，可以在方法中对传入的参数进行判断，返回判断结果(boolean)
    // 4.Supplier<T> 生产型接口：根据其中抽象方法的参数列表和返回值类型，可以在方法中创建对象，把创建好的对象返回(T)

    // 常用的默认方法
    // 我们在使用Predicate接口时候可能需要进行判断条件的拼接。而and方法相当于是使用&&来拼接两个判断条件
    @Test
    public void add() {
        // 打印作家中年龄大于17且姓名长度大于1的作家
        Stream<Author> stream = authorList.stream();
        stream.filter(new Predicate<Author>() {
            @Override
            public boolean test(Author author) {
                return author.getAge() > 17;
            }
        }.and(new Predicate<Author>() {
            @Override
            public boolean test(Author author) {
                return author.getName().length() > 1;
            }
        })).forEach(System.out::println);
    }
    // 我们在使用Predicate接口时候可能需要进行判断条件的拼接。而or方法相当于是使用||来拼接两个判断条件。
    @Test
    public void or() {
        // 打印作家中年龄大于17且姓名长度大于1的作家
        Stream<Author> stream = authorList.stream();
        stream.filter(new Predicate<Author>() {
            @Override
            public boolean test(Author author) {
                return author.getAge() > 17;
            }
        }.or(new Predicate<Author>() {
            @Override
            public boolean test(Author author) {
                return author.getName().length() < 2;
            }
       })).forEach(author -> System.out.println(author.getName()));
   }
   // Predicate接口中的方法。negate方法相当于是在判断添加前面加了个! 表示取反
    @Test
    public void negate() {
        // 打印作家中年龄不大于17的作家
        Stream<Author> stream = authorList.stream();
        stream.filter(new Predicate<Author>() {
            @Override
            public boolean test(Author author) {
                return author.getAge() > 17;
            }
        }.negate()).forEach(author -> System.out.println(author.getName()));

    }


}
