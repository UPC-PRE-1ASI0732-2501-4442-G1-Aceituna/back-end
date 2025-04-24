package upc.edu.ecomovil.api.vehicles.infraestructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.edu.ecomovil.api.vehicles.domain.model.aggregates.Vehicle;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    //Aqui incluyo aquellos metodos que no estan en el JpaRepository
    List<Vehicle> findAllByDetails_Type(String type);
    List<Vehicle> findAllByOwnerId(Long ownerId);
}
