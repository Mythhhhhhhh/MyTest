package cn.myth.jdk8;

import cn.myth.jdk8.demo.Author;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class ParallelStreamTest extends AbstractBaseTest {

    // 并行流
    // 当流中有大量元素时，我们可以使用并行流去提高操作的效率。其实并行流就是把任务分配给多个线程去完全。
    // 如果我们自己去用代码实现的话其实会非常的复杂，并且要求你对并发编程有足够的理解和认识。
    // 而如果我们使用Stream的话，我们只需要修改一个方法的调用就可以使用并行流来帮我们实现，从而提高效率。
    // parallel方法可以把串行流转换成并行流。

    @Test
    public void test1() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = stream.parallel()
                .peek(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        System.out.println(Thread.currentThread().getName() + ":" + integer);
                    }
                })
                .filter(num -> num > 5)
                .reduce((result, ele) -> result + ele)
                .get();
        System.out.println(sum);
    }

    @Test
    public void test2() {
        authorList.parallelStream()
                .mapToInt(Author::getAge)
                .map(age -> age + 10)
                .filter(age -> age > 18)
                .map(age -> age + 2)
                .forEach(System.out::println);
    }

}
