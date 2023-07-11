package Demo.flow;

import java.util.concurrent.Flow;
import java.util.function.Consumer;

/**
 * @author bk
 */
public class MySubscriber<T> implements Flow.Subscriber<T> {

    private Flow.Subscription subscription;
    private final Consumer<T> consumer;
    private boolean completed;
    private long requestNum = 3;

    public MySubscriber(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(3);
    }

    @Override
    public void onNext(T item) {
        requestNum--;
        consumer.accept(item);
        if (requestNum == 0 &&!completed) {
            requestNum = 3;
            subscription.request(requestNum);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    @Override
    public void onComplete() {
        requestNum = 0;
        completed = true;
        subscription.cancel();
    }
}
