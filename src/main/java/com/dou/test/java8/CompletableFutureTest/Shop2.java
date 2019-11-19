package com.dou.test.java8.CompletableFutureTest;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author dsp
 * @date 2019-09-05
 */
public class Shop2 {

    public Shop2() {
    }

    public Shop2(String name) {
        this.name = name;
    }

    private static List<Shop2> shops = Arrays.asList(
            new Shop2("BestPrice"),
            new Shop2("LetsSaveBig"),
            new Shop2("MyFavoriteShop"),
            new Shop2("BuyItAll"));

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static final Random random = new Random();

    public static void randomDelay() {
        int delay = 500 + random.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private double calculatePrice(String product) {
        // delay();
        randomDelay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    private String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    public List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(toList());
    }

    private static Executor executor =
            Executors.newFixedThreadPool(Math.min(shops.size(), 100), r -> {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            });

    private static List<String> findPricesAsyncExecutor(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor)) //以异步方式取得每个 shop 中指定 产品的原始价格
                        .map(future -> future.thenApply(Quote::parse)) // Quote 对象存在时，解析报价
                        .map(future -> future.thenCompose(
                                quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor))) // 使用另一个异步任务构造期望的Future ，申请折扣
                        .collect(toList());
        return priceFutures.stream()
                .map(CompletableFuture::join) // 等待流中的所有 Future 执行完毕，并提取各自的返回值
                .collect(toList());
    }

    public static Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
    }

    public static void main(String[] args) {
        // long start = System.nanoTime();
        // System.out.println(findPricesAsyncExecutor("myPhone27S"));
        // long duration = (System.nanoTime() - start) / 1_000_000;
        // System.out.println("Done in " + duration + " msecs");

        // long start = System.nanoTime();
        // findPricesStream("myPhone").map(f -> f.thenAccept(System.out::println));
        // System.out.println("All shops have now responded in "
        //         + ((System.nanoTime() - start) / 1_000_000) + " msecs");

        // 等待所有的都返回，在输出
        long start = System.nanoTime();
        CompletableFuture[] futures = findPricesStream("myPhone27S")
                .map(f -> f.thenAccept(s -> System.out.println(s + " (done in " + ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
        // CompletableFuture.anyOf(futures).join();
        System.out.println("All shops have now responded in "
                + ((System.nanoTime() - start) / 1_000_000) + " msecs");


    }



}
