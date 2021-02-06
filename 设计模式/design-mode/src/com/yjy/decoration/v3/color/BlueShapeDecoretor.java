package com.yjy.decoration.v3.color;

import com.yjy.decoration.v3.Shape;
import com.yjy.decoration.v3.ShapeDecorator;

public class BlueShapeDecoretor extends ShapeDecorator {

    public BlueShapeDecoretor(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public String draw() {
        return "蓝色的" +  decoratedShape.draw();
    }
}
