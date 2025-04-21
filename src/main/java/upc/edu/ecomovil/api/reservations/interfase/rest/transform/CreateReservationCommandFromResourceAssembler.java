package upc.edu.ecomovil.api.reservations.interfase.rest.transform;

import upc.edu.ecomovil.api.reservations.domain.model.commands.CreateReservationCommand;
import upc.edu.ecomovil.api.reservations.interfase.rest.resources.CreateReservationResource;

public class CreateReservationCommandFromResourceAssembler {
    public static CreateReservationCommand toCommandFromResource(CreateReservationResource resource){
        return new CreateReservationCommand( resource.status(), resource.vehicleId(), resource.profileId());
    }
}
