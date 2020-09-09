package com.yjy;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 测试jdk1.8新特性：stream
public class StreamTest {

	static class User {
		private String name;
		private int grade;
		private String type;
		private Date birthday;

		// 用户名&成绩
		public User(String name, int grade) {
			this.name = name;
			this.grade = grade;
		}

		// 用户名&用户类型
		public User(String name, String type) {
			this.name = name;
			this.type = type;
		}

		// 用户名&出生日期
		public User(String name, Date birthday) {
			this.name = name;
			this.birthday = birthday;
		}

		// 用户名&出生日期&成绩
		public User(String name, Date birthday, int grade) {
			this.name = name;
			this.birthday = birthday;
			this.grade = grade;
		}

		public static int compareByBirthday(User a, User b) {
			return a.getBirthday().compareTo(b.getBirthday());
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getGrade() {
			return grade;
		}

		public void setGrade(int grade) {
			this.grade = grade;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Date getBirthday() {
			return birthday;
		}

		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}

		@Override
		public String toString() {
			return "User [name=" + name + ", grade=" + grade + ", type=" + type + ", birthday=" + birthday + "]";
		}

	}

	static class Foo {
		private Integer type;
		private Integer num;

		public Foo(Integer type, Integer num) {
			this.type = type;
			this.num = num;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public Integer getNum() {
			return num;
		}

		public void setNum(Integer num) {
			this.num = num;
		}
	}

	public static class Person {
		private Integer id;
		private String name;
		private Integer age;

		public Person(Integer id, String name) {
			this.id = id;
			this.name = name;
		}

		public Person(Integer id, String name, Integer age) {
			this.id = id;
			this.name = name;
			this.age = age;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "Person2 [id=" + id + ", name=" + name + ", age=" + age + "]";
		}
	}

	public static void main(String[] args) {
		StreamTest st = new StreamTest();
		// 测试最大值、最小值、综合、排列和遍历、过滤
		st.test1();

		// 测试map相关
		st.testToMap(); // 测试collect() -> toMap
		st.testToCollection(); // 测试collect() -> toCollection
		st.testMapToInt(); // 测试mapToInt()
		st.testUserSort(); // 测试map()
		st.testUserSort2();
		st.testPersonMap();
		st.testPersonMap();

		// Collectors元素聚合
		st.testCollectors(); // 最大值、最小值、求和、平均值
		// 映射：先对集合中的元素进行映射，然后再对映射的结果使用Collectors操作
		st.testCollection(); // 测试Collectors.mapping

		// 分组：两种方式Collectors.groupingBy
		// 1）groupingBy(Function<? super T, ? extends K> classifier)
		// 参数是Function类型，Function返回值可以是要分组的条件，也可以是要分组的字段。返回的结果是Map，其中key的数据类型为Function体中计算类型，value是List<T>类型，为分组的结果
		// 2）partitioningBy 用于分成两组的情况
		st.testGroupingBy1();
		st.testPartitioningBy(); // 与 st.testGroupingBy1()效果一样
		st.testGroupingBy2();
		st.testGroupingBy3();
		st.testGroupingBy4();

		// 测试reduce()
		st.testReduce1();
		st.testReduce2();
		st.testReduce3(); // 累计操作
	}

	// 测试最大值、最小值、综合、排列和遍历、过滤
	public void test1() {
		List<Integer> list = Arrays.asList(2, 3, 5, 8, 6);
		list.stream().min(Integer::compareTo).ifPresent(System.out::println); // 最小值
		list.stream().max(Integer::compareTo).ifPresent(System.out::println); // 最大值
		list.stream().reduce((a, b) -> a + b).ifPresent(System.out::println); // 总和
		list.stream().sorted().forEach(e -> System.out.print(e + " ")); // 排序和遍历
		System.out.println();
		list.stream().filter(e -> e > 3 && e < 8).forEach(e -> System.out.print(e + " ")); // 过滤和遍历
		System.out.println();

	}

	// 测试collect() -> toMap
	public void testToMap() {
		Map<String, String> map = Stream.of(1, 2, 3, 4, 5).map(i -> i * 10)
				.collect(Collectors.toMap(key -> "key" + key, value -> "value:" + value));
		System.out.println(map);
	}

	// 测试collect() -> toCollection
	private void testToCollection() {
		TreeSet<Integer> treeSet = Stream.of(3, 2, 5, 3, 4).collect(Collectors.toCollection(TreeSet::new));
		System.out.println(treeSet);
	}

	// 测试mapToInt()
	private void testMapToInt() {
		List<User> list = new ArrayList<>();
		list.add(new User("testAA1", 23));
		list.add(new User("testAA2", 43));
		list.add(new User("testAA3", 33));
		list.add(new User("testAA4", 25));

		// 计算总成绩
		int sum = list.stream().mapToInt(User::getGrade).reduce(0, (a, b) -> a + b);
		System.out.println("团队总成绩：" + sum);
	}

	public void testPersonMap() {
		List<Person> personList = Arrays.asList(new Person(1, "testAA1"), new Person(2, "testAA1"),
				new Person(3, "testAA1"));
		Map<Integer, Person> personMap = personList.stream()
				.collect(Collectors.toMap(Person::getId, Function.identity()));
		System.out.println(personMap.get(1));

		Map<Integer, String> personnameMap = personList.stream()
				.collect(Collectors.toMap(Person::getId, Person::getName));
		System.out.println(personnameMap.get(1));
	}

	public void testPersonMap2() {
		List<Person> list = new ArrayList<>();
		list.add(new Person(1, "TestAA1", 23));
		list.add(new Person(2, "TestAA2", 33));
		list.add(new Person(3, "TestAA3", 55));
		list.add(new Person(4, "TestAA4", 44));
		list.add(new Person(5, "TestAA5", 55));

		// 姓名组成新集合
		List<String> nameList = list.stream().map(Person::getName).collect(Collectors.toList());
		nameList.forEach(e -> System.out.print(e + " "));
		System.out.println();

		// id组成新集合
		List<Integer> idList = list.stream().map(Person::getId).collect(Collectors.toList());
		idList.forEach(e -> System.out.print(e + " "));
		System.out.println();

		// list转map，key为id，value为Person对象
		Map<Integer, Person> map1 = list.stream().collect(Collectors.toMap(Person::getId, Person -> Person));
		System.out.println(map1.toString());
		// list转map，key为id，value为name
		Map<Integer, String> map2 = list.stream().collect(Collectors.toMap(Person::getId, Person::getName));
		System.out.println(map2.toString());
		// list转map，key为age，value为List<Person>集合
		Map<Integer, List<Person>> map3 = list.stream().collect(Collectors.groupingBy(Person::getAge));
		System.out.println(map3.toString());

		int minAge = list.stream().mapToInt(Person::getAge).min().getAsInt();
		System.out.println("最小年龄：" + minAge);
		int maxAge = list.stream().mapToInt(Person::getAge).max().getAsInt();
		System.out.println("最大年龄：" + maxAge);
		double avgAge = list.stream().mapToInt(Person::getAge).average().getAsDouble();
		System.out.println("平均年龄：" + avgAge);

		long count = list.stream().filter(p -> p.getAge() > 25 && "TestAA2".equals(p.getName())).count();
		System.out.println("人数为：" + count);

		String[] arr = { "scsa", "bi", "a", "cd", "ca156165" };
		Object[] objArr1 = Arrays.stream(arr).sorted().toArray();
		System.out.println(objArr1);
		Object[] objArr2 = Arrays.stream(arr).sorted((o1, o2) -> o1.length() - o2.length()).toArray();
		System.out.println(objArr2);
		Object[] objArr3 = Arrays.stream(arr).sorted(Comparator.comparing(String::length)).toArray();
		System.out.println(objArr3);
		// 倒序输出
		Object[] objArr4 = Arrays.stream(arr).sorted(Comparator.reverseOrder()).toArray();
		System.out.println(objArr4);
		Object[] objArr5 = Arrays.stream(arr).sorted(Comparator.naturalOrder()).toArray();
		System.out.println(objArr5);
		Object[] objArr6 = Arrays.stream(arr).sorted(Comparator.comparing(String::length).reversed()).toArray();
		System.out.println(objArr6);

		Arrays.stream(arr).sorted(Comparator.comparing(this::charAt).thenComparing(String::length))
				.forEach(System.out::println);

		list.stream().sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getName))
				.forEach(System.out::println);

		String name = list.stream().sorted(Comparator.comparing(x -> x.getAge())).findFirst().get().getName();
		System.out.println(name);
	}

	public char charAt(String x) {
		return x.charAt(0);
	}

	// 测试User排列
	private void testUserSort() {
		// 需求：1、成绩小于60的人 2、按照出生日期排序 3、获取姓名
		List<User> list = new ArrayList<>();
		list.add(new User("aa", Date.valueOf("2019-05-05"), 65));
		list.add(new User("bb", Date.valueOf("2019-05-04"), 58));
		list.add(new User("cc", Date.valueOf("2019-05-03"), 45));
		list.add(new User("dd", Date.valueOf("2019-05-02"), 55));
		list.add(new User("ee", Date.valueOf("2019-05-01"), 78));
		List<String> collect = list.stream().filter(u -> u.getGrade() < 60).sorted(User::compareByBirthday)
				.map(User::getName).collect(Collectors.toList());
		System.out.println(collect);
	}

	private void testUserSort2() {
		User[] userArray = { new User("testAA1", Date.valueOf("2018-05-11")),
				new User("testAA2", Date.valueOf("2018-05-02")), new User("testAA3", Date.valueOf("2018-05-03")),
				new User("testAA4", Date.valueOf("2018-05-05")), new User("testAA5", Date.valueOf("2018-05-05")) };
		// Arrays.sort(userArray, (a, b) -> a.getBirthday().compareTo(b.getBirthday()));
		// // 方式一
		// Arrays.sort(userArray, (a, b) -> User.compareByBirthday(a, b)); // 方式二
		Arrays.sort(userArray, User::compareByBirthday); // 方式三：静态方法引用
		System.out.println(Arrays.asList(userArray));
	}

	private void testGroupingBy1() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		// 奇偶数分组：奇数分一组，偶数分一组 {false=[1, 3, 5, 7, 9], true=[2, 4, 6, 8, 10]}
		Map<Boolean, List<Integer>> result = list.stream().collect(Collectors.groupingBy(item -> item % 2 == 0));
		System.out.println(result);
	}

	private void testPartitioningBy() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Map<Boolean, List<Integer>> result = list.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));
		System.out.println(result);
	}

	private void testGroupingBy2() {
		List<User> list = new ArrayList<>();
		list.add(new User("testAA1", 23));
		list.add(new User("testAA2", 25));
		list.add(new User("testAA3", 23));
		list.add(new User("testAA4", 26));
		Map<Integer, List<User>> map = list.stream().collect(Collectors.groupingBy(User::getGrade));
		System.out.println(map);
	}

	// 分组并对分组中的数据统计
	private void testGroupingBy3() {
		List<Foo> list = new ArrayList<>(4);
		list.add(new Foo(1, 2));
		list.add(new Foo(2, 23));
		list.add(new Foo(2, 6));
		Map<Integer, IntSummaryStatistics> collect = list.stream()
				.collect(Collectors.groupingBy(Foo::getType, Collectors.summarizingInt(Foo::getNum)));
		IntSummaryStatistics statistics1 = collect.get(1);
		IntSummaryStatistics statistics2 = collect.get(2);
		System.out.println(statistics1.getSum());
		System.out.println(statistics2.getSum());
		System.out.println(statistics2.getAverage());
		System.out.println(statistics2.getMax());
		System.out.println(statistics2.getMin());
		System.out.println(statistics2.getCount());
	}

	// 按照用户类型分类
	private void testGroupingBy4() {
		List<User> list = new ArrayList<>();
		list.add(new User("testAA1", "01"));
		list.add(new User("testAA2", "02"));
		list.add(new User("testAA3", "01"));
		list.add(new User("testAA4", "03"));

		Map<String, List<User>> map = list.stream().collect(Collectors.groupingBy(User::getType));
		System.out.println(map);
	}

	private void testCollectors() {
		List<Integer> list = Arrays.asList(1, 2, 3);
		Integer maxValue = list.stream()
				.collect(Collectors.collectingAndThen(Collectors.maxBy((a, b) -> a - b), Optional::get)); // 最大值，结果为3
		Integer minValue = list.stream()
				.collect(Collectors.collectingAndThen(Collectors.minBy((a, b) -> a - b), Optional::get)); // 最小值，结果为1
		Integer sumValue = list.stream().collect(Collectors.summingInt(item -> item)); // 求和，结果为6
		Double avg = list.stream().collect(Collectors.averagingDouble(x -> x)); // 平均值，结果为2.0
		System.out.println(maxValue + " " + minValue + " " + sumValue + " " + avg);
	}

	private void testCollection() {
		String collect = Stream.of("a", "b", "c")
				.collect(Collectors.mapping(x -> x.toUpperCase(), Collectors.joining(","))); // 结果为A,B,C
		System.out.println(collect);
	}

	public void testReduce1() {
		int[] arr = { 1, 2, 3, 4 };

		// 1、一个参数：Optional<T> reduce(BinaryOperator<T> accumulator)
		int max = Arrays.stream(arr).reduce((a, b) -> a > b ? a : b).getAsInt(); // 求最大值，结果为4
		int sum = Arrays.stream(arr).reduce((a, b) -> a + b).getAsInt(); // 求总和，结果为10
		System.out.println(max + " " + sum);

		// 2、两个参数：T reduce(T identity, BinaryOperator<T> accumulator)方法
		int initSum = 5; // 相当于在Stream的基础上多了一个初始化的值
		int totalSum = Arrays.stream(arr).reduce(initSum, (a, b) -> a + b); // 有初始值5，求总和，结果为15
		System.out.println(totalSum);

		Stream<String> s = Stream.of("bb", "cc", "dd", "ee");
		String reduce2 = s.reduce("aa", (a, b) -> a.concat(b)); // 有初始值aa，拼接后结果为aabbccddee
		System.out.println(reduce2);

		// 看不懂！！！
		// 3、三个参数：<U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator,
		// BinaryOperator<U> combiner)
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		Integer reduce = list.stream().reduce(5, (result, x) -> result + x, (result, b) -> result * b);
		System.out.println(reduce);
	}

	private void testReduce2() {
		List<User> list = new ArrayList<>();
		list.add(new User("testAA1", 23));
		list.add(new User("testAA2", 43));
		list.add(new User("testAA3", 33));
		list.add(new User("testAA4", 25));

		// 计算总成绩
		int sum = list.stream().mapToInt(User::getGrade).reduce(0, (a, b) -> a + b);
		System.out.println("团队总成绩：" + sum);
	}

	private void testReduce3() {
		// 解释：相当于result为 1，b为x+1，遍历数组每个元素+1后与result相乘
		Integer sum = Stream.of(1, 3, 4).collect(Collectors.reducing(1, x -> x + 1, (result, b) -> result * b));
		System.out.println("总和：" + sum);
	}
}
