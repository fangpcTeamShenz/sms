package com.pj.core.threadPool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.pj.core.threadPool.ThreadPoolProxy;

/**
 * 作    者：  sohan
 * 创建时间：  2016/12/16 08:28
 * 描    述：  线程池工厂类
 */

public class ThreadPoolProxyFactory {
    private static ThreadPoolProxy mNormalProxy;

    /**
     * 创建普通的线程池代理
     */
    public static ThreadPoolProxy createNormalPoolProxy() {
        if (mNormalProxy == null) {
            synchronized (ThreadPoolProxyFactory.class) {
                if (mNormalProxy == null) {
                    mNormalProxy = new ThreadPoolProxy(5,10);
                }
            }
        }
        return mNormalProxy;
    }
    
    @SuppressWarnings({ "static-access", "rawtypes" })
	public static void main(String[] args) throws InterruptedException, ExecutionException {
    	// 线程中的线程执行 业务场景：当需要知道线程执行返回结果时（返回值），又不影响外层执行效率！
    	for(int i = 0;i<10;i++){
    		ThreadPoolProxyFactory.createNormalPoolProxy().execute(new Runnable() {
				public void run() {
					Future b = ThreadPoolProxyFactory.createNormalPoolProxy().submit(new Callable() {
		    			@Override
		    			public Object call() throws Exception{
		    				System.out.println("线程");
		    				try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								
							}
		    				Map<String, Object> map = new HashMap<String, Object>();
		    				map.put("1", 1);
		    				map.put("2", 2);
		    				map.put("3", 3);
		    				return map;
		    			}
		    		});
					try {
						System.out.println(b.get().toString());
					} catch (Exception e) {
						
					}
				}
			});
			//System.out.println(f.get().toString());
    	}
		
	}
}
