package cn.myth.jdk8;

import cn.myth.jdk8.demo.Author;
import cn.myth.jdk8.demo.Book;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaTest extends AbstractBaseTest {

    @Test
    public void fun() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        }).start();

        new Thread(() -> System.out.println("Hello World2")).start();
    }

    /* ------------------------------------------------ */

    @Test
    public void example() {
        //eg:打印所有年龄小于18的作家的名字，并且要注意去重
        authorList
                .stream()//把集合转换成流
                .distinct()//先去除重复的作家
                .filter(author -> author.getAge() < 18)//筛选年龄小于18的
                .forEach(author -> System.out.println(author.getName()));//遍历打印名字
    }

    @Test
    public void mid() {
        // 1.创建流
        // 单列集合:集合对象.stream()
        Stream<Author> stream = authorList.stream();
        // 数组:Arrays.stream(数组) 或者使用Stream.of来创建
        Integer[] arr = {1,2,3,4,5};
        Stream<Integer> stream1 = Arrays.stream(arr);
        Stream<Integer> stream2 = Stream.of(arr);
        // 双列集合:转换成单列集合后再创建
        Map<String, Integer> map = new HashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);
        Stream<Map.Entry<String, Integer>> stream3 = map.entrySet().stream();

        // 2.中间操作
        // filter:过滤 可以对流中的元素进行条件过滤，符合过滤条件的才能继续留在流中
        authorList
                .stream()
                .filter(author -> author.getAge() < 18)
                .forEach(author -> System.out.println(author.getName()));
        System.out.println(LINE);

        // map:映射 可以把流中的元素进行转换或计算
        authorList
                .stream()
                .map(author -> author.getName())
                .forEach(name -> System.out.println(name));
        authorList
                .stream()
                .map(author -> author.getAge())
                .map(age -> age + 10)
                .forEach(age -> System.out.println(age));
        System.out.println(LINE);

        // distinct:去重 去除流中的重复元素
        // 注意:distinct方法是依赖Object的equals方法来判断是否是相同对象的，所以需要注意重写equals方法
        authorList
                .stream()
                .distinct()
                .forEach(author -> System.out.println(author.getName()));
        System.out.println(LINE);

        // sorted:排序 可以对流中元素进行排序
        // 注意:如果调用空参的sorted()方法，需要流中的元素是实现了Comparable
        authorList
                .stream()
                .distinct()
                .sorted(((o1, o2) -> o2.getAge() - o1.getAge()))//对流中的元素按照年龄进行降序排序
                .forEach(author-> System.out.println(author.getAge()));
        System.out.println(LINE);

        // limit:限制 可以限制流的最大长度，超出的部分将被抛弃
        authorList
                .stream()
                .distinct()
                .sorted(((o1, o2) -> o2.getAge() - o1.getAge()))//对流中的元素按照年龄进行降序排序
                .limit(2)
                .forEach(author-> System.out.println(author.getAge()));
        System.out.println(LINE);

        // skip:跳过 跳过流中的前n个元素，返回剩下的元素
        authorList
                .stream()
                .distinct()
                .sorted(((o1, o2) -> o2.getAge() - o1.getAge()))//对流中的元素按照年龄进行降序排序
                .skip(1)
                .forEach(author-> System.out.println(author.getAge()));
        System.out.println(LINE);

        // flatMap:扁平化 map只能把一个对象转换成另一个对象来作为流中的元素。而flatMap可以把一个对象转换成多个对象作为流中的元素。
        // eg1: 打印所有书籍的名字，对重复的元素进行去重
        authorList
                .stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .forEach(book -> System.out.println(book.getName()));
        System.out.println(LINE);
        // eg2: 打印现有数据的所有分类，要求对分类进行去重，不能出现这种格式：哲学,爱情
        authorList
                .stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .flatMap(book -> Arrays.stream(book.getCategory().split(",")))
                .distinct()
                .forEach(category -> System.out.println(category));
        System.out.println(LINE);
    }

    @Test
    public void finish() {
        // forEach 对流中的元素进行遍历操作，我们通过传入的参数去指定对遍历到的元素进行什么具体操作。

        // count 可以用来获取当前流中元素的个数
        long count = authorList.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .count();
        System.out.println(count);
        System.out.println(LINE);

        // max&min 可以用来或者流中的最值
        // eg:分别获取这些作家的所出书籍的最高分和最低分并打印
        Optional<Integer> max = authorList
                .stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .max((score1, score2) -> score1 - score2);

        Optional<Integer> min = authorList
                .stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .min((score1, score2) -> score1 - score2);

        System.out.println(max.get());
        System.out.println(min.get());
        System.out.println(LINE);

        // collect 把当前流转换成一个集合
        // eg:获取一个存放所有作者名字的List集合
        List<String> nameList = authorList
                .stream()
                .map(author -> author.getName())
                .collect(Collectors.toList());
        System.out.println(nameList);
        System.out.println(LINE);

        // eg:获取一个所有书名的Set集合
        Set<String> bookNameSet = authorList
                .stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getName())
                .collect(Collectors.toSet());
        System.out.println(bookNameSet);
        System.out.println(LINE);

        // eg:获取一个Map集合,map的key为作者名，value为List
        Map<String, List<Book>> map = authorList
                .stream()
                .distinct()
                .collect(Collectors.toMap(author -> author.getName(), author -> author.getBooks()));
        System.out.println(map);
        System.out.println(LINE);

        // anyMatch 用来判断是否含有任意符合匹配条件的元素，结果为boolean类型
        boolean anyMatchFlag = authorList
                .stream()
                .anyMatch(author -> author.getAge() > 29);
        System.out.println(anyMatchFlag);
        System.out.println(LINE);

        // allMatch 用来判断是否所有元素都符合匹配条件，结果为boolean类型
        boolean allMatchFlag = authorList
                .stream()
                .allMatch(author -> author.getAge() >= 18);
        System.out.println(allMatchFlag);
        System.out.println(LINE);

        // noneMatch 用来判断是否所有元素都不符合匹配条件，结果为boolean类型
        boolean noneMatchFlag = authorList
                .stream()
                .noneMatch(author -> author.getAge() > 100);
        System.out.println(noneMatchFlag);
        System.out.println(LINE);

        // findAny 获取流中任意一个元素，该方法没有办法保证获取的一定是流中的第一个元素
        // findFirst 获取流中的第一个元素

        Optional<Author> firstAuthor = authorList
                .stream()
                .filter(author -> author.getAge() > 18)
                .findFirst();
        firstAuthor.ifPresent(author -> System.out.println(author.getName()));
        System.out.println(LINE);

        // reduce归并 对流中的数据按照指定的计算方式计算出一个结果(缩减操作)
        // reduce的作用是把stream中的元素给组合起来，我们可以传入一个初始值，它会按照我们的计算方式依次拿流中的元素和初始值进行计算，计算结果再和后面的元素进行计算
        // eg:使用reduce求所有作者年龄的和
        Integer sum = authorList
                .stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(0, (result, age) -> result + age);
        System.out.println(sum);
        System.out.println(LINE);

        // 使用reduce求所有作者中年龄的最大值和最小值
        /**
         * T result = identity;
         * for (T element : this stream)
         * 	result = accumulator.apply(result, element)
         * return result;
         */
        Integer maxAuthorAge = authorList
                .stream()
                .map(author -> author.getAge())
                .reduce(Integer.MIN_VALUE, (result, age) -> Math.max(result, age));
        System.out.println(maxAuthorAge);

        Integer minAuthorAge = authorList
                .stream()
                .map(author -> author.getAge())
                .reduce(Integer.MAX_VALUE, (result, age) -> Math.min(result, age));
        System.out.println(minAuthorAge);

        // 用reduce一个参数的重载形式内部的计算
        /**
         *  	boolean foundAny = false;
         *      T result = null;
         *      for (T element : this stream) {
         *          if (!foundAny) {
         *              foundAny = true;
         *              result = element;
         *          }
         *          else
         *              result = accumulator.apply(result, element);
         *      }
         *      return foundAny ? Optional.of(result) : Optional.empty();
         */
        Optional<Integer> minAuthorAgeOptional = authorList
                .stream()
                .map(author -> author.getAge())
                .reduce((result, age) -> Math.min(result, age));
        minAuthorAgeOptional.ifPresent(age -> System.out.println(age));

        System.out.println(LINE);
    }

    // 基本数据类型优化
    // 我们之前用到的很多Stream的方法由于都使用了泛型。所以涉及到的参数和返回值都是引用数据类型。
    // 即使我们操作的是整数小数，但是实际用的都是他们的包装类。JDK5中引入的自动装箱和自动拆箱让我们在使用对应的包装类时就好像使用基本数据类型一样方便。但是你一定要知道装箱和拆箱肯定是要消耗时间的。虽然这个时间消耗很下。但是在大量的数据不断的重复装箱拆箱的时候，你就不能无视这个时间损耗了。
    // 所以为了让我们能够对这部分的时间消耗进行优化。Stream还提供了很多专门针对基本数据类型的方法。
    // 例如：mapToInt,mapToLong,mapToDouble,flatMapToInt,flatMapToDouble等。
    @Test
    public void convert() {
        authorList.stream()
                .map(author -> author.getAge())
                .map(age -> age + 10)
                .filter(age -> age > 18)
                .map(age -> age + 2)
                .forEach(System.out::println);

        System.out.println(LINE);

        authorList.stream()
                .mapToInt(author -> author.getAge())
                .map(age -> age + 10)
                .filter(age -> age > 18)
                .map(age -> age + 2)
                .forEach(System.out::println);

    }

}
