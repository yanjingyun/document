package com.yjy.strategy2.v3;

public class Client {
    public static void main(String[] args) {
        Player player = new Player();
        player.buy(5000D);
        System.out.printf("玩家为%s \t累计消费：%s元 \t原价：%s元 \t折扣价：%s元%n", player.memberType(), player.getTotalAmount(), 5000D, player.calLastAmount());
        player.buy(12000D);
        System.out.printf("玩家为%s \t累计消费：%s元 \t原价：%s元 \t折扣价：%s元%n", player.memberType(), player.getTotalAmount(), 12000D, player.calLastAmount());
        player.buy(12000D);
        System.out.printf("玩家为%s \t累计消费：%s元 \t原价：%s元 \t折扣价：%s元%n", player.memberType(), player.getTotalAmount(), 12000D, player.calLastAmount());
        player.buy(12000D);
        System.out.printf("玩家为%s \t累计消费：%s元 \t原价：%s元 \t折扣价：%s元%n", player.memberType(), player.getTotalAmount(), 12000D, player.calLastAmount());
    }
}