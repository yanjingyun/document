com.yjy.bio //BIO实现Socket通信(有两处阻塞)
	--测试1：一个服务端&一个客户端情况
	1.启动BioServer，accept方法会阻塞（等待客户端连接）
	2.启动BioClient，这时候执行BioServer#accept方法，但read方法进行了阻塞（等待客户端输入）
	3.客户端输入传输内容后，服务端能看到测试内容
	--测试2：一个服务端&两个客户端情况
	启动一个服务端后，连续启动两个客户端，能看到服务端只有一个连接请求
	总结：服务端只有一个线程，而且accept方法和read方法都会阻塞线程，因此只能处理一个客户端的请求完后才能处理下一个。

com.yjy.test01_bio_v2 //BIO实现Socket通信（多线程实现）
	测试：一个服务端&两个客户端情况
	启动一个服务端后，连续启动两个客户端，能看到服务端有两个连接请求打印
	总结：优化了服务端的read阻塞而不能处理新连接请求的情况


com.yjy.test02_nio //测试NIO相关概念
	testBuffer(); //测试Buffer
	testChannel(); // 测试通道Channel
	testWatchService(); // 使用NIO的WatchService监控文件系统变化
	testFileVisitor(); // 利用NIO来遍历文件，基于事件驱动的方式遍历文件
	testMappedByteBuffer() // 使用MappedByteBuffer进行大文件读写
		MappedByteBuffer buffer = new RandomAccessFile(filePath);
		描述：内存映射文件首先将外存上的文件映射到内存中的一块连续区域，被当成一个字节数组进行处理，读写操作直接对内存进行操作，而后再将内存区域重新映射到外存文件，这就节省了中间频繁的对外存进行读写的时间，大大降低了读写时间。



>>>NIO好处：只要一个Selector就可以监听注册到其上的所有Channel，通过轮询准备好的Channel实现单线程处理多客户端请求
com.yjy.test02_nio_server_client // NIO实现Socket通信(处理BIO的两处阻塞)
	测试：
		启动服务端，再连续启动两个客户端，三个服务都能正常运行；
		过一段时间关闭某个客户端，能看到服务端会抛出一个异常，但还是能正常运行的。



com.yjy.test03_reactor	// 测试reactor模型
	理论：https://www.cnblogs.com/hyy9527/p/13064347.html
	v1版本：单reactor单线程
	v2版本：单reactor多线程
	v3版本：主从reactor多线程（未开发）



