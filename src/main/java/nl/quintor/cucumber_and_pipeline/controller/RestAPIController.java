package nl.quintor.cucumber_and_pipeline.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.quintor.cucumber_and_pipeline.domain.CreditRequestQueue;
import nl.quintor.cucumber_and_pipeline.model.CreditRequest;
import nl.quintor.cucumber_and_pipeline.model.Status;
import nl.quintor.cucumber_and_pipeline.repository.CreditRequestRepository;

@RestController
public class RestAPIController {

    @Autowired
    private CreditRequestRepository creditRequestRepository;
    
    @Autowired
    private CreditRequestQueue creditRequestQueue;

    @RequestMapping(value = "/creditRequest", method = RequestMethod.POST)
    @Transactional
    public CreditRequest receiveCreditRequest(@RequestBody CreditRequest creditRequest) throws JAXBException {

        enhanceCreditRequest(creditRequest);
        creditRequestRepository.save(creditRequest);

        scheduleTask(creditRequest.getId());
        return creditRequest;
    }

    private void scheduleTask(Long creditRequestId) {
        creditRequestQueue.addToQueue(creditRequestId);
    }

    private void enhanceCreditRequest(final CreditRequest creditRequest) {
        creditRequest.setCreationDate(new Date());
        creditRequest.setStatus(Status.ONTVANGEN);
        creditRequest.setOntvangendoor(getHostname());
    }

    private String getHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "Hostname not retrieved";
        }
    }

    @RequestMapping("/creditRequests")
    public Iterable<CreditRequest> getCreditRequests() {
        return creditRequestRepository.findAll();
    }

}
