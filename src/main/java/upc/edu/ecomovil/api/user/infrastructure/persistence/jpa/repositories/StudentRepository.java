package upc.edu.ecomovil.api.user.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.edu.ecomovil.api.user.domain.model.entities.Student;
import upc.edu.ecomovil.api.user.domain.model.valueobjects.RucNumber;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByRuc(RucNumber ruc);
    List<Student> findAllByPlanId(Long planId);
    //List<Student> findByPlanId(String planId);
}
