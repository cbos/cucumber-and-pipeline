package nl.quintor.cucumber_and_pipeline.domain;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import nl.quintor.cucumber_and_pipeline.model.CreditRequest;
import nl.quintor.cucumber_and_pipeline.model.Status;
import nl.quintor.cucumber_and_pipeline.repository.CreditRequestRepository;

@Component
@Transactional
public class CreditRequestHandler {

    @Autowired
    private CreditRequestRepository creditRequestRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processCreditRequest(Long id) {
        CreditRequest creditRequest = creditRequestRepository.findOne(id);
        if (creditRequest.getBedrag() != null && creditRequest.getBedrag().compareTo(new BigDecimal(200000)) > 0) {
            creditRequest.setStatus(Status.AFGEWEZEN);
            creditRequest.setAfwijsreden("Bedrag is te hoog");
        } else {
            creditRequest.setStatus(Status.AKKOORD);
        }
        creditRequest.setVerwerktdoor(getHostname());
        creditRequestRepository.save(creditRequest);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CreditRequest markAsInBehandeling(Long id) {
        CreditRequest creditRequest = creditRequestRepository.findOne(id);
        creditRequest.setStatus(Status.IN_BEHANDELING);
        return creditRequestRepository.save(creditRequest);
    }

    private String getHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "Hostname not retrieved";
        }
    }
}
