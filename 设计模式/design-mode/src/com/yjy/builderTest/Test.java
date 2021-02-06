package com.yjy.builderTest;

public class Test {
    public static void main(String[] args) {
        Pancake pancake = new Pancake.Builder()
                .addCaraway()
                .addChine()
                .addCracknel()
                .addEgg()
                .builder();
        System.out.println(pancake.name);
        System.out.println("总价：" + pancake.price + "元");
    }
}
