package com.xylink.test.scheme.chainOfResponsibility;

/**
 * @ClassName 责任链模式
 * @Description TODO
 * @Author liuqihang
 * @Date 2022/7/22 15:53
 */
public class HandleChain {

    private Handler head = null;
    private Handler tail = null;

    public void addHandler(Handler handler) {
        handler.setSuccessor(null);
        if (head == null) {
            head = handler;
            tail = handler;
            return;
        }
        tail.setSuccessor(handler);
        tail = handler;
    }

    public void handle() {
        if (head != null) {
            head.handle();
        }
    }
}
