package com.yjy.decoration.v3;

import com.yjy.decoration.v3.color.BlueShapeDecoretor;
import com.yjy.decoration.v3.color.RedShapeDecorator;
import com.yjy.decoration.v3.size.BigShapeDecorator;
import com.yjy.decoration.v3.size.MediumShapeDecorator;
import com.yjy.decoration.v3.size.SmallShapeDecorator;

import java.io.FileNotFoundException;

public class DecoratorPatternDemo {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>未使用装饰器>>>>>>>>>>>>>>>>>>>>>");
        Shape circle = new Circle();
        System.out.println(circle.draw());

        System.out.println(">>>>>>>>>>>>>>>>>>>>>使用装饰器>>>>>>>>>>>>>>>>>>>>>");
        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        System.out.println(redCircle.draw());

        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println(redRectangle.draw());

        ShapeDecorator blueRectangle = new BlueShapeDecoretor(new Rectangle());
        System.out.println(blueRectangle.draw());

        System.out.println(">>>>>>>>>>>>>>>>>>>>>使用多个装饰器>>>>>>>>>>>>>>>>>>>>>");
        ShapeDecorator bigBlueRectangle = new BigShapeDecorator(new BlueShapeDecoretor(new Rectangle()));
        System.out.println(bigBlueRectangle.draw());

        ShapeDecorator mediumBlueRectangle = new MediumShapeDecorator(new BlueShapeDecoretor(new Rectangle()));
        System.out.println(mediumBlueRectangle.draw());

        System.out.println(">>>>>>>>>>>>>>>>>>>>>多个装饰器顺序改变>>>>>>>>>>>>>>>>>>>>>");
        ShapeDecorator SmallBlueCircle = new SmallShapeDecorator(new BlueShapeDecoretor(new Circle()));
        System.out.println(SmallBlueCircle.draw());

        ShapeDecorator BlueSmallCircle = new BlueShapeDecoretor(new SmallShapeDecorator(new Circle()));
        System.out.println(BlueSmallCircle.draw());
    }
}