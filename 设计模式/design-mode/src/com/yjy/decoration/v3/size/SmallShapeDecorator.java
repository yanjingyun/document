package com.yjy.decoration.v3.size;

import com.yjy.decoration.v3.Shape;
import com.yjy.decoration.v3.ShapeDecorator;

public class SmallShapeDecorator extends ShapeDecorator {

    public SmallShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public String draw() {
       return "小的" +  decoratedShape.draw();
    }
}