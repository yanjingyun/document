package com.yjy.composite.v2;

/**
 * 安全组合模式
 */
public class Client {
    public static void main(String[] args) throws Exception {
        LeafCourse ywCourse = new LeafCourse("语文", "150");
        LeafCourse sxCourse = new LeafCourse("数学", "150");
        LeafCourse yyCourse = new LeafCourse("英语", "150");

        LeafCourse wlCourse = new LeafCourse("物理", "110");
        LeafCourse hxCourse = new LeafCourse("化学", "100");
        LeafCourse swCourse = new LeafCourse("生物", "90");

        LevelCourse lzCourse = new LevelCourse("理综", "300", 2);
        lzCourse.addChild(wlCourse);
        lzCourse.addChild(hxCourse);
        lzCourse.addChild(swCourse);

        LevelCourse gkCourse = new LevelCourse("理科高考", "750", 1);
        gkCourse.addChild(ywCourse);
        gkCourse.addChild(sxCourse);
        gkCourse.addChild(yyCourse);

        gkCourse.addChild(lzCourse);
        gkCourse.info();
    }
}