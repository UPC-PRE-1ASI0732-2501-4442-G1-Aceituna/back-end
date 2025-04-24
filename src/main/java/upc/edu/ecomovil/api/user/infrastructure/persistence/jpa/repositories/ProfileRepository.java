package upc.edu.ecomovil.api.user.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.edu.ecomovil.api.user.domain.model.aggregates.Profile;
import upc.edu.ecomovil.api.user.domain.model.valueobjects.EmailAddress;
import upc.edu.ecomovil.api.user.domain.model.valueobjects.RucNumber;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    //Aqui incluyo aquellos metodos que no estan en el JpaRepository
    Optional<Profile> findByEmail(EmailAddress emailAddress);
    Optional<Profile> findByRuc(RucNumber ruc);

}
