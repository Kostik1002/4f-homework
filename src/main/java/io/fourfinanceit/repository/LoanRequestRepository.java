package io.fourfinanceit.repository;

import io.fourfinanceit.model.LoanRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LoanRequestRepository extends CrudRepository<LoanRequest, Long>  {

    @Query("select count(*) from LoanRequest where date >= DATEADD('DAY', -1, CURRENT_DATE) and clientId=?1 and ip=?2")
    long getCountOfRequestsWithTheSameIp(Long clientId, String ip);
}
