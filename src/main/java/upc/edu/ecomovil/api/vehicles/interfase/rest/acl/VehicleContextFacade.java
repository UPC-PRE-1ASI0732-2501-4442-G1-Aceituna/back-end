package upc.edu.ecomovil.api.vehicles.interfase.rest.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.ecomovil.api.vehicles.domain.model.aggregates.Vehicle;
import upc.edu.ecomovil.api.vehicles.domain.model.queries.GetVehicleByIdQuery;
import upc.edu.ecomovil.api.vehicles.domain.services.VehicleCommandService;
import upc.edu.ecomovil.api.vehicles.domain.services.VehicleQueryService;

import java.util.Optional;

@Service
public class VehicleContextFacade {
    private final VehicleCommandService vehicleService;
    private final VehicleQueryService vehicleQueryService;


    @Autowired
    public VehicleContextFacade(VehicleCommandService vehicleService, VehicleQueryService vehicleQueryService) {
        this.vehicleService = vehicleService;
        this.vehicleQueryService = vehicleQueryService;
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        GetVehicleByIdQuery query = new GetVehicleByIdQuery(id);
        return vehicleQueryService.handle(query);
    }
}

