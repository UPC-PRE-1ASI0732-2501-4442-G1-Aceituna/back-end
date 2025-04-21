package upc.edu.ecomovil.api.reservations.interfase.rest.transform;

import upc.edu.ecomovil.api.reservations.domain.model.aggregates.Reservation;
import upc.edu.ecomovil.api.reservations.interfase.rest.resources.ReservationResource;

public class ReservationResourceFromEntityAssembler {
    public static ReservationResource toResourceFromEntity(Reservation entity){
        return new ReservationResource(entity.getId(), entity.getStatus(), entity.getVehicle().getId(), entity.getProfile().getId());
    }
}

//public class VehicleResourceFromEntityAssembler {
//    public static VehicleResource toResourceFromEntity(Vehicle entity){
//        return new VehicleResource(entity.getId(), entity.getType(), entity.getName(), entity.getYear(), entity.getReview(), entity.getPriceRent(), entity.getPriceSell(), entity.getIsAvailable(), entity.getImageUrl(), entity.getLat(), entity.getLng(), entity.getStudent().getId());
//    }
//}