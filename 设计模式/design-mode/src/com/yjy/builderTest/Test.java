package com.yjy.builderTest;

public class Test {
    public static void main(String[] args) {
        Pancake pancake = new Pancake.Builder()
                .addCaraway()
                .addChine()
                .addCracknel()
                .addEgg()
                .build();
        System.out.printf("名称:%s%n 总价:%s元%n", pancake.name, pancake.price);

        Pancake pancake2 = new Pancake.Builder()
                .addEgg()
                .build();
        System.out.println();
        System.out.printf("名称:%s%n 总价:%s元%n", pancake2.name, pancake2.price);
    }
}
