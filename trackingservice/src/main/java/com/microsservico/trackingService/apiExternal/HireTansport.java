package com.microsservico.trackingService.apiExternal;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class HireTansport {

    public Long getTrackingCode(){

        long code = ThreadLocalRandom.current().nextLong(10000000L, 100000000L);

        return code;
    }
}
