package nl.quintor.cucumber_and_pipeline.controller;

import static nl.quintor.cucumber_and_pipeline.model.Status.ONTVANGEN;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import nl.quintor.cucumber_and_pipeline.domain.CreditRequestQueue;
import nl.quintor.cucumber_and_pipeline.model.CreditRequest;
import nl.quintor.cucumber_and_pipeline.repository.CreditRequestRepository;

@RunWith(MockitoJUnitRunner.class)
public class RestAPIControllerTest {

    @Mock
    CreditRequestRepository creditRequestRepository;

    @Mock
    CreditRequestQueue creditRequestQueue;

    @InjectMocks
    RestAPIController restAPIController;

    @Test
    public void testNewRequest() throws JAXBException {
        CreditRequest newRequest = new CreditRequest();
        newRequest.setId(10L);
        newRequest.setNaam("Test");
        newRequest.setBedrag(BigDecimal.valueOf(10000L));
        restAPIController.receiveCreditRequest(newRequest);
        
        assertThat(newRequest.getStatus(), is(ONTVANGEN)); //Request is marked as ONTVANGEN
        verify(creditRequestRepository).save(newRequest); //Save is done
        verify(creditRequestQueue).addToQueue(newRequest.getId()); //Handling is scheduled
    }
}