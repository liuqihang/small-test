package com.xylink.test.event;

import cn.hutool.core.date.StopWatch;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName SmsListener
 * @Description TODO
 * @Author liuqihang
 * @Date 2022/7/25 15:07
 */
@Slf4j
@Component
public class SmsListener implements ApplicationListener<MessageEvent> {

    @SneakyThrows
    @Override
    public void onApplicationEvent(MessageEvent event) {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        Thread.sleep(2123);
        String bizId = event.getBizId();

        stopWatch.stop();
        log.info("{}：校验订单商品价格耗时：({})毫秒", bizId, stopWatch.getTotalTimeMillis());
    }
}
