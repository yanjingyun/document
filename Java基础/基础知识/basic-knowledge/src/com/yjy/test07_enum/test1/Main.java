package com.yjy.test07_enum.test1;

import java.util.EnumSet;

public class Main {

	public static void main(String[] args) {
		System.out.println("value=" + WeekEnum.FRI.getValue()); // 输出：value=0

		// 遍历 EnumSet的使用
		EnumSet<WeekEnum> weekSet = EnumSet.allOf(WeekEnum.class);
		for (WeekEnum dayTest : weekSet) {
			System.out.println(dayTest.name() + "：value=" + dayTest.getValue());
		}
	}
}
