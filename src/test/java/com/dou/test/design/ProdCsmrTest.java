package com.dou.test.design;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProdCsmrTest {


    public class Producer implements Runnable{

        private  Storage storage;
        private  String name;

        public Producer(Storage storage, String name) {
            this.storage = storage;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    storage.push(new Product((long) (Math.random() * 10000)));
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class Consumer implements Runnable{

        private  Storage storage;
        private  String name;

        public Consumer(Storage storage, String name) {
            this.storage = storage;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                while (true){
                    Product p = storage.pop();
                    System.out.println(p.toString());
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class Product{

        private Long id;

        public Product(Long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "产品编号：" + this.id;
        }
    }

    public class Storage{

        BlockingQueue<Product> queue = new LinkedBlockingQueue<>(10);

        public void push(Product t) throws InterruptedException {
            queue.put(t);
        }

        public Product pop() throws InterruptedException {
            return queue.take();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }


}
