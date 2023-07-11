package Demo.flow;

import java.util.concurrent.Flow;
import java.util.function.Predicate;

/**
 * @author bk
 */
public class MyFilterProcessor<T> implements Flow.Processor<T,T>,Flow.Subscription{

    private Flow.Subscriber<? super T> subscriber;  //MyMappingProcessor
    private Flow.Subscription subscription; // MyPublish
    private final Predicate<T> predicate;
    private long requestNum = 0;
    private long lessNum = 0;

    public MyFilterProcessor(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    // 订阅者
    @Override
    public void subscribe(Flow.Subscriber<? super T> subscriber) {
        this.subscriber = subscriber;
        this.subscriber.onSubscribe(this);
    }

    // 消费者
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(T item) {
        requestNum--;
        if (predicate.test(item)) {
            this.subscriber.onNext(item);
        } else {
            lessNum++;
        }
        if (requestNum == 0 && lessNum > 0) {
            request(lessNum);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        subscriber.onError(throwable);
    }

    @Override
    public void onComplete() {
        subscriber.onComplete();
    }

    // 中间
    @Override
    public void request(long n) {
        this.requestNum = n;
        this.lessNum = 0;
        this.subscription.request(n);
    }

    @Override
    public void cancel() {
        this.subscription.cancel();
    }
}
