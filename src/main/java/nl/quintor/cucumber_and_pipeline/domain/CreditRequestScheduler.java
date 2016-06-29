package nl.quintor.cucumber_and_pipeline.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CreditRequestScheduler {
    
    @Autowired
    private CreditRequestHandler creditRequestHandler;
    
    @Autowired
    private CreditRequestQueue creditRequestQueue;
    
    @Scheduled(fixedDelay = 4000l)
    public void handleNewRequests(){
        final Long creditRequestId = creditRequestQueue.pullFromQueue();
        if(creditRequestId != null) {
            handleCreditRequest(creditRequestId);
        }
    }
    
    private void handleCreditRequest(Long creditRequestId){
        creditRequestHandler.markAsInBehandeling(creditRequestId);
        waitSomeMoments();
        creditRequestHandler.processCreditRequest(creditRequestId);
    }

    private void waitSomeMoments() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // Put the interrupt back on the Thread and go on
            Thread.currentThread().interrupt();
        }
    }
    
    
}
