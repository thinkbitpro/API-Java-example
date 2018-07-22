package com.thinkbit.api.example;

import com.thinkbit.api.example.define.Direction;
import com.thinkbit.api.example.utils.ThinkBitAPIClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExampleApplicationTests {

    @Autowired
    private ThinkBitAPIClient thinkBitAPIClient;

    @Test
    public void testCreateOrder() throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(2);
        this.thinkBitAPIClient.orderCreate("EOS_USDT", Direction.BUY, new BigDecimal("1.72"), new BigDecimal("1"))
                .doOnSuccess(response -> {
                    System.out.println(response);
                    countDownLatch.countDown();
                })
                .log()
                .subscribe();

        this.thinkBitAPIClient.orderCreate("EOS_USDT", Direction.SELL, new BigDecimal("1.72"), new BigDecimal("1"))
                .doOnSuccess(response -> {
                    System.out.println(response);
                    countDownLatch.countDown();
                })
                .log()
                .subscribe();
        countDownLatch.await();
    }

    @Test
    public void testAccountBalance(){
        this.thinkBitAPIClient.accountBalance("EOS")
                .log()
                .subscribe();
    }

    @Test
    public void testCancelOrder() throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(2);
        this.thinkBitAPIClient.orderCreate("EOS_USDT", Direction.BUY, new BigDecimal("1.72"), new BigDecimal("1"))
                .doOnSuccess(orderCreate -> {
                    this.thinkBitAPIClient.orderCancel(orderCreate.getOrderId())
                            .doOnSuccess(orderCancel -> {
                                countDownLatch.countDown();
                            })
                            .log()
                            .subscribe();
                    countDownLatch.countDown();
                })
                .log()
                .subscribe();
        countDownLatch.await();
    }

    @Test
    public void testActiveOrder()throws InterruptedException{
        CountDownLatch countDownLatch=new CountDownLatch(1);
        this.thinkBitAPIClient.orderActive("EOS_USDT")
                .doOnSuccess(orders -> {
                    orders.forEach(System.out::println);
                })
                .log()
                .subscribe();
        countDownLatch.await();
    }
}
