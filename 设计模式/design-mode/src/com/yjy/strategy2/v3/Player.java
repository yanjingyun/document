package com.yjy.strategy2.v3;

/**
 * 客户类，帮我们完成会员升级的功能
 */
public class Player {
    private Double totalAmount = 0D;//客户在鹅厂消费的总额
    private Double amount = 0D;//客户单次消费金额
    private CalPrice calPrice; //每个客户都有一个计算价格的策略

    //客户购买皮肤，就会增加它的总额，金额达到一定程度会升级会员等级
    public void buy(Double amount) {
        this.amount = amount;
        totalAmount += amount;

//        if (totalAmount > 30000) {//30000则改为金牌会员计算方式
//            calPrice = new GoldVip();
//        } else if (totalAmount > 20000) {//类似
//            calPrice = new SuperVip();
//        } else if (totalAmount > 10000) {//类似
//            calPrice = new Vip();
//        } else {
//            calPrice = new Orgnic();
//        }
        /* 修改点，我们将策略的制定转移给了策略工厂，将这部分责任分离出去 */
        calPrice = CalPriceFactory.createCalPrice(this);
    }

    public String memberType() {
        return calPrice.menberType();
    }
    //计算客户最终要付的钱
    public Double calLastAmount() {
        return calPrice.calPrice(amount);
    }

    public Double getTotalAmount() {
        return totalAmount;
    }
}