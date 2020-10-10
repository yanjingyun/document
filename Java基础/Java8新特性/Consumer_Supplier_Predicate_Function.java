package com.yjy;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Java8新特性：Consumer接口、Supplier接口、Predicate接口、Function接口
public class Consumer_Supplier_Predicate_Function {

	public static void main(String[] args) {

		// 测试Consumer接口
		// Consumer接口就是一个消费型的接口，通过传入参数，然后输出值，就是这么简单
		testConsumer1();

		// 测试Supplier接口
		// Supplier接口是一个供给型的接口，相当于一个容器或变量，可以存储数据，然后可以供其他方法使用的这么一个接口
		testSupplier1();
		testSupplier2();

		// 测试Predicate接口
		// Predicate接口其实就是一个判断的作用，通过test方法做判断
		testPredicate1();
		testPredicate2();

		// 测试Function接口
		// Function接口是一个功能型接口，实现apply方法来进行转换
		// Function接口是一个功能接口，它的一个作用就是转换，将输入数据转换成另一种形式的数据格式。
		testFunction1();
	}

	private static void testConsumer1() {
		System.out.println("***********************");
		Consumer<String> consumer = new Consumer<String>() {

			@Override
			public void accept(String s) {
				System.out.println(s);
			}
		};
		Stream<String> stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
		stream.forEach(consumer);
		
		// 简化代码：使用lambda表达式
		Stream<String> stream1 = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
		Consumer<String> consumer1 = (s) -> System.out.println(s);
		stream1.forEach(consumer1);
		
		// 简化代码：使用方法引用
		Stream<String> stream2 = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
		Consumer<String> consumer2 = System.out::println;
		stream2.forEach(consumer2);
	}

	private static void testSupplier1() {
		System.out.println("***********************");
		Supplier<Integer> supplier = new Supplier<Integer>() {
			@Override
			public Integer get() {
				return new Random().nextInt(100);
			}
		};
		System.out.println(supplier.get());

		// 简化代码：使用lambda表达式
		Supplier<Integer> supplier2 = () -> new Random().nextInt(100);
		System.out.println(supplier2.get());

		// 简化代码：使用方法引用
		Supplier<Double> supplier3 = Math::random;
		System.out.println(supplier3.get());
	}

	private static void testSupplier2() {
		System.out.println("***********************");
		Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
		Optional<Integer> first = stream.filter(i -> i > 6).findFirst();
		// orElse，如果first中存在数，就返回这个数，如果不存在，就放回传入的数
		System.out.println(first.orElse(5));

		Supplier<Integer> supplier = new Supplier<Integer>() {
			@Override
			public Integer get() {
				// 返回一个随机值
				return new Random().nextInt();
			}
		};
		// orElseGet，如果first中存在数，就返回这个数，如果不存在，就返回supplier返回的值
		System.out.println(first.orElseGet(supplier));
	}

	private static void testPredicate1() {
		System.out.println("***********************");
		// 使用Predicate接口实现方法,只有一个test方法，传入一个参数，返回一个boolean值
		Predicate<Integer> predicate = new Predicate<Integer>() {

			@Override
			public boolean test(Integer t) {
				return t > 5 ? true : false;
			}
		};
		System.out.println(predicate.test(4));
		System.out.println(predicate.test(6));

		// 简化代码
		Predicate<Integer> predicate2 = (t) -> t > 5 ? true : false;
		System.out.println(predicate2.test(4));
		System.out.println(predicate2.test(6));
	}

	private static void testPredicate2() {
		Predicate<Integer> predicate = (t) -> t > 5 ? true : false;
		Stream<Integer> stream = Stream.of(1, 23, 3, 4, 5, 56, 6, 6);
		List<Integer> list = stream.filter(predicate).collect(Collectors.toList());
		list.forEach(System.out::println);
		System.out.println();
	}

	private static void testFunction1() {
		System.out.println("***********************");

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