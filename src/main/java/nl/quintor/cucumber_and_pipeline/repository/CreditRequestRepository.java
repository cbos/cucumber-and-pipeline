package nl.quintor.cucumber_and_pipeline.repository;

import org.springframework.data.repository.CrudRepository;

import nl.quintor.cucumber_and_pipeline.model.CreditRequest;

/**
 * Credit Request repository
 */
public interface CreditRequestRepository extends CrudRepository<CreditRequest, Long> {

}
