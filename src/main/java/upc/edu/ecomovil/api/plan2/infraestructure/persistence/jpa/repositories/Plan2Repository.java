package upc.edu.ecomovil.api.plan2.infraestructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.edu.ecomovil.api.plan2.domain.model.aggregates.Plan2;

@Repository
public interface Plan2Repository extends JpaRepository<Plan2, Long> {
}
