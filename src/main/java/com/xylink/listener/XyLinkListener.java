package com.xylink.listener;

import com.xylink.config.SDKConfigMgr;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class XyLinkListener implements ApplicationListener<ApplicationReadyEvent> {

    private static String API_HOST = "https://sdkapi.xylink.com";
    private static String CLIENT_ID = "MaOH94G6kxffqg7hHEQXhZQc";
    private static String CLIENT_SECRET = "xvUR0FyCTLVSbenosoBh8SDaVOQ5VQcG";
    private static String ENTERPRISE_ID = "28617ab154b37d33c23ed5139b57a6dc2d8f5dc2";


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        applicationContext.getEnvironment();
        log.info("XyLinkListener start");
        SDKConfigMgr.initSDKConfig(API_HOST,CLIENT_ID,CLIENT_SECRET,ENTERPRISE_ID);
    }


    public static void main(String[] args) {
        Long num = 3565400000L;
        int i = num.hashCode();
        System.out.println(i);

    }

}
