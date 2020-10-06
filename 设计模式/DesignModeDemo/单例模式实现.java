5种实现单例模式方式：饿汉式、双重检查、静态内部类方式、枚举方式
枚举实现：
	传统单例一旦实现序列化接口将不再是Singleton，因为readObject()方法总是返回一个新实例。
	避免多线程同步问题， 还能防止反序列化重新创建新的对象。

/**
 * 方式1：饿汉式-单例模式
 */
public class Singleton {
	private static Singleton singleton = new Singleton();
	private Singleton() {}
	
	public static Singleton getInstance() {
		return singleton;
	}
	
	public static void main(String[] args) {
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		System.out.println(s1 == s2);
	}
}

/**
 * 方式2：双重检查-单例模式
 */
public class Singleton {
	private volatile static Singleton singleton; //注意volatile
	private Singleton() {}
	
	public static final Singleton getInstance() {
		if (singleton == null) {
			synchronized (Singleton.class) {
				if (singleton == null) {
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
	
	public static void main(String[] args) {
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		System.out.println(s1 == s2);
	}
}

/**
 * 方式3：静态内部类-单例模式
 */
public class Singleton {
	private static class SingletonHolder {
		private static final Singleton singleton = new Singleton();
	}
	private Singleton() {}
	public static final Singleton getInstance() {
		return SingletonHolder.singleton;
	}
	
	public static void main(String[] args) {
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		System.out.println(s1 == s2);
	}
}


/**
 * 方式4：枚举实现
 */
public enum Singleton {
	INSTANCE;
	public void method1() {
		System.out.println("枚举实现单例模式...");
	}
	
	public static void main(String[] args) {
		Singleton s1 = Singleton.INSTANCE;
		Singleton s2 = Singleton.INSTANCE;
		System.out.println(s1 == s2);
		s1.method1();
	}
}
