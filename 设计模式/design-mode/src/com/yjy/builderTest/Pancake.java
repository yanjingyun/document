package com.yjy.builderTest;

public class Pancake {
    public Double price;   //价格
    public String name;    //名称

    private Pancake() {
        this.price = 3.00;
        this.name = "煎饼果子";
    }

    // 构造者(老板)
    public static class Builder {
        private Pancake pancake = new Pancake();

        public Builder addCaraway() {
            pancake.price += 0.5;
            pancake.name += "\n加香菜";
            return this;
        }

        public Builder addShallot() {
            pancake.price += 0.5;
            pancake.name += "\n加葱末";
            return this;
        }

        public Builder addHam() {
            pancake.price += 1.5;
            pancake.name += "\n加火腿";
            return this;
        }

        public Builder addEgg() {
            pancake.price += 2;
            pancake.name += "\n加鸡蛋";
            return this;
        }

        public Builder addLettuce() {
            pancake.price += 0.5;
            pancake.name += "\n加生菜";
            return this;
        }

        public Builder addChine() {
            pancake.price += 2;
            pancake.name += "\n加里脊肉";
            return this;
        }

        public Builder addPepper() {
            pancake.price += 0.5;
            pancake.name += "\n加辣椒";
            return this;
        }

        public Builder addCracknel() {
            pancake.price += 1.0;
            pancake.name += "\n加脆饼";
            return this;
        }

        public Pancake builder() {
            return pancake;
        }
    }
}
