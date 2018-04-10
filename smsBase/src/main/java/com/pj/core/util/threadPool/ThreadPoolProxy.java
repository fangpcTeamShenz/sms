package com.pj.core.util.threadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 类    名:  ThreadPoolProxy
 * 创 建 者:  朱剑斌
 * 创建时间:  2016/3/17 09:45
 * 描    述： 线程池的代理类
 * 描    述： 替原对象(ThreadPool)完成一些操作(提交任务,执行任务,移除任务)
 */
public class ThreadPoolProxy {
	
    ThreadPoolExecutor mExecutor;//核心池大小

    private int maximumPoolSize;
    
    private int minnumPoolSize;
    
    private long keepAliveTime;

    private TimeUnit unit;
    
    private RejectedExecutionHandler handler;
    
    private BlockingQueue<Runnable> workQueue;
    
    private ThreadFactory threadFactory;
    
    public ThreadPoolProxy(int minnumPoolSize,int maximumPoolSize) {
    	this.minnumPoolSize = minnumPoolSize; //线程池维护线程的最少数量
        this.maximumPoolSize = maximumPoolSize; //线程池维护线程的最大数量
        this.keepAliveTime = 1L; //线程池维护线程所允许的空闲时间
        this.unit = TimeUnit.MILLISECONDS; //线程池维护线程所允许的空闲时间的单位
        this.workQueue = new SynchronousQueue<Runnable>(); //任务队列
        this.handler = new ThreadPoolExecutor.CallerRunsPolicy(); //线程池对拒绝任务的处理策略
        this.threadFactory = Executors.defaultThreadFactory();//线程工厂
    }

    /**
     * 创建ThreadPoolExecutor对象
     */
    private void initThreadPoolExecutor() {
        //双重检查加锁,只有在第一次实例化的时候才启用同步机制,提高了性能
        if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()) {
            synchronized (ThreadPoolProxy.class) {
                if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()) {
                    mExecutor = new ThreadPoolExecutor(minnumPoolSize, maximumPoolSize,
                    		keepAliveTime, unit,workQueue, threadFactory, handler);
                }
            }
        }
    }
    
    /*
     提交任务和执行任务的区别?
        1.是否有返回值?
            submit有返回值,执行没有返回值
        2.Future是干嘛的?
            1.描述异步执行完成之后的结果
            2.有方法检查任务是否执行完成,还可以等待执行完成,接收执行完成后的结果
            3.get方法可以接收结果,在任务执行完成之后,get方法可以阻塞等待结果准备好-->重点关心
            4.cancle方法可以去取消
            5.get方法还有一种重要的作用,可以抛出任务执行过程中遇到的异常
     */
    /**
     * 提交任务
     *
     * @param task
     */
    @SuppressWarnings("rawtypes")
	public Future submit(Callable task) {
        //初始化mExecutor对象
        initThreadPoolExecutor();
        @SuppressWarnings("unchecked")
		Future<?> submitResult = mExecutor.submit(task);
        return  submitResult;
    }

    /**
     * 执行任务
     *
     * @param task
     */
    public void execute(Runnable task) {
        initThreadPoolExecutor();
        mExecutor.execute(task);
    }

    /**
     * 移除任务
     *
     * @param task
     */
    public void remove(Runnable task) {
        initThreadPoolExecutor();
        mExecutor.remove(task);
    }
}
