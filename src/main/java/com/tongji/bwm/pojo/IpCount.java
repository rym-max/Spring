package com.tongji.bwm.pojo;

import lombok.Data;

@Data
public class IpCount {
    private String IPAddress;
    private int Count;

    public IpCount() {
    }

    public IpCount(String IPAddress, int count) {
        this.IPAddress = IPAddress;
        Count = count;
    }
}
