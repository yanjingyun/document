package com.yjy.decoration.v3.size;

import com.yjy.decoration.v3.Shape;
import com.yjy.decoration.v3.ShapeDecorator;

public class MediumShapeDecorator extends ShapeDecorator {

    public MediumShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public String draw() {
       return "中等的" +  decoratedShape.draw();
    }
}