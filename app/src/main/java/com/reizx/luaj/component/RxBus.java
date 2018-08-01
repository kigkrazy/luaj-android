package com.reizx.luaj.component;

import com.reizx.luaj.util.RxUtil;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * 与EventBus类似是一种用于Android的发布/订阅事件总线。它有很多优点：简化应用组件间的通信；
 * 解耦事件的发送者和接收者；避免复杂和容易出错的依赖和生命周期的问题；很快，专门为高性能优化过等等。
 *
 * Created by codeest on 2016/8/2.
 */
public class RxBus {
    private static RxBus rxBus;//单例主体
    // PublishSubject只会把在订阅发生的时间点之后来自原始Flowable的数据发射给观察者
    private final FlowableProcessor<Object> flowableProcessor = PublishProcessor.create().toSerialized();//主要的事件总线对象

    /**
     * 获取实例
     *
     * @return
     */
    public static RxBus getInstance() {
        if (rxBus == null)
            rxBus = new RxBus();
        return rxBus;
    }

    /**
     * 事件推送
     * @param o
     */
    public void post(Object o) {
        flowableProcessor.onNext(o);
    }

    /**
     * 延迟事件推送
     * @param o 事件
     * @param millis
     */
    public void postDelay(final Object o, final long  millis) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(millis);
                    flowableProcessor.onNext(o);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Flowable<T> toFlowable(Class<T> eventType) {
        return flowableProcessor.ofType(eventType);
    }

    // 封装默认订阅
    public <T> Disposable toDefaultFlowable(Class<T> eventType, Consumer<T> act) {
        return flowableProcessor.ofType(eventType).compose(RxUtil.<T>rxSchedulerHelper()).subscribe(act);
    }
}
