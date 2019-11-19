package com.dou.test.java8.CompletableFutureTest;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author dsp
 * @date 2019-09-05
 */
public class Shop {

    public Shop() {
    }

    public Shop(String name) {
        this.name = name;
    }

    private String name;

    static List<Shop> shops = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));

    private static final Executor executor =
            Executors.newFixedThreadPool(Math.min(shops.size(), 100),
                    new ThreadFactory() {
                        public Thread newThread(Runnable r) {
                            Thread t = new Thread(r);
                            t.setDaemon(true);
                            return t;
                        }
                    });

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    // public Future<Double> getPriceAsync(String product) {
    //     CompletableFuture<Double> futurePrice = new CompletableFuture<>();
    //     new Thread( () -> {
    //         double price = calculatePrice(product);
    //         futurePrice.complete(price);
    //     }).start();
    //     return futurePrice;
    // }

    public Future<Double> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        delay();
        Random random = new Random();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void doSomethingElse() {
        delay();
    }

    public static List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }

    public static List<String> findPricesParallel(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }

    public static List<String> findPricesAsync(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> String.format("%s price is %.2f", shop.getName(),shop.getPrice(product))))
                        .collect(Collectors.toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    public static List<String> findPricesAsyncExecutor(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> String.format("%s price is %.2f", shop.getName(),shop.getPrice(product)),executor))
                        .collect(Collectors.toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    public static void main(String[] args) {
        // Shop shop = new Shop("BestShop");
        // long start = System.nanoTime();
        // Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        // long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        // System.out.println("Invocation returned after " + invocationTime + " msecs");
        // // 执行更多任务，比如查询其他商店
        // doSomethingElse();
        // // 在计算商品价格的同时
        // try {
        //     double price = futurePrice.get();
        //     System.out.printf("Price is %.2f%n", price);
        // } catch (Exception e) {
        //     throw new RuntimeException(e);
        // }
        // long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        // System.out.println("Price returned after " + retrievalTime + " msecs");

        long start = System.nanoTime();
        // 顺序
        System.out.println(findPrices("myPhone27S"));
        // 并发
        System.out.println(findPricesParallel("myPhone27S"));
        // 异步
        System.out.println(findPricesAsync("myPhone27S"));
        // 异步 定制执行器
        System.out.println(findPricesAsyncExecutor("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }





}
