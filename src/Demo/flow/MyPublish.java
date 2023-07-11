package Demo.flow;

import java.util.Spliterator;
import java.util.concurrent.Flow;

/**
 * @author bk
 */
public class MyPublish<T> implements Flow.Publisher<T>,Flow.Subscription {

    private Flow.Subscriber<? super T> subscriber;  // MyFilterProcessor
    private final Spliterator<T> spliterator; // 数据
    private boolean completed = false;
    private boolean canceled = false;

    public MyPublish(Spliterator<T> spliterator) {
        this.spliterator = spliterator;
    }

    @Override
    public void subscribe(Flow.Subscriber<? super T> subscriber) {
        this.subscriber = subscriber;
        this.subscriber.onSubscribe(this);
    }

    // Subscription
    @Override
    public void request(long n) {
        if (completed || canceled) {
            return;
        }
        long size = spliterator.getExactSizeIfKnown();
        if (size == 0) {
            completed = true;
            subscriber.onComplete();
            return;
        }
        for (long min = Math.min(n, size); min > 0; min--) {
            spliterator.tryAdvance(subscriber::onNext);
        }
    }

    @Override
    public void cancel() {
        canceled = true;
        if (!completed) {
            subscriber.onComplete();
        }
    }
}
