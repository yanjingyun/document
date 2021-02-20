package com.yjy.broker;

import java.util.HashMap;
import java.util.Map;

public class RegisterCenter {
    Map<String, AbstractService> map = new HashMap<>();

    public void registered(AbstractService service) {
        map.put(service.getClass().getSimpleName(), service);
    }

    public void call(Class clazz) {
        AbstractService service = map.get(clazz.getSimpleName());
        if (service == null) {
            System.out.println(clazz.getSimpleName() + "服务不存在！");
            return;
        }
        service.doSomething();
    }
}