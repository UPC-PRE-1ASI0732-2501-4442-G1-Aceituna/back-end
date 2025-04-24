package upc.edu.ecomovil.api.vehicles.domain.services;

import upc.edu.ecomovil.api.user.domain.model.aggregates.Profile;
import upc.edu.ecomovil.api.user.domain.model.commands.CreateProfileCommand;
import upc.edu.ecomovil.api.vehicles.domain.model.aggregates.Vehicle;
import upc.edu.ecomovil.api.vehicles.domain.model.commands.CreateVehicleCommand;

import java.util.Optional;

public interface VehicleCommandService {

    Optional<Vehicle> handle(CreateVehicleCommand command);
}
