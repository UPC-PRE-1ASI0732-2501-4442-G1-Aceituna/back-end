package upc.edu.ecomovil.api.reservations.interfase.rest.resources;

public record ReservationResource(
        Long id,
        String status,
        Long vehicleId,
        Long profileId
){}

//public record VehicleResource(Long id, String type, String name, Integer year, Integer review,Double priceRent, Double priceSell, Boolean isAvailable, String imageUrl, Float lat, Float lng, Long studentId) {