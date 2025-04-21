package upc.edu.ecomovil.api.vehicles.domain.services;

import upc.edu.ecomovil.api.vehicles.domain.model.aggregates.Vehicle;
import upc.edu.ecomovil.api.vehicles.domain.model.queries.GetAllVehiclesByTypeQuery;
import upc.edu.ecomovil.api.vehicles.domain.model.queries.GetAllVehiclesQuery;
import upc.edu.ecomovil.api.vehicles.domain.model.queries.GetVehicleByIdQuery;

import java.util.List;
import java.util.Optional;

public interface VehicleQueryService {
    //    Optional<Profile> handle(GetProfileByIdQuery query);
    //    List<Profile> handle(GetAllProfilesQuery query);
    //    Optional<Profile> handle(GetProfileByEmailQuery query);

    Optional<Vehicle> handle(GetVehicleByIdQuery query);
    List<Vehicle> handle(GetAllVehiclesQuery query);
    List<Vehicle> handle(GetAllVehiclesByTypeQuery query);

}
