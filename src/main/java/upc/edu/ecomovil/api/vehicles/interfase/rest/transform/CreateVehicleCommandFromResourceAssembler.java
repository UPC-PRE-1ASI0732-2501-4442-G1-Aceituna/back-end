package upc.edu.ecomovil.api.vehicles.interfase.rest.transform;

import upc.edu.ecomovil.api.vehicles.domain.model.commands.CreateVehicleCommand;
import upc.edu.ecomovil.api.vehicles.interfase.rest.resources.CreateVehicleResource;

public class CreateVehicleCommandFromResourceAssembler {
    public static CreateVehicleCommand toCommandFromResource(CreateVehicleResource resource, Long profileId) {
        return new CreateVehicleCommand(resource.type(), resource.name(), resource.year(), resource.review(), resource.priceRent(), resource.priceSell(), resource.isAvailable(), resource.imageUrl(), resource.lat(), resource.lng(), resource.description(), profileId);
    }
}