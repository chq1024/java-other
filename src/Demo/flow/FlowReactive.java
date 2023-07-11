package Demo.flow;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author bk
 */
public class FlowReactive {

    public void exec() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,5, 1000,
                                                                        TimeUnit.MILLISECONDS,
                                                                        new ArrayBlockingQueue<>(10,true),
                                                                        Executors.defaultThreadFactory(),
                                                                        new ThreadPoolExecutor.AbortPolicy());
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        SubmissionPublisher<Map<String,Object>> submissionPublisher = new SubmissionPublisher<>(threadPoolExecutor,1,null);
        System.out.println(submissionPublisher.getMaxBufferCapacity());
        submissionPublisher.subscribe(new Flow.Subscriber<>() {
            private Flow.Subscription subscription;
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                subscription.request(1L);
            }

            @Override
            public void onNext(Map<String, Object> item) {
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "消费数据" + item);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    subscription.request(1L);
                });
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("完成消费");
            }
        });
        for (int i = 1; i <= 10000; i++) {
            int num = submissionPublisher.submit(Map.of("num", i));
            System.out.println("发送"+i+"条数据" +num);
        }
        submissionPublisher.close();
        threadPoolExecutor.shutdown();
        executorService.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        FlowReactive flowReactive = new FlowReactive();
        flowReactive.exec();
    }
}
