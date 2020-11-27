package com.dou.test.concurrent;

import com.dou.test.concurrent.completable.CustomerInfoService;
import com.dou.test.concurrent.completable.LabelService;
import com.dou.test.concurrent.completable.LearnRecordService;
import com.dou.test.concurrent.completable.OrderService;
import com.dou.test.concurrent.completable.RemoteLoader;
import com.dou.test.concurrent.completable.WatchRecordService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.stream.Collectors.toList;

@SpringBootTest
public class CompletableFutureTest {

    private void doSomethingException() {
        System.out.println("doSomething...");
        throw new RuntimeException("Test Exception");
    }

    private void doSomething() {
        System.out.println("doSomething...");
    }

    @NotNull
    private List<RemoteLoader> getRemoteLoaders() {
        return Arrays.asList(
                new CustomerInfoService(),
                new LearnRecordService(),
                new LabelService(),
                new OrderService(),
                new WatchRecordService(),

                new CustomerInfoService(),
                new LearnRecordService(),
                new LabelService(),
                new OrderService(),
                new WatchRecordService(),

                new CustomerInfoService(),
                new LearnRecordService(),
                new LabelService(),
                new OrderService(),
                new WatchRecordService(),

                new CustomerInfoService(),
                new LearnRecordService(),
                new LabelService(),
                new OrderService(),
                new WatchRecordService(),
                new CustomerInfoService(),
                new LearnRecordService(),
                new LabelService(),
                new OrderService(),
                new WatchRecordService(),
                new CustomerInfoService(),
                new LearnRecordService(),
                new LabelService(),
                new OrderService(),
                new WatchRecordService(),
                new CustomerInfoService(),
                new LearnRecordService(),
                new LabelService(),
                new OrderService(),
                new WatchRecordService(),
                new CustomerInfoService(),
                new LearnRecordService(),
                new LabelService(),
                new OrderService(),
                new WatchRecordService(),
                new CustomerInfoService(),
                new LearnRecordService(),
                new LabelService(),
                new OrderService(),
                new WatchRecordService(),
                new CustomerInfoService(),
                new LearnRecordService(),
                new LabelService(),
                new OrderService(),
                new WatchRecordService(),
                new CustomerInfoService(),
                new LearnRecordService(),
                new LabelService(),
                new OrderService(),
                new WatchRecordService(),
                new CustomerInfoService(),
                new LearnRecordService(),
                new LabelService(),
                new OrderService(),
                new WatchRecordService(),

                new CustomerInfoService(),
                new LearnRecordService(),
                new LabelService(),
                new OrderService(),
                new WatchRecordService()
        );
    }

    /**
     * 基本用法
     */
    @Test
    public void base(){
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(()->{
            try {
                Thread.sleep(1000);
                future.complete("Finish");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        String result = future.join();
        System.out.println(result);
    }

    /**
     * 异常反馈给主线程
     */
    @Test
    public void exception() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(()->{
            try {
                this.doSomethingException();
                future.complete("Finish");
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        }).start();
        System.out.println(future.get());
    }

    /**
     * 异常反馈给主线程2
     */
    @Test
    public void exception2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(()->{
                    this.doSomethingException();
                    return "Finish";
                });
        System.out.println(future.get());
    }

    /**
     * 异常反馈给主线程3
     */
    @Test
    public void testCompletableFuture2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> {
                    doSomethingException();
                    return "Finish";
                })
                .exceptionally(throwable -> "Throwable exception message:" + throwable.getMessage());
        System.out.println(future.get());
    }

    /**
     * parallelStream
     * 当list达到一定大小时，并行流效果不理想
     */
    @Test
    public void stream() {
        long start = System.currentTimeMillis();
        List<RemoteLoader> remoteLoaders = getRemoteLoaders();
        List<String> customerDetail = remoteLoaders
                .parallelStream()
                .map(RemoteLoader::load)
                .collect(toList());
        System.out.println(customerDetail);
        long end = System.currentTimeMillis();
        System.out.println("总共花费时间:" + (end - start));
    }

    /**
     * CompletableFuture + stream
     */
    @Test
    public void streamAndCompletableFuture() {
        long start = System.currentTimeMillis();
        List<RemoteLoader> remoteLoaders = getRemoteLoaders();
        List<CompletableFuture<String>> completableFutures = remoteLoaders
                .stream()
                .map(loader -> CompletableFuture.supplyAsync(loader::load))
                .collect(toList());
        List<String> customerDetail = completableFutures
                .stream()
                .map(CompletableFuture::join)
                .collect(toList());
        System.out.println(customerDetail);
        long end = System.currentTimeMillis();
        System.out.println("总共花费时间:" + (end - start));
    }

    /**
     * CompletableFuture + stream + thread pool
     */
    @Test
    public void pool() {
        long start = System.currentTimeMillis();
        List<RemoteLoader> remoteLoaders = getRemoteLoaders();
        ExecutorService executorService = Executors.newFixedThreadPool(12);
        List<CompletableFuture<String>> completableFutures = remoteLoaders
                .stream()
                .map(loader -> CompletableFuture.supplyAsync(loader::load, executorService))
                .collect(toList());
        List<String> customerDetail = completableFutures
                .stream()
                .map(CompletableFuture::join)
                .collect(toList());
        System.out.println(customerDetail);
        long end = System.currentTimeMillis();
        System.out.println("总共花费时间:" + (end - start));
    }


}
