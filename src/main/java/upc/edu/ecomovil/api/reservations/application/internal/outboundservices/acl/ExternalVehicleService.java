package upc.edu.ecomovil.api.reservations.application.internal.outboundservices.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.ecomovil.api.vehicles.domain.model.aggregates.Vehicle;
import upc.edu.ecomovil.api.vehicles.interfase.rest.acl.VehicleContextFacade;

import java.util.Optional;

@Service
public class ExternalVehicleService {
public final VehicleContextFacade vehicleContextFacade;

@Autowired
    public ExternalVehicleService(VehicleContextFacade vehicleContextFacade) {
        this.vehicleContextFacade = vehicleContextFacade;
    }

    public Optional<Vehicle> fetchVehicleById(Long id) {
        return vehicleContextFacade.getVehicleById(id);
    }

}
