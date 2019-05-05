package com.dou.test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * java多线程模拟生产者消费者问题
 *
 * ProducerConsumer是主类，Producer生产者，Consumer消费者，Product产品，Storage仓库
 *
 * @author 林计钦 https://www.cnblogs.com/linjiqin/p/3217050.html
 * @version 1.0 2013-7-24 下午04:49:02
 */
public class ProducerConsumer {

    public static void main(String[] args) {

        ProducerConsumer pc = new ProducerConsumer();
        Storage storage = pc.new Storage();

        Producer p1 = pc.new Producer("Producer-张三", storage);
        Producer p2 = pc.new Producer("Producer-李四", storage);
        Consumer c1 = pc.new Consumer("Consumer-王五", storage);
        Consumer c2 = pc.new Consumer("Consumer-老刘", storage);
        Consumer c3 = pc.new Consumer("Consumer-老林", storage);

        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(p1);
        service.submit(p2);
        service.submit(c1);
        service.submit(c2);
        service.submit(c3);

    }

    /**
     * Producer生产者
     */
    public class Producer implements Runnable{

        private String name;
        private Storage storage;

        public Producer(String name, Storage storage) {
            this.name = name;
            this.storage = storage;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Product product = new Product((int) (Math.random() * 10000)); // 产生0~9999随机整数
                    System.out.println(name + "已经生产完产品(" + product.toString() + ").");
                    storage.push(product);
                    System.out.println(name + "已放入仓储(" + product.toString() + ").");
                    System.out.println("===================================");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Consumer消费者
     */
    public class Consumer implements Runnable{

        private String name;
        private Storage storage = null;

        public Consumer(String name, Storage storage) {
            this.name = name;
            this.storage = storage;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Product product = storage.pop();
                    System.out.println(name + "已取出产品.(" + product.toString() + ").");
                    System.out.println(name + "已消费(" + product.toString() + ").");
                    System.out.println("===============");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Product产品
     */
    public class Product{

        private int id;
        public Product(int id) {
            this.id = id;
        }

        public String toString() {// 重写toString方法
            return "产品编号：" + this.id;
        }
    }

    /**
     * Storage仓库
     */
    public class Storage{

        BlockingQueue<Product> queues = new LinkedBlockingQueue<>(10);

        /**
         * 生产
         *
         * @param p
         *            产品
         * @throws InterruptedException
         */
        public void push(Product p) throws InterruptedException {
            queues.put(p);
            System.out.println("已经存放到仓储:"+ p.toString());
        }

        /**
         * 消费
         *
         * @return 产品
         * @throws InterruptedException
         */
        public Product pop() throws InterruptedException {
            return queues.take();
        }

    }


}
