package cn.myth.jdk8;

import cn.myth.jdk8.demo.Author;
import cn.myth.jdk8.demo.Book;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class OptionalTest extends AbstractBaseTest {

    @Test
    public void create() {
        // 1.创建对象
        // 一般使用Optional的静态方法ofNullable来把数据封装成一个Optional对象。无论传入的参数是否为null都不会出现问题。
        Author author = new Author();
        Optional<Author> authorOptional = Optional.ofNullable(author);

        // 确定一个对象不是空的则可以使用Optional的静态方法of来把数据封装成Optional对象。
        Optional<Author> authorOptional2 = Optional.of(author);

        // 把null封装成Optional对象返回。这时则可以使用Optional的静态方法empty来进行封装。
        Optional.empty();
    }

    @Test
    public void consumer() {
        // 2.安全消费值
        // ifPresent 会判断其内封装的数据是否为空，不为空时才会执行具体的消费代码
        Optional<Author> authorOptional = Optional.ofNullable(author);

        authorOptional.ifPresent(author -> System.out.println(author.getName()));
    }

    @Test
    public void get() {
        // 如果我们想获取值自己进行处理可以使用get方法获取，但是不推荐，因为当Optional内部的数据为空时会出现异常
        // 3.安全获取值
        Optional<Author> authorOptional = Optional.ofNullable(author);
        // orElseGet 获取数据并且设置数据为空时的默认值。如果数据不为空就能获取到该数据。
        // 如果为空则根据你传入的参数来创建对象作为默认值返回
        Author author1 = authorOptional.orElseGet(() -> new Author());
        System.out.println(author1);
        System.out.println(LINE);

        // orElseThrow 如果为空则根据你传入的参数来创建异常抛出
        try {
            Author author = authorOptional.orElseThrow((Supplier<Throwable>) () -> new RuntimeException("author为空"));
            System.out.println(author.getName());
            System.out.println(LINE);
        }  catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void filter() {
        // 4.过滤 可以使用filter方法对数据进行过滤，如果原本是有数据的，但是不符合判断，也会变成一个无数据的Optional对象
        Optional<Author> authorOptional = Optional.ofNullable(author);
        authorOptional.filter(author -> author.getAge() > 18).ifPresent(author -> System.out.println(author.getName()));
        //authorOptional.filter(author -> author.getAge() > 100).orElseThrow(() -> new RuntimeException("Error"));
        System.out.println(LINE);
    }

    @Test
    public void present() {
        // 5.判断
        // 我们可以使用isPresent方法进行是否存在数据的判断，如果为空返回值为false，如果不为空，返回值为true
        // 但这种并不能体现Optional的好处，更推荐使用ifPresent方法
    }

    @Test
    public void map() {
        // 6.数据转换
        Optional<Author> authorOptional = Optional.ofNullable(author);
        Optional<List<Book>> booksOptional = authorOptional.map(author -> author.getBooks());
        booksOptional.ifPresent(books -> System.out.println(books));
        System.out.println(LINE);
    }





}
