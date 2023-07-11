package Demo.flow;

import java.util.concurrent.Flow;
import java.util.function.Function;

/**
 * @author bk
 */
public class MyMappingProcessor<P,N> implements Flow.Processor<P,N>,Flow.Subscription {

    private Flow.Subscriber<? super N> subscriber; //MySubscription
    private Flow.Subscription subscription;  //MyFilterProcessor
    private final Function<P, N> function;

    public MyMappingProcessor(Function<P, N> function) {
        this.function = function;
    }

    @Override
    public void subscribe(Flow.Subscriber<? super N> subscriber) {
        this.subscriber = subscriber;
        this.subscriber.onSubscribe(this);
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(P item) {
        subscriber.onNext(function.apply(item));
    }

    @Override
    public void onError(Throwable throwable) {
        subscriber.onError(throwable);
    }

    @Override
    public void onComplete() {
        subscriber.onComplete();
    }

    @Override
    public void request(long n) {
        subscription.request(n);
    }

    @Override
    public void cancel() {
        subscription.cancel();
    }
}
