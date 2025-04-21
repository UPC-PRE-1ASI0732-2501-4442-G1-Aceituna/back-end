package upc.edu.ecomovil.api.reservations.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.edu.ecomovil.api.reservations.domain.model.aggregates.Reservation;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    //Aqui incluyo aquellos metodos que no estan en el JpaRepository
    public List<Reservation> findAllByProfileId(Long profileId);

}

