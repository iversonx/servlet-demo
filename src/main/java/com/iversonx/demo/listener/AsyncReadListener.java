package com.iversonx.demo.listener;

import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * @author Lijie
 * @version 1.0
 * @date 2019/11/27 16:41
 */
public class AsyncReadListener implements ReadListener {
    private AsyncContext asyncContext;
    public AsyncReadListener(AsyncContext context){
        this.asyncContext = context;
    }
    //数据可用时触发执行
    @Override
    public void onDataAvailable() throws IOException {
        System.out.println("数据可用时触发执行");
    }

    //数据读完时触发调用
    @Override
    public void onAllDataRead() throws IOException {
        try {
            Thread.sleep(5000);//暂停5秒，模拟耗时处理数据
            PrintWriter out = asyncContext.getResponse().getWriter();
            out.write("数据读完了: " + LocalDateTime.now() + ":" + Thread.currentThread().getName());
            out.flush();
            asyncContext.complete();
            System.out.println("数据读完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //数据出错触发调用
    @Override
    public void onError(Throwable t){
        System.out.println("数据 出错");
        t.printStackTrace();
    }
}
