package com.yjy.strategy.v2;

public class Client { //使用

	public static void main(String[] args) {
        Calculator calculator = new Calculator();//实例化计算器
        calculator.setStrategy(new Addition());//接入加法实现
        int result = calculator.getResult(1, 1);//计算！
        System.out.println(result);//得到的是加法结果2

        calculator.setStrategy(new Subtraction());//再次接入减法实现
        result = calculator.getResult(1, 1);//计算！
        System.out.println(result);//得到的是减法结果0
    	// 分析：注释已经写得非常明白了，相信大家都看懂了吧。那么我们这个计算器可以说是具有算法策略扩展性的，以后要有新的算法是不需要再更改任何现有代码的，只需要新写一个算法比如乘法Multiplication，并实现calculate方法，接下来要做的只是组装上去便可以使用了。
	}
}