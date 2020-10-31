package com.yjy;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Function是个泛型类，T代表输入参数，R代表返回结果
 * Function接口是一个功能型接口，实现apply方法来进行转换
 * Function接口是一个功能接口，它的一个作用就是转换，将输入数据转换成另一种形式的数据格式
 * 应用场景：我们通过传入不同的Function，实现在同一方法实现不同的操作
 * 如项目中有个新增用户功能，但用户分VIP和普通用户，且有两种不同的新增逻辑，那么此时我们就可以先写两种不同的逻辑。除此之外，这样还让逻辑与数据分离开来，我们可以实现逻辑的复用。
 */
public class FunctionTest {
	
	@Test
	public void test1() {
		Function<Integer, Integer> A = x -> x + 1;
		Function<Integer, Integer> B = i -> i * i;
		System.out.println("apply():" + A.apply(3)); // 4
		System.out.println("fun.apply(num):" + calculate(B, 5)); // 25
		
		// 复杂调用：两个方法F1,F2都需要两个个逻辑AB，但是F1需要A->B，F2方法需要B->A
		System.out.println("B.apply(A.apply(5)):"+B.apply(A.apply(5))); // 36
		System.out.println("A.apply(B.apply(5)):"+A.apply(B.apply(5))); // 26
		
		// 优化：使用compose和andThen
		System.out.println("B.compose(A).apply(5):"+B.compose(A).apply(5)); // 36
		System.out.println("B.andThen(A).apply(5):"+B.andThen(A).apply(5)); // 26
	}
	
	@Test
	public void test2() {
		Function<String, Integer> s = Integer::parseInt;
		Integer integer = s.apply("10");
		System.out.println(integer);
	}
	
	@Test
	public void test3() {
		Comparator<Integer> comparator = Integer::compare;
		System.out.println(comparator.compare(100,10)); // 大于(1)
		System.out.println(comparator.compare(10,10)); // 等于(0)
		System.out.println(comparator.compare(10,100)); // 小于(-1)
	}
	
	public static Integer calculate(Function<Integer, Integer> fun, Integer num) {
		return fun.apply(num);
	}
	
	@Test
	public void test4() {
		// 使用map方法，泛型的第一个参数是转换前的类型，第二个是转化后的类型
		// Function接口对象，实现了apply方法，该方法有一个输入参数和一个输出参数
		Function<String, Integer> function = new Function<String, Integer>() {

			@Override
			public Integer apply(String s) {
				return s.length(); // 返回每个字符串的长度
			}
		};
		Stream<String> strStream = Stream.of("aaa", "bb", "cccc");
		Stream<Integer> intStream = strStream.map(function);
		intStream.forEach(System.out::println);

		// 简化代码
		Function<String, Integer> function2 = (s) -> s.length();
		Stream<String> strStream2 = Stream.of("aaa", "bb", "cccc");
		Stream<Integer> intStream2 = strStream2.map(function2);
		intStream2.forEach(System.out::println);
	}
}
