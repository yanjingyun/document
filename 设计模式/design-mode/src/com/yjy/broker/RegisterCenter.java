package com.yjy.broker;

import java.util.HashMap;
import java.util.Map;

public class RegisterCenter {
    Map<String, AbstractService> map = new HashMap<>();

    public void registered(AbstractService service) {
        map.put(service.getClass().getSimpleName(), service);
    }

    public void call(String serviceName) {
        AbstractService service = map.get(serviceName);
        if (service == null) {
            System.out.println(serviceName + "服务不存在！");
            return;
        }
        service.doSomething();
    }
}