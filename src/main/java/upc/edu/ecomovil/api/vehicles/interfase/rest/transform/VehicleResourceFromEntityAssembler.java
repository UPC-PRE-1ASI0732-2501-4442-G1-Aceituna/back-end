package upc.edu.ecomovil.api.vehicles.interfase.rest.transform;

import upc.edu.ecomovil.api.vehicles.domain.model.aggregates.Vehicle;
import upc.edu.ecomovil.api.vehicles.interfase.rest.resources.VehicleResource;

public class VehicleResourceFromEntityAssembler {
    public static VehicleResource toResourceFromEntity(Vehicle entity){
        return new VehicleResource(entity.getId(), entity.getType(), entity.getName(), entity.getYear(), entity.getReview(), entity.getPriceRent(), entity.getPriceSell(), entity.getIsAvailable(), entity.getImageUrl(), entity.getLat(), entity.getLng(), entity.getDescription());
    }
}

//    public static ProfileResource toResourceFromEntity(Profile entity){
//        return new ProfileResource(entity.getId(),  entity.getEmail(), entity.getFullName(), entity.getPhoneNumber());
//    }