package org.sebas.pos.repo;

import org.sebas.pos.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepo extends JpaRepository<Sales, Long> {

}
