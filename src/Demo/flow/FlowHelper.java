package Demo.flow;

import java.util.Spliterator;
import java.util.concurrent.Flow;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author bk
 */
public class FlowHelper<T> {

    private Flow.Publisher<T> publisher;

    private FlowHelper(Flow.Publisher<T> publisher) {
        this.publisher = publisher;
    }

    public static <T> FlowHelper<T> init(Spliterator<T> spliterator) {
        MyPublish<T> myPublish = new MyPublish<>(spliterator);
        return new FlowHelper<>(myPublish);
    }

    public FlowHelper<T> filter(Predicate<T> predicate) {
        MyFilterProcessor<T> myFilterProcessor = new MyFilterProcessor<>(predicate);
        publisher.subscribe(myFilterProcessor);
        publisher = myFilterProcessor;
        return this;
    }

    public <R> FlowHelper<R> mapping(Function<T,R> function) {
        MyMappingProcessor<T,R> myMappingProcessor = new MyMappingProcessor<>(function);
        publisher.subscribe(myMappingProcessor);
        return new FlowHelper<>(myMappingProcessor);
    }

    public void accept(Consumer<T> consumer) {
        MySubscriber<T> mySubscriber = new MySubscriber<>(consumer);
        publisher.subscribe(mySubscriber);
    }
}
