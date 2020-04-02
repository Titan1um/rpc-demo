package com.jun.demo;

import com.jun.demo.Interface.Service;

public class ServiceImpl implements Service {

    public String touchFish(String name) {
        return name + " is touching fish!!!!";
    }

}
