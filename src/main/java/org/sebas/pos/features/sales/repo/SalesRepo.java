package org.sebas.pos.features.sales.repo;

import org.sebas.pos.features.sales.domain.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepo extends JpaRepository<Sales, Long> {

}
