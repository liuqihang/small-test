package com.xylink.test.scheme.chainOfResponsibility;

/**
 * @ClassName Handler
 * @Description TODO
 * @Author liuqihang
 * @Date 2022/7/22 15:47
 */
public abstract class Handler {

    protected Handler successor = null;

    public void setSuccessor(Handler successor){
        this.successor = successor;
    }

    public void handle(){
        doHandle();
        if(successor != null){
            successor.handle();
        }
    }

    protected abstract void doHandle();

}
