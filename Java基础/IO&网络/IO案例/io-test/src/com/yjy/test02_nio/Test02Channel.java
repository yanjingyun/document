package com.yjy.test02_nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

/**
 * 一、通道（Channel）：用于源节点与目标节点的连接。在Java NIO中负责缓冲区中数据的传输。channel本身不存储数据，因此需要配合缓冲区（Buffer）进行传输。
 * 
 * 二、通道的主要实现类：
 * java.nio.channels.Channel 接口：
 * 		|--FileChannel
 * 		|--SocketChannel
 * 		|--ServerSocketChannel
 * 		|--DatagramChannel
 * 第一个用于本地文件传输，后面三个用于网络传输。
 * 
 * 三、获取通道：
 * 1、Java针对支持通道的类提供了getChannel()方法
 * 		本地IO：
 * 		FileInputStream/FileOutputStream
 * 		RandomAccessFile
 * 
 * 		网络IO：
 * 		Socket
 * 		ServerSocket
 * 		DatagramSocket
 * 
 * 2、在JDK1.7中的NIO2针对各个通道提供了静态方法open()
 * 3、在JDK1.7中的NIO2的Files工具类的newByteChannel()
 * 
 * 四、通道之间的数据传输
 * transferFrom()
 * transferTo()
 * 
 * 五、分散（Scatter）与聚集（Gather）
 * 分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）：将多个缓冲区聚集到通道中
 * 
 * 六、字符集：Charset
 * 编码：字符串 -> 字节数组
 * 节码：字节数组 -> 字符串
 */
public class Test02Channel {
	
	/** 分散（Scatter）与聚集（Gather） */
	@Test
	public void test4() throws IOException {
		RandomAccessFile raf1 = new RandomAccessFile("学习目标.txt", "rw");
		
		// 1.获取通道
		FileChannel channel1 = raf1.getChannel();
		
		// 2.分配指定大小的缓冲区
		ByteBuffer buf1 = ByteBuffer.allocate(100);
		ByteBuffer buf2 = ByteBuffer.allocate(1024);
		
		// 3.分散读取
		ByteBuffer[] bufs = {buf1, buf2};
		channel1.read(bufs);
		
		for (ByteBuffer byteBuffer : bufs) {
			byteBuffer.flip();
		}
		
		System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
		System.out.println("-----------------------");
		System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));
		
		
		
		// 4.聚集写入
		RandomAccessFile raf2 = new RandomAccessFile("学习目标2.txt", "rw");
		FileChannel channel2 = raf2.getChannel();
		channel2.write(bufs);
		
		raf1.close();
		raf2.close();
	}
	
	/** 通道之间的数据传输(直接缓冲区) */
	@Test
	public void test3() throws IOException {
		FileChannel inChannel = FileChannel.open(Paths.get("E:\\ruanjian\\VMware安装包\\CentOS-7-x86_64-Minimal-1804.iso"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("E:\\4.iso"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
		
		// 两条语句差不多
//		inChannel.transferTo(0, inChannel.size(), outChannel);
		outChannel.transferFrom(inChannel, 0, inChannel.size());
	}
	
	/** 使用直接缓冲区完成文件的复制（内存映射文件）-大文件写入很快 */
	@Test
	public void test2() throws IOException { //耗时：586
		long start = System.currentTimeMillis();
		
		FileChannel inChannel = FileChannel.open(Paths.get("E:\\ruanjian\\VMware安装包\\CentOS-7-x86_64-Minimal-1804.iso"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("E:\\3.iso"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
		
		// 内存映射文件（物理内存中）
		MappedByteBuffer inMapperBuffer = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
		MappedByteBuffer outMapperBuffer = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());

		// 直接对缓冲区进行数据的读写操作
		byte[] dst = new byte[inMapperBuffer.limit()];
		outMapperBuffer.put(dst);
		
		inChannel.close();
		outChannel.close();
		
		System.out.println("耗时：" + (System.currentTimeMillis() - start));
	}

	/** 利用通道完成文件的复制(非直接缓冲区) */
	@Test
	public void test1() { //耗时：5553
		long start = System.currentTimeMillis();
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			
			fis = new FileInputStream("E:\\ruanjian\\VMware安装包\\CentOS-7-x86_64-Minimal-1804.iso");
			fos = new FileOutputStream("E:\\2.iso");
			
			// 1.获取通道
			inChannel = fis.getChannel();
			outChannel = fos.getChannel();
			
			// 2.分配指定大小的缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			
			// 3.将通道中的数据存入缓冲区中
			while (inChannel.read(buffer) != -1) {
				buffer.flip(); // 切换读取数据的模式
				// 4.将缓冲区中的数据写入通道中
				outChannel.write(buffer);
				buffer.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outChannel != null) {
				try {
					outChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (inChannel != null) {
				try {
					inChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("耗时：" + (System.currentTimeMillis() - start));
	}
	
}
