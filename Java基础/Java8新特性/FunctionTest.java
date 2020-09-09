package com.yjy;

import java.util.Comparator;
import java.util.function.Function;

public class FunctionTest {

	public static void main(String[] args) {
		// Function是个泛型类，T代表输入参数，R代表返回结果。
		// 应用场景：我们通过传入不同的Function，实现在同一方法实现不同的操作。
		// 如项目中有个新增用户功能，但用户分VIP和普通用户，且有两种不同的新增逻辑，那么此时我们就可以先写两种不同的逻辑。除此之外，这样还让逻辑与数据分离开来，我们可以实现逻辑的复用。
		Function<Integer, Integer> A = x -> x + 1;
		Function<Integer, Integer> B = i -> i * i;
		System.out.println(A.apply(3)); // 4
		System.out.println(calculate(B, 5)); // 25
		
		Function<String, Integer> s = Integer::parseInt;
		Integer integer = s.apply("10");
		System.out.println(integer);
		
		Comparator<Integer> comparator = Integer::compare;
		System.out.println(comparator.compare(100,10)); // 大于(1)
		System.out.println(comparator.compare(10,10)); // 等于(0)
		System.out.println(comparator.compare(10,100)); // 小于(-1)
		
		// 复杂调用：两个方法F1,F2都需要两个个逻辑AB，但是F1需要A->B，F2方法需要B->A
		System.out.println("F1:"+B.apply(A.apply(5))); // 36
		System.out.println("F2:"+A.apply(B.apply(5))); // 26
		// 优化：使用compose和andThen
		System.out.println("F1:"+B.compose(A).apply(5)); // 36
		System.out.println("F2:"+B.andThen(A).apply(5)); // 26

	}
	
	public static Integer calculate(Function<Integer, Integer> fun, Integer num) {
		return fun.apply(num);
	}
}