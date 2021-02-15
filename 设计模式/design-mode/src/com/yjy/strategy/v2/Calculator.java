package com.yjy.strategy.v2;

public class Calculator {//计算器类
	private Strategy strategy;//拥有某种算法策略

	public void setStrategy(Strategy strategy) {//接入算法策略
		this.strategy = strategy;
	}

	public int getResult(int a, int b){
		return this.strategy.calculate(a, b);//返回具体策略的结果
	}
}