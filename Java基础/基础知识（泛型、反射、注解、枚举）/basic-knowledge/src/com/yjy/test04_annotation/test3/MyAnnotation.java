package com.yjy.test04_annotation.test3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER) //ÐÞÊÎ²ÎÊý
public @interface MyAnnotation {

	String value();
}
