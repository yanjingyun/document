package com.yjy.decoration.v3.color;

import com.yjy.decoration.v3.Shape;
import com.yjy.decoration.v3.ShapeDecorator;

public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public String draw() {
       return "红色的" +  decoratedShape.draw();
    }
}