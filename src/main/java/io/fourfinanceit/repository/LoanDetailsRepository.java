package io.fourfinanceit.repository;

import io.fourfinanceit.model.LoanDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface LoanDetailsRepository extends CrudRepository<LoanDetail, Long> {

    @Query("select max(date) from LoanDetail where loanId=?1")
    Date findLastExtensionDateByLoanId(Long loanId);

    Iterable<LoanDetail> findAllByLoanId(Long loanId);
}
