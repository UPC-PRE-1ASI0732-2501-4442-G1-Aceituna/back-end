package upc.edu.ecomovil.api.vehicles.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.edu.ecomovil.api.vehicles.domain.model.aggregates.Vehicle;
import upc.edu.ecomovil.api.vehicles.domain.model.queries.GetAllVehiclesByTypeQuery;
import upc.edu.ecomovil.api.vehicles.domain.model.queries.GetAllVehiclesQuery;
import upc.edu.ecomovil.api.vehicles.domain.model.queries.GetVehicleByIdQuery;
import upc.edu.ecomovil.api.vehicles.domain.services.VehicleQueryService;
import upc.edu.ecomovil.api.vehicles.infraestructure.persistence.jpa.repositories.VehicleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleQueryServiceImpl implements VehicleQueryService {
    private final VehicleRepository vehicleRepository;

    public VehicleQueryServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Optional<Vehicle> handle(GetVehicleByIdQuery query) {
        return vehicleRepository.findById(query.vehicleId());
    }

    @Override
    public List<Vehicle> handle(GetAllVehiclesQuery query) {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> handle(GetAllVehiclesByTypeQuery query) {
        return vehicleRepository.findAllByDetails_Type(query.type());
    }
}

//    @Override
//    public List<Acquirer> handle(GetAllAcquirerQuery query) {
//        return acquirerRepository.findAll();
//    }
//
//}