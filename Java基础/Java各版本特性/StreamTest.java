package com.yjy;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 测试jdk1.8新特性：stream
public class StreamTest {

	public static void main(String[] args) {
		StreamTest st = new StreamTest();
		// 测试最大值、最小值、综合、排列和遍历、过滤
		st.test1();
		
		// 测试map相关
		st.testToMap();
		st.testUserMap();
		st.testUserSort();
		st.testUserMap3();
		st.testPersonMap();
		st.testPerson2Map();
		
		// 测试Collectors.groupingBy
		st.testGroupingBy1();
		st.testGroupingBy2();
		st.testGroupingBy3();
		
		// 测试Collectors.mapping
		st.testCollection();
		
		// 测试reduce()
		st.testReduce();
	}
	
	// 测试最大值、最小值、综合、排列和遍历、过滤
	public void test1() {
		List<Integer> list = Arrays.asList(2,3,5,8,6);
		list.stream().min(Integer::compareTo).ifPresent(System.out::println); // 最小值
		list.stream().max(Integer::compareTo).ifPresent(System.out::println); // 最大值
		list.stream().reduce((a, b) -> a + b).ifPresent(System.out::println); // 总和
		list.stream().sorted().forEach(e -> System.out.print(e + " ")); // 排序和遍历
		System.out.println();
		list.stream().filter(e -> e > 3 && e < 8).forEach(e -> System.out.print(e + " ")); // 过滤和遍历
		System.out.println();
		
	}
	
	// 测试map
	public void testToMap() {
		Map<String, String> map = Stream.of(1,2,3,4,5).map(i -> i * 10).collect(Collectors.toMap(key -> "key" + key/10, value -> "value:" + value));
		System.out.println(map);

		TreeSet<Integer> treeSet = Stream.of(1, 3, 4).collect(Collectors.toCollection(TreeSet::new));
		System.out.println(treeSet);
	}
	
	// 测试mapToInt()
	private void testUserMap() {
		List<User> list = new ArrayList<>();
		list.add(new User("testAA1", 23));
		list.add(new User("testAA2", 43));
		list.add(new User("testAA3", 33));
		list.add(new User("testAA4", 25));
		
		// 计算总成绩
		int sum = list.stream().mapToInt(User::getGrade).reduce(0, (a, b) -> a + b);
		System.out.println("团队总成绩：" + sum);
	}
	
	static class User {
		private String name;
		private int grade;
		public User(String name, int grade) {
			this.name = name;
			this.grade = grade;
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
	}
	
	
	public void testPersonMap() {
		List<Person> personList = Arrays.asList(
				new Person(1, "testAA1"),
				new Person(2, "testAA1"),
				new Person(3, "testAA1"));
		Map<Integer, Person> personMap = personList.stream().collect(Collectors.toMap(Person::getId, Function.identity()));
		System.out.println(personMap.get(1));
		
		Map<Integer, String> personnameMap = personList.stream().collect(Collectors.toMap(Person::getId, Person::getName));
		System.out.println(personnameMap.get(1));
	}

	public static class Person {
		private Integer id;
		private String name;
		public Person(Integer id, String name) {
			this.id = id;
			this.name = name;
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
	}
	
	public void testPerson2Map() {
		List<Person2> list = new ArrayList<>();
		list.add(new Person2(1, "TestAA1", 23));
		list.add(new Person2(2, "TestAA2", 33));
		list.add(new Person2(3, "TestAA3", 55));
		list.add(new Person2(4, "TestAA4", 44));
		list.add(new Person2(5, "TestAA5", 55));
		
		// 姓名组成新集合
		List<String> nameList = list.stream().map(Person2::getName).collect(Collectors.toList());
		nameList.forEach(e -> System.out.print(e + " "));
		System.out.println();
		
		// id组成新集合
		List<Integer> idList = list.stream().map(Person2::getId).collect(Collectors.toList());
		idList.forEach(e -> System.out.print(e + " "));
		System.out.println();
		
		// list转map，key为id，value为Person2对象
		Map<Integer, Person2> map1 = list.stream().collect(Collectors.toMap(Person2::getId, Person2 -> Person2));
		System.out.println(map1.toString());
		// list转map，key为id，value为name
		Map<Integer, String> map2 = list.stream().collect(Collectors.toMap(Person2::getId, Person2::getName));
		System.out.println(map2.toString());
		// list转map，key为age，value为List<Person2>集合
		Map<Integer, List<Person2>> map3 = list.stream().collect(Collectors.groupingBy(Person2::getAge));
		System.out.println(map3.toString());
		
		int minAge = list.stream().mapToInt(Person2::getAge).min().getAsInt();
		System.out.println("最小年龄：" + minAge);
		int maxAge = list.stream().mapToInt(Person2::getAge).max().getAsInt();
		System.out.println("最大年龄：" + maxAge);
		double avgAge = list.stream().mapToInt(Person2::getAge).average().getAsDouble();
		System.out.println("平均年龄：" + avgAge);
		
		long count = list.stream().filter(p -> p.getAge() > 25 && "TestAA2".equals(p.getName())).count();
		System.out.println("人数为：" + count);
		
		String[] arr = {"scsa","bi","a","cd","ca156165"};
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
		
		Arrays.stream(arr).sorted(Comparator.comparing(this::charAt).thenComparing(String::length)).forEach(System.out::println);
		
		list.stream().sorted(Comparator.comparing(Person2::getAge).thenComparing(Person2::getName)).forEach(System.out::println);
		
		String name = list.stream().sorted(Comparator.comparing(x -> x.getAge())).findFirst().get().getName();
		System.out.println(name);
	}
	public char charAt(String x){
        return x.charAt(0);
    }
	public static class Person2 {
		private Integer id;
		private String name;
		private Integer age;
		public Person2(Integer id, String name, Integer age) {
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
			return "Person22 [id=" + id + ", name=" + name + ", age=" + age + "]";
		}
	}

	// 测试User排列
	private List<String> testUserSort() {
		// 需求：1、小于18岁的人  2、按照出生日期排序 3、获取姓名
		List<User2> list = new ArrayList<>();
		list.add(new User2("aa", Date.valueOf("2019-05-05"), 15));
		list.add(new User2("bb", Date.valueOf("2019-05-04"), 18));
		list.add(new User2("cc", Date.valueOf("2019-05-03"), 20));
		list.add(new User2("dd", Date.valueOf("2019-05-02"), 23));
		list.add(new User2("ee", Date.valueOf("2019-05-01"), 25));
		return list.stream().filter(u -> u.getAge() <= 20).sorted(User2::compareByBirthday).map(User2::getName).collect(Collectors.toList());
	}
	static class User2 {
		private String name;
		private Date birthday;
		private int age;
		
		public User2(String name, Date birthday, int age) {
			this.name = name;
			this.birthday = birthday;
			this.age = age;
		}
		
		public static int compareByBirthday(User2 a, User2 b) {
			return a.getBirthday().compareTo(b.getBirthday());
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Date getBirthday() {
			return birthday;
		}
		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
	}
	
	private void testUserMap3() {
		User3[] userArray = {
				new User3("testAA1", Date.valueOf("2018-05-01")),
				new User3("testAA3", Date.valueOf("2018-05-03")),
				new User3("testAA2", Date.valueOf("2018-05-02")),
				new User3("testAA4", Date.valueOf("2018-05-05")),
				new User3("testAA5", Date.valueOf("2018-05-05"))};
		// Arrays.sort(userArray, (a, b) -> a.getBirthday().compareTo(b.getBirthday())); //方式一
		// Arrays.sort(userArray, (a, b) -> User.compareByBirthday(a, b)); //方式二
		Arrays.sort(userArray, User3::compareByBirthday); //方式三：静态方法引用
		System.out.println(Arrays.asList(userArray));
	}
	static class User3 {
		private String name;
		private Date birthday;
		
		public User3(String name, Date birthday) {
			this.name = name;
			this.birthday = birthday;
		}
		
		public static int compareByBirthday(User3 a, User3 b) {
			return a.getBirthday().compareTo(b.getBirthday());
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Date getBirthday() {
			return birthday;
		}
		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}
		
		@Override
		public String toString() {
			return "User3 [name=" + name + ", birthday=" + birthday + "]";
		}
	}
	
	private void testGroupingBy1() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		// 奇偶数分组：奇数分一组，偶数分一组 {false=[1, 3, 5, 7, 9], true=[2, 4, 6, 8, 10]}
		Map<Boolean, List<Integer>> result = list.stream().collect(Collectors.groupingBy(item -> item % 2 == 0));
		System.out.println(result);

		Map<Boolean, List<Integer>> twoPartiton = list.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));
		System.out.println(twoPartiton);
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

	private void testGroupingBy3() {
		 List<Foo> list = new ArrayList<>(4);
		 list.add(new Foo(1, 2));
		 list.add(new Foo(2, 23));
		 list.add(new Foo(2, 6));
		 Map<Integer, IntSummaryStatistics> collect = list.stream().collect(Collectors.groupingBy(Foo::getType, Collectors.summarizingInt(Foo::getNum)));
		 IntSummaryStatistics statistics1 = collect.get(1);
		 IntSummaryStatistics statistics2 = collect.get(2);
		 System.out.println(statistics1.getSum());
		 System.out.println(statistics2.getSum());
		 System.out.println(statistics2.getAverage());
		 System.out.println(statistics2.getMax());
		 System.out.println(statistics2.getMin());
		 System.out.println(statistics2.getCount());
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
	
	private void testCollection() {
		String collect = Stream.of("a", "b", "c").collect(Collectors.mapping(x -> x.toUpperCase(), Collectors.joining(",")));
		System.out.println(collect);
	}
	
	public void testReduce() {
		int[] arr = {1,2,3,4};
		int sum = Arrays.stream(arr).reduce((a, b) -> a + b).getAsInt();
		int max = Arrays.stream(arr).reduce((a, b) -> a > b ? a : b).getAsInt();
		
		int initSum = 5; // 相当于在Stream的基础上多了一个初始化的值
		int reduce = Arrays.stream(arr).reduce(initSum, (a, b) -> a + b);
		System.out.println(sum + " " + max + " " + reduce);
		
		Stream<String> s = Stream.of("bb", "cc", "dd", "ee");
		String reduce2 = s.reduce("aa", (a, b) -> a.concat(b));
		System.out.println(reduce2);
	}
}
