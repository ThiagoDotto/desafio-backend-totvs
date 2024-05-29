package com.totvs.desafiobackendtotvs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillToPayRepository extends JpaRepository<Bill, Long> {

}
