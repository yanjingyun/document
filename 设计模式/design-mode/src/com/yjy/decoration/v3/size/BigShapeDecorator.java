package com.yjy.decoration.v3.size;

import com.yjy.decoration.v3.Shape;
import com.yjy.decoration.v3.ShapeDecorator;

public class BigShapeDecorator extends ShapeDecorator {

    public BigShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public String draw() {
       return "大的" +  decoratedShape.draw();
    }
}