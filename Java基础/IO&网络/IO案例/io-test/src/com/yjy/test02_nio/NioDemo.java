package com.yjy.test02_nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class NioDemo {
	public static void main(String[] args) throws Exception {
		testBuffer(); // 测试缓存Buffer
		testChannel(); // 测试通道Channel

		// 使用NIO的WatchService监控文件系统变化
		testWatchService();

		// 利用NIO来遍历文件，基于事件驱动的方式遍历文件
		testFileVisitor();
		
		// 使用MappedByteBuffer进行大文件读写
		testMappedByteBuffer();
	}

	// 测试Buffer
	private static void testBuffer() {
		// 1.分配一个指定大小的缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		System.out.println("分配一个指定大小的缓冲区：allocate()");
		System.out.println(
				String.format("capacity:%s，position：%s，limit：%s", buf.capacity(), buf.position(), buf.limit()));
		// 2.写数据：将数据存入缓冲区中
		System.out.println("写数据：将数据存入缓冲区中：put()");
		buf.put("abcd".getBytes());
		System.out.println(
				String.format("capacity:%s，position：%s，limit：%s", buf.capacity(), buf.position(), buf.limit()));

		// 3.切换为读数据
		System.out.println("切换为读数据：flip()");
		buf.flip();
		System.out.println(
				String.format("capacity:%s，position：%s，limit：%s", buf.capacity(), buf.position(), buf.limit()));

		// 4.读数据：读取缓冲区的数据
		System.out.println("读数据：读取缓冲区的数据：get()");
		byte[] dst = new byte[buf.limit()];
		buf.get(dst);
		System.out.println(new String(dst, 0, dst.length));
		System.out.println(
				String.format("capacity:%s，position：%s，limit：%s", buf.capacity(), buf.position(), buf.limit()));

		// 5.rewind():可重复读
		System.out.println("可重复读：rewind()");
		buf.rewind();
		System.out.println(
				String.format("capacity:%s，position：%s，limit：%s", buf.capacity(), buf.position(), buf.limit()));

		// 6.clear()：清空缓冲区，但缓冲区中的数据仍然存在
		System.out.println("清空缓冲区，但缓冲区中的数据仍然存在：clear()");
		buf.clear();
		System.out.println(
				String.format("capacity:%s，position：%s，limit：%s", buf.capacity(), buf.position(), buf.limit()));
	}

	// 测试Channel通道
	private static void testChannel() throws IOException {
		File srcFile = new File(
				"D:\\gitManager\\gitCode\\document\\Java基础\\IO\\IO案例\\io-test\\src\\com\\yjy\\test02_nio\\test.txt");
		FileInputStream fis = new FileInputStream(srcFile);
		FileOutputStream fos = new FileOutputStream(new File("D:\\nio2.txt"));

		FileChannel inChannel = fis.getChannel();
		FileChannel outChannel = fos.getChannel();

		ByteBuffer buffer = ByteBuffer.allocate(1024);

		// 测试1：控制台打印
		// // 将inChannel中的数据读取到Buffer中
		// int len = inChannel.read(buffer);
		// byte[] bys = buffer.array();
		// System.out.println(new String(bys, 0, len));

		// 测试2：写入其它文件中
		while ((inChannel.read(buffer)) != -1) {
			buffer.flip(); // 为取出数据做好准备
			outChannel.write(buffer);
			buffer.clear();
		}

		fis.close();
		fos.close();
	}

	// 使用NIO的WatchService监控文件系统变化
	private static void testWatchService() throws IOException, InterruptedException {
		// 获取到文件系统的WatchService对象
		WatchService watchService = FileSystems.getDefault().newWatchService();

		Paths.get("D:\\TestTest").register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY,
				StandardWatchEventKinds.OVERFLOW);
		// 通过wachService来监听文件系统
		while (true) {
			WatchKey key = watchService.take();
			List<WatchEvent<?>> pollEvents = key.pollEvents();
			for (WatchEvent<?> watchEvent : pollEvents) {
				System.out.println(watchEvent.context() + "发生了" + watchEvent.kind() + "事件");
			}

			boolean reset = key.reset();
			if (!reset) {
				break;
			}
		}
	}

	// 利用NIO来遍历文件，基于事件驱动的方式遍历文件
	private static void testFileVisitor() throws IOException {
		FileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) throws IOException {
				System.out.println("正准备访问" + path + "文件");
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
				System.out.println("正在访问" + path + "文件");
				if (path.endsWith("nio2.txt")) {
					System.out.println("恭喜你找到了我想要的Java文件,你可以停止查找了");
					return FileVisitResult.TERMINATE;
				}
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path path, IOException exc) throws IOException {
				return FileVisitResult.SKIP_SIBLINGS;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path path, IOException exc) throws IOException {
				return FileVisitResult.CONTINUE;
			}
		};
		Files.walkFileTree(Paths.get("D:\\TestTest"), visitor);
	}

	/**
	 * 大文件读写操作测试
	 * 方式1测试：927744，Read time :16ms，Write time :9ms
	 * 方式2测试：927744，Read time :1ms，Write time :9ms
	 */
	private static void testMappedByteBuffer() throws IOException {
		ByteBuffer byteBuf = ByteBuffer.allocate(1024 * 14 * 1024);
		byte[] bbb = new byte[14 * 1024 * 1024];
		FileInputStream fis = new FileInputStream("E:\\ruanjian\\VMware安装包\\CentOS-7-x86_64-Minimal-1804.iso");
		FileOutputStream fos = new FileOutputStream("E:\\ruanjian\\VMware安装包\\outFile2.txt");
		FileChannel fc = fis.getChannel();
		long timeStar = System.currentTimeMillis();// 得到当前的时间
//		fc.read(byteBuf);// 方式1：读取文件
		MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()); //方式2：读取文件	
		System.out.println("文件大小" + (fc.size() / 1024));
		long timeEnd = System.currentTimeMillis();// 得到当前的时间
		System.out.println("Read time :" + (timeEnd - timeStar) + "ms");
		timeStar = System.currentTimeMillis();
		fos.write(bbb);// 2.写入
		// mbb.flip();
		timeEnd = System.currentTimeMillis();
		System.out.println("Write time :" + (timeEnd - timeStar) + "ms");
		fos.flush();
		
		fc.close();
		fis.close();
	}
}
