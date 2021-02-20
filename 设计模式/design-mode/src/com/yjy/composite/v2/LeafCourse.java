package com.yjy.composite.v2;

/**
 * 叶子节点
 */
public class LeafCourse extends GkAbstractCourse {

    public LeafCourse(String name, String score) {
        super(name, score);
    }

    @Override
    public void info() {
        System.out.println("课程:" + this.name + ",分数:" + this.score);
    }
}