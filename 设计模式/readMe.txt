


com.yjy.responsibilityChain //测试责任链
	v1版本：无设计模式完成 员工、经理、CEO的审批流程
	v2版本：使用责任链实现

com.yjy.responsibilityChain2 // 测试责任链
	v1版本：测试责任链进行用户登录的一些列校验
		校验顺序 验证码->用户名密码-》是否重复登录
		缺点：需要手动指定顺序
	v2版本：和建造者模式联用
		在Handler类添加一个Builder静态内部类，用于处理v1版本手动指定顺序的问题。








