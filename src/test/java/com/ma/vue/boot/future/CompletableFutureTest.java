package com.ma.vue.boot.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf = new CompletableFuture<String>();
        boolean coding = cf.complete("coding");
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        Void unused = CompletableFuture.runAsync(() -> {

        }).thenRun(() -> {

        }).get();

    }
}
