package com.nowcoder.community;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10); //阻塞队列长度为10，ArrayBlockingQueue：用数组实现队列
        new Thread(new Producer(queue)).start();//启动生产者线程1
        new Thread(new Consumer(queue)).start();//启动消费者线程1
        new Thread(new Consumer(queue)).start();//启动消费者线程2
        new Thread(new Consumer(queue)).start();//启动消费者线程3
    }
}

//生产者线程
//继承Runnable接口来实现多线程
class Producer implements Runnable {

    private BlockingQueue<Integer> queue;

    //有参构造器：实例化Producer时把阻塞队列BlockingQueue传进来
    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    //实现接口的run方法
    @Override
    public void run() {
        try {
            //模拟不断往阻塞队列传数据
            for (int i = 0; i < 100; i++) {
                Thread.sleep(20);
                queue.put(i);
                System.out.println(Thread.currentThread().getName() + "生产:" + queue.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

//消费者线程
//继承Runnable接口来实现多线程
class Consumer implements Runnable {

    private BlockingQueue<Integer> queue;

    // //有参构造器：实例化Producer时把阻塞队列BlockingQueue传进来
    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            //模拟不断从阻塞队列取数据
            boolean isUsed = false;
            while (true) {
                int randomTime = new Random().nextInt(1000);
                Thread.sleep(randomTime);
                queue.take();
                System.out.println(Thread.currentThread().getName() + "消费:" + queue.size() +
                        "===randomTime：" + (isUsed ? randomTime : 40));
                isUsed = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
