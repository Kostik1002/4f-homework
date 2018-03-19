package io.fourfinanceit.repository;

import io.fourfinanceit.model.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {

    Loan findByIdAndClientId(Long id, Long clientId);

}
