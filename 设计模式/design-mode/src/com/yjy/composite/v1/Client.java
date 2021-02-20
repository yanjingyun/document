package com.yjy.composite.v1;

/**
 * 透明组合模式
 * 缺陷：叶子节点还是拥有树枝节点的功能，只是调用会报错
 */
public class Client {
    public static void main(String[] args) throws Exception {

        GkAbstractCourse ywCourse = new LeafCourse("语文", "150");
        GkAbstractCourse sxCourse = new LeafCourse("数学", "150");
        GkAbstractCourse yyCourse = new LeafCourse("英语", "150");

        GkAbstractCourse wlCourse = new LeafCourse("物理", "110");
        GkAbstractCourse hxCourse = new LeafCourse("化学", "100");
        GkAbstractCourse swCourse = new LeafCourse("生物", "90");

        GkAbstractCourse lzCourse = new LevelCourse("理综", 2);
        lzCourse.addChild(wlCourse);
        lzCourse.addChild(hxCourse);
        lzCourse.addChild(swCourse);

        GkAbstractCourse lkCourse = new LevelCourse("理科高考科目", 1);
        lkCourse.addChild(ywCourse);
        lkCourse.addChild(sxCourse);
        lkCourse.addChild(yyCourse);
        lkCourse.addChild(lzCourse);

        yyCourse.addChild(wlCourse); // 这种会抛出异常，叶子节点不能拥有子节点

        lkCourse.info(); // 展现所有层级信息
    }
}