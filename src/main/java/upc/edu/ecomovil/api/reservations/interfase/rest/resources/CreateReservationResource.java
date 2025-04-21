package upc.edu.ecomovil.api.reservations.interfase.rest.resources;

public record CreateReservationResource(
        String status,
        Long vehicleId,
        Long profileId
) {
}


//public record CreateVehicleResource(String type, String name, Integer year, Integer review, Double priceRent, Double priceSell, Boolean isAvailable, String imageUrl, Float lat, Float lng, Long studentId) {
//}