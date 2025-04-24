package upc.edu.ecomovil.api.vehicles.interfase.rest.resources;

public record VehicleResource(Long id, String type, String name, Integer year, Integer review,Double priceRent, Double priceSell, Boolean isAvailable, String imageUrl, Float lat, Float lng, String description) {

}
