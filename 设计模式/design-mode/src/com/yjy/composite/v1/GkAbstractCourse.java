package com.yjy.composite.v1;

/**
 * 顶层抽象组件
 */
public abstract class GkAbstractCourse {
    public void addChild(GkAbstractCourse course) {
        System.out.println("不支持添加操作");
    }

    public String getName() throws Exception {
        throw new Exception("不支持获取名称");
    }

    public void info() throws Exception {
        throw new Exception("不支持查询信息操作");
    }
}