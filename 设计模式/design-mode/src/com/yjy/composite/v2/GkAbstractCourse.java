package com.yjy.composite.v2;

/**
 * 顶层抽象组件
 */
public abstract class GkAbstractCourse {
    protected String name;
    protected String score;

    public GkAbstractCourse(String name, String score) {
        this.name = name;
        this.score = score;
    }

    public abstract void info();
}