package com.xylink.test.event;

import lombok.Data;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * @ClassName MessageEvent
 * @Description TODO
 * @Author liuqihang
 * @Date 2022/7/25 15:05
 */
@Data
@ToString
public class MessageEvent extends ApplicationEvent {

    private String bizId;

    public MessageEvent(Object source, String bizId) {
        super(source);
        this.bizId = bizId;
    }
}
