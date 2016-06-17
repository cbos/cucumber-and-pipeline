package nl.quintor.cucumber_and_pipeline.domain.steps;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cucumber.api.java.Before;
import cucumber.api.java.nl.Als;
import cucumber.api.java.nl.Dan;
import cucumber.api.java.nl.Stel;
import nl.quintor.cucumber_and_pipeline.domain.CreditRequestHandler;
import nl.quintor.cucumber_and_pipeline.model.CreditRequest;
import nl.quintor.cucumber_and_pipeline.model.Status;
import nl.quintor.cucumber_and_pipeline.repository.CreditRequestRepository;

public class CreditRequestHandlerSteps {

    @Mock
    CreditRequestRepository creditRequestRepository;

    @InjectMocks
    CreditRequestHandler creditRequestHandler;

    private CreditRequest creditRequest;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Stel("^klant (.*) doet nieuwe aanvraag doet voor (\\d+)$")
    public void klantDoetNieuweAanvraagDoetVoor(String customerName, int amount) throws Throwable {
        creditRequest = new CreditRequest();
        creditRequest.setNaam(customerName);
        creditRequest.setBedrag(BigDecimal.valueOf(amount));
        creditRequest.setId(11L);
        when(creditRequestRepository.findOne(creditRequest.getId())).thenReturn(creditRequest);
    }
   
    @Als("^de aanvraag in behandeling wordt genomen$")
    public void deAanvraagInBehandelingWordtGenomen() throws Throwable {
        creditRequestHandler.markAsInBehandeling(creditRequest.getId());
    }

    @Dan("^moet de status \"([^\"]*)\" zijn$")
    public void moetDeStatusZijn(String expectedStatus) throws Throwable {
        assertThat(creditRequest.getStatus(), is(Status.valueOf(expectedStatus)));
    }

    @Als("^de aanvraag wordt verwerkt$")
    public void deAanvraagWordtVerwerkt() throws Throwable {
        creditRequestHandler.processCreditRequest(creditRequest.getId());
    }
}
