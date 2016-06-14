package nl.quintor.cucumber_and_pipeline.domain;

import java.util.concurrent.ConcurrentLinkedDeque;

import org.springframework.stereotype.Component;

@Component
public class CreditRequestQueue {

    private ConcurrentLinkedDeque<Long> concurrentLinkedDeque = new ConcurrentLinkedDeque<>();
    
    public void addToQueue(Long requestId) {
        concurrentLinkedDeque.addLast(requestId);
    }
    
    public Long pullFromQueue(){
        return concurrentLinkedDeque.pollFirst();
    }
}
