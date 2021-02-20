package com.yjy.composite.v1;

/**
 * 普通科目类(叶子节点)
 */
public class LeafCourse extends GkAbstractCourse {
    private String name;//课程名称
    private String score;//课程分数

    public LeafCourse(String name, String score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void info() {
        System.out.println("课程:" + this.name + ",分数:" + score);
    }
}